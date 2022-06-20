package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends Exception {

    public TransactionNotFoundException(Long id) {
        super("Nenhuma transação encontrada com a id " + id);
    }

}
