package com.fourcamp.fourbank.model;

import com.fourcamp.fourbank.enums.PaymentTypeEnum;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;

public class PaymentMethod {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @NonNull
    private PaymentTypeEnum type;
    @NonNull
    private String identifier;
    @NonNull
    private Account account;
    @NonNull
    private Account originAccount;
    @NonNull
    private Account destinationAccount;
    @NonNull
    private Double value;
}