package br.com.foursys.fourcamp.fourbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.foursys.fourcamp.fourbank.dto.response.TransactionMessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.TransactionNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Transaction;
import br.com.foursys.fourcamp.fourbank.service.TransactionService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/Transaction")
public class TransactionController {

	@Autowired
	private final TransactionService service;
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public TransactionMessageResponseDTO createCliente(@RequestBody Transaction transaction) {
		return service.createTransaction(transaction);
	}
	
	@GetMapping
	public List<Transaction> listAll(){
		return service.listAll();
	}
	
	@GetMapping("/{id}")
	public Transaction findById(@PathVariable Long id) throws TransactionNotFoundException {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public TransactionMessageResponseDTO updateById(@PathVariable Long id, @RequestBody Transaction transaction) throws TransactionNotFoundException{
		return service.updateById(id, transaction);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws TransactionNotFoundException{
		service.delete(id);
	}
}
