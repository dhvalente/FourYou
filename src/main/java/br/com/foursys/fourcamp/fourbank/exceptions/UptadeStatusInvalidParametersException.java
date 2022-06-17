package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UptadeStatusInvalidParametersException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UptadeStatusInvalidParametersException() {
        super("Por favor digite algum status válido -> ativo ou desativado");
    }

}