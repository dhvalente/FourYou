package br.com.foursys.fourcamp.fourbank.controller;

import java.net.URI;
import java.util.List;
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

import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.service.DebitCardService;

@RestController
@RequestMapping(value = "/creditCard")
public class DebitCardController {

	@Autowired
	private DebitCardService debitCardService;

	@GetMapping
	public ResponseEntity<List<DebitCard>> findAll() {
		List<DebitCard> list = debitCardService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DebitCard> findById(@PathVariable Long id) {
		DebitCard obj = debitCardService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<DebitCard> insert(@RequestBody DebitCard obj) {
		obj = debitCardService.insert(obj);
		// Para retornar com status 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		debitCardService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<DebitCard> update(@PathVariable Long id , @RequestBody DebitCard obj) {
		obj = debitCardService.update(id, obj);
		return ResponseEntity.ok(obj);
	}
}