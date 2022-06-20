package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.exceptions.PolicyNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.PolicyOrCardNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Policy;
import br.com.foursys.fourcamp.fourbank.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public List<Policy> listAll(@PathVariable String creditCardNumber) throws PolicyOrCardNotFoundException {
        return policyService.listAllByCreditCard(creditCardNumber);
    }

    @GetMapping("/{id}")
    public Policy findById(@PathVariable Long id) throws PolicyNotFoundException {
        return policyService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PolicyNotFoundException {
        policyService.delete(id);
    }
}
