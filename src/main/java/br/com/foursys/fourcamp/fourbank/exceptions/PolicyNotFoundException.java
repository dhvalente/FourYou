package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PolicyNotFoundException extends Exception{

    public PolicyNotFoundException(Long id) {
        super("Nenhuma apólice encontrada com a id " + id);
    }

}
