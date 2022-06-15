package com.fourcamp.fourbank.service;

import com.fourcamp.fourbank.dto.response.MessageResponseDTO;
import com.fourcamp.fourbank.exceptions.PolicyNotFoundException;
import com.fourcamp.fourbank.model.Policy;
import com.fourcamp.fourbank.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    private PolicyRepository policyRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public MessageResponseDTO createPolicy(Policy policy) {
        Policy savedPolicy = getPolicy(policy);
        return createMessageResponse(savedPolicy.getId(), "Created ");
    }

    public List<Policy> listAll() {
        return policyRepository.findAll();
    }

    public MessageResponseDTO updateById(Long id, Policy policy) throws PolicyNotFoundException {
        verifyIfExists(id);
        Policy validPolicy = policyIsValid(policy);
        Policy updatedPolicy = getPolicy(validPolicy);
        return createMessageResponse(updatedPolicy.getId(), "Updated ");
    }

    public void delete(Long id) throws PolicyNotFoundException {
        verifyIfExists(id);
        policyRepository.deleteById(id);
    }

    private Policy getPolicy(Policy policy) {
        Policy validPolicy = policyIsValid(policy);
        return policyRepository.save(policy);
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + "Policy com a id " + id)
                .build();
    }

    private Policy verifyIfExists(Long id) throws PolicyNotFoundException {
        return policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException(id));
    }

    private Policy policyIsValid(Policy policy) {
        //validações
        return policy;
    }

    public Policy findById(Long id) throws PolicyNotFoundException {
        return verifyIfExists(id);
    }

}
