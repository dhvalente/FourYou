package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debitCard")
public class DebitCardController {

	@Autowired
	private final DebitCardService debitCardService;

	@Autowired
	public DebitCardController(DebitCardService debitCardService) {
		this.debitCardService = debitCardService;
	}

	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createDebitCard(@RequestBody DebitCard debitCard) {
		return debitCardService.createDebitCard(debitCard);
	}

	@GetMapping
	public List<DebitCard> listAll() {
		return debitCardService.listAll();
	}

	@GetMapping("/findById/{id}")
	public DebitCard findById(@PathVariable Long id) throws CardNotFoundException {
		return debitCardService.findById(id);
	}
	@GetMapping("/findByNumber/{number}")
	public DebitCard findById(@PathVariable String number) throws CardNotFoundException {
		return debitCardService.findByNumber(number);
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
}