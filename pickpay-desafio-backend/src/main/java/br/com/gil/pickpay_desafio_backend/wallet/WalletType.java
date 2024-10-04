package br.com.gil.pickpay_desafio_backend.wallet;

public enum WalletType {
    COMUM(1), LOGISTA(2);

    private int value;

    private WalletType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
