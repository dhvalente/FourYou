package com.fourcamp.fourcamp22.java.group4.model;

public class PixPayment extends PaymentMethod {

    private Integer id;
    private PaymentTypeEnum type;
    private String identifier;
    private Account account;
    private Account originAccount;
    private Account destinationAccount;
    private Double value;
}
