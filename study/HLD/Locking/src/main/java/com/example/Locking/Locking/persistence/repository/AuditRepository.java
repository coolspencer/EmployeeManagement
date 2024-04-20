package com.example.Locking.Locking.persistence.repository;

import com.example.Locking.Locking.persistence.entity.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, String> {

}
