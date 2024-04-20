package com.example.Locking.Locking.persistence.service;


import com.example.Locking.Locking.persistence.entity.Audit;
import com.example.Locking.Locking.persistence.entity.Stock;
import com.example.Locking.Locking.persistence.exceptions.OptimisticLockingException;
import com.example.Locking.Locking.persistence.repository.AuditRepository;
import com.example.Locking.Locking.persistence.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PurchaseOrder {

    private StockRepository stockRepository;
    private AuditRepository auditRepository;

    /**
     * Purchase the product without considering version field in stock_table
     * @param productId Id of the product
     */
    public void purchase(String productId) {
        Optional<Stock> practiceOptional = stockRepository.findById(productId);
        if (practiceOptional.isPresent() && practiceOptional.get().getCount() > 0){
            Stock practice = practiceOptional.get();
            Audit audit = Audit.builder().count(practice.getCount())
                    .id(UUID.randomUUID().toString()).build();
            practice.setCount(practice.getCount() - 1);
            int update = stockRepository.updateCount(practice.getCount(), productId);
            if(update == 0) throw new OptimisticLockingException("Update failed");
            auditRepository.save(audit);
        }
    }

    /**
     * Purchase the product by considering version field in stock_table
     * OptimisticLocking case
     * @param productId Id of the product
     */
    public void purchaseV1(String productId){
        Optional<Stock> practiceOptional = stockRepository.findById(productId);
        if (practiceOptional.isPresent() && practiceOptional.get().getCount() > 0){
            Stock practice = practiceOptional.get();
            Audit audit = Audit.builder().count(practice.getCount()).id(UUID.randomUUID().toString()).build();
            practice.setCount(practice.getCount() - 1);
            int update = stockRepository.updateCountWithVersion(practice.getCount(), practice.getVersion(), productId);
            if(update == 0) throw new OptimisticLockingException("Update failed");
            auditRepository.save(audit);
        }
    }

    /**
     * Purchase the product by locking the row.
     * Notice the @Transactional annotation.
     * PessimisticLocking case
     * @param productId Id of the product
     */
    @Transactional
    public void purchaseV2(String productId){
        Optional<Stock> practiceOptional = stockRepository.findById1(productId);
        if (practiceOptional.isPresent() && practiceOptional.get().getCount() > 0){
            Stock practice = practiceOptional.get();
            Audit audit = Audit.builder().count(practice.getCount()).id(UUID.randomUUID().toString()).build();
            practice.setCount(practice.getCount() - 1);
            int update = stockRepository.updateCountWithVersion(practice.getCount(), practice.getVersion(), productId);
            if(update == 0) throw new OptimisticLockingException("Update failed");
            auditRepository.save(audit);
        }
    }
}
