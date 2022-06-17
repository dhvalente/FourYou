package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreditLimitInsufficientException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CreditLimitInsufficientException() {
        super("Limite de crédito insuficiente para realizar a transação");
    }
   
}