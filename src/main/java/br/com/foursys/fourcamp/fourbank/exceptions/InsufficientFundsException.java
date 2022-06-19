package br.com.foursys.fourcamp.fourbank.exceptions;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() { super("Saldo insuficiente para completar a transação!");}
}
