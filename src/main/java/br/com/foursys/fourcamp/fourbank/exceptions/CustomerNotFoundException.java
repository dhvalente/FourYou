package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(Long id) {
        super("Nenhum Cliente encontrado com a id " + id);
    }

}
