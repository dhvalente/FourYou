package com.fourcamp.fourbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFoundException extends Exception{

    public CardNotFoundException(Long id) {
        super("Nenhum cartão encontrado com a número " + id);
    }

}
