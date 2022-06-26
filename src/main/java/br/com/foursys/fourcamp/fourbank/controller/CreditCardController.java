	package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.UpdatePasswordDTO;
import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNumberNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InvalidParametersException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.service.CreditCardService;
import br.com.foursys.fourcamp.fourbank.util.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {
	
	@Autowired
	private final CreditCardService creditCardService;

	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createCreditCard(@RequestBody CreditCard creditCard) {
		 return ResponseEntity.status(HttpStatus.CREATED).body(creditCardService.createCreditCard(creditCard));
	}

	@GetMapping
	public ResponseEntity<List<CreditCard>>listAll() {		
		List<CreditCard> list = creditCardService.listAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) throws CardNotFoundException {
		try {		
			return ResponseEntity.status(HttpStatus.OK).body(creditCardService.findById(id));
		}catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}

	}
	
	@GetMapping("/findByNumber/{number}")
	public ResponseEntity<Object> findById(@PathVariable String number) throws CardNumberNotFoundException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(creditCardService.findByNumber(number));
		}catch (CardNumberNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}
		
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody CreditCard creditCard)
			throws CardNotFoundException {
		try {
			MessageResponseDTO obj = creditCardService.updateById(id, creditCard);
			return ResponseEntity.ok(obj); 
		}catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object>deleteById(@PathVariable Long id) throws CardNotFoundException {
		try {
			creditCardService.delete(id);
			return ResponseEntity.noContent().build();
		}catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}
		
	}
	
	@PutMapping("/updatestatus/{id}/{status}")
	public ResponseEntity<Object> updateStatus(@PathVariable Long id, @PathVariable String status) throws CardNotFoundException, InvalidParametersException {
		try {
			CreditCard obj =  creditCardService.updateStatus(status , id);
			return ResponseEntity.ok(obj);
		} catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}catch (InvalidParametersException e) {
			 return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseModel(HttpStatus.NOT_ACCEPTABLE,
	                    HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));
		}		
	}

	@PutMapping("/updatepassword/{id}")
	public ResponseEntity<Object> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordDTO password)
			throws CardNotFoundException {
		try {
			CreditCard obj = creditCardService.updatePassword(id, password);
			return ResponseEntity.ok(obj);
		}catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}		

	}

}