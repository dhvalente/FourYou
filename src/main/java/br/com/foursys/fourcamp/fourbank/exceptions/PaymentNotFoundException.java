package com.fourcamp.fourcamp22.java.group4.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends Exception {

    public PaymentNotFoundException(Long id) {
        super("Nenhum pagamento encontrado com a id " + id);
    }

}
