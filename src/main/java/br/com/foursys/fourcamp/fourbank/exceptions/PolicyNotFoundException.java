package com.fourcamp.fourbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PolicyNotFoundException extends Exception{

    public PolicyNotFoundException(Long id) {
        super("Nenhuma apólice encontrada com a id " + id);
    }

}
