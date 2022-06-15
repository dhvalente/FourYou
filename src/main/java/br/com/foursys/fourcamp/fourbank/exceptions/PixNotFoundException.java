package com.fourcamp.fourbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PixNotFoundException extends Exception {

    public PixNotFoundException(String id) {
        super("Nenhum Pix encontrado com a id " + id);
    }

}
