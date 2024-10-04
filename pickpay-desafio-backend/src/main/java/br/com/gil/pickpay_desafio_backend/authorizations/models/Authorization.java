package br.com.gil.pickpay_desafio_backend.authorizations.models;

public record Authorization(String message) {

    public boolean isAuthorized(){
        System.out.println(message);
        System.out.println("Autorizado".equals(message));
        return message.equals("Autorizado");
    }
}
