package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParametersException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidParametersException() {
        super("Parâmetros Inválidos");
    }

}
