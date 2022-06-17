package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsuranceNotFoundException extends Exception{

    public InsuranceNotFoundException(Long id) {
        super("Nenhum seguro encontrado com a id " + id);
    }

}
