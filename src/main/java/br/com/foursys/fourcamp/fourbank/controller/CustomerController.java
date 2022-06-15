package br.com.foursys.fourcamp.fourbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.foursys.fourcamp.fourbank.dto.response.CustomerMessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CustomerNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Address;
import br.com.foursys.fourcamp.fourbank.model.Customer;
import br.com.foursys.fourcamp.fourbank.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/Customer")

public class CustomerController {
	
	@Autowired
	private final CustomerService customerService;
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerMessageResponseDTO createCliente(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@GetMapping
	public List<Customer> listAll(){
		return customerService.listAll();
	}
	
	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) throws CustomerNotFoundException {
		return customerService.findById(id);
	}
	
	@PutMapping("/{id}")
	public CustomerMessageResponseDTO updateById(@PathVariable Long id, @RequestBody Customer customer, @RequestBody Address addres) throws CustomerNotFoundException{
		return customerService.updateById(id, customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws CustomerNotFoundException{
		customerService.delete(id);
	}
	
}
