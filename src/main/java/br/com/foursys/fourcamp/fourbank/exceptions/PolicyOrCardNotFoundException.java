package br.com.foursys.fourcamp.fourbank.exceptions;

public class PolicyOrCardNotFoundException extends Exception {

    public PolicyOrCardNotFoundException() {
        super("O cartão não existe ou não possui apólices!");
    }
}
