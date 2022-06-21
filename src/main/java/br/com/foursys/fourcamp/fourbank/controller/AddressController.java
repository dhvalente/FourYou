package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.model.Address;
import br.com.foursys.fourcamp.fourbank.service.AddressService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	AddressService addressService;

	@GetMapping(value = "/findcep/{cep}")
	public ResponseEntity<Object> findAddresByCep(@PathVariable String cep) {
			return ResponseEntity.status(HttpStatus.CREATED).body(addressService.findAddressByCep(cep));
	}
	
	@GetMapping
	public ResponseEntity<List<Address>> findAll() {
		List<Address> list = addressService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Address> findById(@PathVariable Long id) {
		Address obj = addressService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Address> insert(@RequestBody Address obj) {
		obj = addressService.insert(obj);
		// Para retornar com status 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		addressService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Address> update(@PathVariable Long id , @RequestBody Address obj) {
		obj = addressService.update(id, obj);
		return ResponseEntity.ok(obj);
	}
}
