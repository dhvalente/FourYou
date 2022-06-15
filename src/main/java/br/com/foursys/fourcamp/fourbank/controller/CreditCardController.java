package br.com.foursys.fourcamp.fourbank.controller;

import java.net.URI;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.service.CreditCardService;

@RestController
@RequestMapping(value = "/creditCard")
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;

	@GetMapping
	public ResponseEntity<List<CreditCard>> findAll() {
		List<CreditCard> list = creditCardService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CreditCard> findById(@PathVariable Long id) {
		CreditCard obj = creditCardService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<CreditCard> insert(@RequestBody CreditCard obj) {
		obj = creditCardService.insert(obj);
		// Para retornar com status 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		creditCardService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CreditCard> update(@PathVariable Long id , @RequestBody CreditCard obj) {
		obj = creditCardService.update(id, obj);
		return ResponseEntity.ok(obj);
	}
}