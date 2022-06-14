package com.fourcamp.fourcamp22.java.group4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsuranceNotFoundException extends Exception{

    public InsuranceNotFoundException(Long id) {
        super("Nenhum seguro encontrado com a id " + id);
    }

}
