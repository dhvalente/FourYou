package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.service.CreditCardService;
import br.com.foursys.fourcamp.fourbank.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

	/* faltam verificações

	@PostMapping("/")
	public ResponseEntity<Object> createCreditCard(@RequestBody @Valid CreditCard creditCard) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(return creditCardService.createCreditCard(creditCard););
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseModel(HttpStatus.NOT_ACCEPTABLE,
					HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));
		}
	} */

	@GetMapping
	public List<CreditCard> listAll() {
		return creditCardService.listAll();
	}

	@GetMapping("/findById/{id}")
	public CreditCard findById(@PathVariable Long id) throws CardNotFoundException {
		return creditCardService.findById(id);
	}
	
	@GetMapping("/findByNumber/{number}")
	public CreditCard findById(@PathVariable String number) throws CardNotFoundException {
		return creditCardService.findByNumber(number);
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
	
	@PutMapping("/updatestatus/{id}/{status}")
	public CreditCard updateStatus(@PathVariable Long id, @PathVariable String status) throws CardNotFoundException {
		return creditCardService.updateStatus(status , id);

	}
}