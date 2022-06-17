package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PixNotFoundException extends Exception {

    public PixNotFoundException(String id) {
        super("Nenhum Pix encontrado com a id " + id);
    }

}
