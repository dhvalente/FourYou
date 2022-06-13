package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.dto.response.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CustomerNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Customer;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.foursys.fourcamp.fourbank.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService {
	
	private CustomerRepository customerRepository;
	
	public MessageResponseDTO createCustomer(Customer customer) {
		Customer savedCustomer = getCustomer(customer);
		return createMessageResponse(savedCustomer.getId(), "Criado ");
	}
	
	public MessageResponseDTO updateById(Long id, Customer customer) throws CustomerNotFoundException {
		verifyIfExists(id);
		Customer updateCustomer = getCustomer(customer);
		return createMessageResponse(updateCustomer.getId(), "Atualizado");
	}
	
	private MessageResponseDTO createMessageResponse(Long id, String s) {
		return MessageResponseDTO.builder()
				.message(s + "Cliente com o id" + id)
				.build();
	}
	
	public List<Customer> listAll(){
		List<Customer> allCustomers = customerRepository.findAll();		
		return allCustomers;
		
	}
	
	public void delete(Long id) throws CustomerNotFoundException {
		verifyIfExists(id);
		customerRepository.deleteById(id);
	}
	
	private Customer verifyIfExists(Long id) throws CustomerNotFoundException{
		return customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id));
	}
	
	private Customer getCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	
	
	public Customer findById(Long id) throws CustomerNotFoundException{
		Customer customer = verifyIfExists(id);
		return customer;
	}
	
	

}
