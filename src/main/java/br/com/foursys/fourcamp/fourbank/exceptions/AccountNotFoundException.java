package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(Long id) {
        super("Nenhum Pix encontrado com a id " + id);
    }

}
