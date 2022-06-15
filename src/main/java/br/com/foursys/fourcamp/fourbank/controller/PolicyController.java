package com.fourcamp.fourbank.controller;

import com.fourcamp.fourbank.dto.response.MessageResponseDTO;
import com.fourcamp.fourbank.exceptions.PolicyNotFoundException;
import com.fourcamp.fourbank.model.Policy;
import com.fourcamp.fourbank.service.PolicyService;
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

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPolicy(@RequestBody Policy policy) {
        return policyService.createPolicy(policy);
    }

    @GetMapping
    public List<Policy> listAll() {
        return policyService.listAll();
    }

    @GetMapping("/{id}")
    public Policy findById(@PathVariable Long id) throws PolicyNotFoundException {
        return policyService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody Policy policy)
            throws PolicyNotFoundException {
        return policyService.updateById(id, policy);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PolicyNotFoundException {
        policyService.delete(id);
    }
}