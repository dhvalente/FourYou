package br.com.foursys.fourcamp.fourbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.foursys.fourcamp.fourbank.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	AddressService addressService;
	
	   @GetMapping(value = "/findcep/{cep}")
	    public ResponseEntity<String> findAddresByCep(@PathVariable String cep) {
		   String obj = addressService.findAddressByCep(cep);		   
	        return ResponseEntity.ok().body(obj);
	    }
}
