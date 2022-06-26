package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNumberNotFoundException extends Exception{

    public CardNumberNotFoundException(String number) {
        super("Nenhum cartão encontrado com o número " + number);
    }

}
