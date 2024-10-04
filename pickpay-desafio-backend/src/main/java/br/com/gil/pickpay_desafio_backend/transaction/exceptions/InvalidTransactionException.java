package br.com.gil.pickpay_desafio_backend.transaction.exceptions;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message){
        super(message);
    }
}
