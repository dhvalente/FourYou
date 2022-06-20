package br.com.foursys.fourcamp.fourbank.controller;

import java.util.List;

import br.com.foursys.fourcamp.fourbank.exceptions.InsuranceOrCardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InsuranceNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.Insurance;
import br.com.foursys.fourcamp.fourbank.service.InsuranceService;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private final InsuranceService insuranceService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public List<Insurance> listAllByCreditCard(@PathVariable String creditCardNumber) throws
            InsuranceOrCardNotFoundException {
        return insuranceService.listAllByCreditCard(creditCardNumber);
    }
    
    @GetMapping("/list")
    public List<Insurance> listAll() throws
            InsuranceOrCardNotFoundException {
        return insuranceService.listAll();
    }

    @GetMapping("/{id}")
    public Insurance findById(@PathVariable Long id) throws InsuranceNotFoundException {
        return insuranceService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws InsuranceNotFoundException {
        insuranceService.delete(id);
    }
    
	@PostMapping("/create/{id}/{rules}")
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createInsurance(@PathVariable Long id, @PathVariable String rules) throws CardNotFoundException {
		return insuranceService.createInsurance(rules, id);
	}
}
