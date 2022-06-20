package br.com.foursys.fourcamp.fourbank.exceptions;

public class InsuranceOrCardNotFoundException extends Exception {

    public InsuranceOrCardNotFoundException() {
        super("Cartão não existe ou não possui seguros!");
    }
}
