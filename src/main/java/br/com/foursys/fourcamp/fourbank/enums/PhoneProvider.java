package br.com.foursys.fourcamp.fourbank.enums;

public enum PhoneProvider {

    TIM(0),
    CLARO(1),
    VIVO(2);

    private Integer key;
    private PhoneProvider(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
