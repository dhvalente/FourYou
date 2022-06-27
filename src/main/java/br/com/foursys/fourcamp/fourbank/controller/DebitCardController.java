package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.dto.UpdatePasswordDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNumberNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InvalidParametersException;
import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.service.DebitCardService;
import br.com.foursys.fourcamp.fourbank.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
	public ResponseEntity<List<DebitCard>> listAll() {
		List<DebitCard> list = debitCardService.listAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) throws CardNotFoundException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(debitCardService.findById(id));
		} catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}

	}

	@GetMapping("/findByNumber/{number}")
	public ResponseEntity<Object> findById(@PathVariable String number) throws CardNumberNotFoundException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(debitCardService.findByNumber(number));
		} catch (CardNumberNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody DebitCard debitCard)
			throws CardNotFoundException {
		try {
			MessageResponseDTO obj = debitCardService.updateById(id, debitCard);
			return ResponseEntity.ok(obj);
		} catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteById(@PathVariable Long id) throws CardNotFoundException {
		try {
			debitCardService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}

	}

	@PutMapping("/updatestatus/{id}/{status}")
	public ResponseEntity<Object> updateStatus(@PathVariable Long id, @PathVariable String status)
			throws CardNotFoundException, InvalidParametersException {
		try {
			DebitCard obj = debitCardService.updateStatus(status, id);
			return ResponseEntity.ok(obj);
		} catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage()));
		} catch (InvalidParametersException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseModel(HttpStatus.NOT_ACCEPTABLE,
                    HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));		}
	}

	@PutMapping("/updatelimit/{id}/{limit}")
	public ResponseEntity<Object> updateLimitByTransaction(@PathVariable Long id, @PathVariable Double limit) throws CardNotFoundException {
		try {
			DebitCard obj =  debitCardService.updateLimitByTransaction(limit, id);
			return ResponseEntity.ok(obj);
		} catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage()));
		} catch (MethodArgumentTypeMismatchException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
					new ResponseModel(HttpStatus.NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));
		}
	}

	
	@PutMapping("/updatepassword/{id}")
	public ResponseEntity<Object> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordDTO password)
			throws CardNotFoundException {
		try {
			DebitCard obj = debitCardService.updatePassword(id, password);
			return ResponseEntity.ok(obj);
		}catch (CardNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}		

	}

}