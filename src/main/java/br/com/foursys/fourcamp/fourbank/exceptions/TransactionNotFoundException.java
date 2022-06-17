package br.com.foursys.fourcamp.fourbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransactionNotFoundException (Long id){
		super("Nenhum cliente encontrado com o id: " + id);
	}

}
