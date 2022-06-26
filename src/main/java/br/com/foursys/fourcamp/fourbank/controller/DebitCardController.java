package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNumberNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.service.DebitCardService;
import br.com.foursys.fourcamp.fourbank.util.ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debitcard")
public class DebitCardController {

	@Autowired
	private final DebitCardService debitCardService;

	@Autowired
	public DebitCardController(DebitCardService debitCardService) {
		this.debitCardService = debitCardService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createCreditCard(@RequestBody DebitCard debitCard) {
		 return ResponseEntity.status(HttpStatus.CREATED).body(debitCardService.createDebitCard(debitCard));
	}

	@GetMapping
	public List<DebitCard> listAll() {
		return debitCardService.listAll();
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) throws CardNotFoundException {
		try {		
			return ResponseEntity.status(HttpStatus.OK).body(debitCardService.findById(id));
		}catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}

	}
	
	@GetMapping("/findByNumber/{number}")
	public ResponseEntity<Object> findById(@PathVariable String number) throws CardNumberNotFoundException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(debitCardService.findByNumber(number));
		}catch (CardNumberNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}
		
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody DebitCard debitCard)
			throws CardNotFoundException {
		return debitCardService.updateById(id, debitCard);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws CardNotFoundException {
		debitCardService.delete(id);
	}
	
	@PutMapping("/updatestatus/{id}/{status}")
	public DebitCard updateStatus(@PathVariable Long id, @PathVariable String status) throws CardNotFoundException {
		return debitCardService.updateStatus(status , id);

	}
	
	@PutMapping("/updatelimit/{id}/{limit}")
	public DebitCard updateStatus(@PathVariable Long id, @PathVariable Double limit) throws CardNotFoundException {
		return debitCardService.updateLimitByTransaction(limit, id);

	}
	
	@PutMapping("/updatepassword/{id}/{password}")
	public DebitCard updatePassword(@PathVariable Long id, @PathVariable String password)
			throws CardNotFoundException {
		return debitCardService.updatePassword(id, password);

	}
	
}