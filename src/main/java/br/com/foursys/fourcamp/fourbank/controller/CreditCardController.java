package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditCard")
public class CreditCardController {

	@Autowired
	private final CreditCardService creditCardService;

	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createCreditCard(@RequestBody CreditCard creditCard) {
		return creditCardService.createCreditCard(creditCard);
	}

	@GetMapping
	public List<CreditCard> listAll() {
		return creditCardService.listAll();
	}

	@GetMapping("/{id}")
	public CreditCard findById(@PathVariable Long id) throws CardNotFoundException {
		return creditCardService.findById(id);
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody CreditCard creditCard)
			throws CardNotFoundException {
		return creditCardService.updateById(id, creditCard);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws CardNotFoundException {
		creditCardService.delete(id);
	}
	
	@PutMapping("/updatestatus/{id}")
	public CreditCard updateStatus(@PathVariable Long id, @RequestBody String status) throws CardNotFoundException {
		return creditCardService.updateStatus(status , id);

	}
}