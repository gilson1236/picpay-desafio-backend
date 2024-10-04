package br.com.gil.pickpay_desafio_backend.authorizations.exceptions;

public class UnauthorizedTransactionException extends RuntimeException {

    public UnauthorizedTransactionException(String message){
        super(message);
    }
}
