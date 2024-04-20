package com.example.Locking.Locking.persistence.exceptions;

public class OptimisticLockingException extends RuntimeException{

    public OptimisticLockingException(String message){
        super(message);
    }
}
