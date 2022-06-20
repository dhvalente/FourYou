package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(Integer id) {
        super("Nenhuma conta encontrada com a id " + id);
    }

}
