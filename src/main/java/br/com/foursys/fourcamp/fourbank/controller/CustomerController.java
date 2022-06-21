package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.CustomerLoginDTO;
import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CustomerNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Address;
import br.com.foursys.fourcamp.fourbank.model.Customer;
import br.com.foursys.fourcamp.fourbank.repository.CustomerRepository;
import br.com.foursys.fourcamp.fourbank.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private final CustomerService customerService;
	@Autowired
	private final CustomerRepository repository;
	private final PasswordEncoder encoder;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createCliente(@RequestBody Customer customer) {
		customer.setPassword(encoder.encode(customer.getPassword()));
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
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody Customer customer, @RequestBody Address addres)
			throws CustomerNotFoundException{
		return customerService.updateById(id, customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws CustomerNotFoundException{
		customerService.delete(id);
	}
	@GetMapping("/validarSenha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
												@RequestParam String password) {

		Optional<Customer> optUsuario = repository.findByLogin(login);
		if (optUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		Customer usuario = optUsuario.get();
		boolean valid = encoder.matches(password, usuario.getPassword());

		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}

	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestBody CustomerLoginDTO loginDTO) {
		Customer customer = getCustomerByLoginAndPassword(loginDTO.getLogin(), loginDTO.getPassword());
		if (customer != null) {
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody CustomerLoginDTO loginDTO) {
		String password = forgotPassword(loginDTO.getLogin());
		return ResponseEntity.status(HttpStatus.OK).body(password);
	}

	public Customer getCustomerByLoginAndPassword(String login, String password) {
		return customerService.getCustomerByEmailAndPassword(login, password);
	}

	public String forgotPassword(String login) {
		return customerService.getCustomerByEmail(login);
	}
}
