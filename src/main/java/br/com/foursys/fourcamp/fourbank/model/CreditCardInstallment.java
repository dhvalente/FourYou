package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CreditCardInstallment {

    LocalDate installmentToExpire;

    Integer numberOfInstallmentsRemaining;
}
