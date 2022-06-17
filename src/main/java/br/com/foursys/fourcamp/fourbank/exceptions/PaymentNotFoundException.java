package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends Exception {

    public PaymentNotFoundException(Long id) {
        super("Nenhum pagamento encontrado com a id " + id);
    }

}
