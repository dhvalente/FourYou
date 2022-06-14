package com.fourcamp.fourcamp22.java.group4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(Long id) {
        super("Nenhum Cliente encontrado com a id " + id);
    }
    //todo conferir tipo de id
}
