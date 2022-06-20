package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.dto.response.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.PolicyNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.PolicyOrCardNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Policy;
import br.com.foursys.fourcamp.fourbank.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyService {

    private PolicyRepository policyRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> listAllByCreditCard(String creditCardNumber) throws PolicyOrCardNotFoundException {
        List<Policy> policiesToReturn = new ArrayList<>();
        for (Policy policy : policyRepository.findAll()) {
            if (policy.getCreditCard().getNumber().equals(creditCardNumber)) {
                policiesToReturn.add(policy);
            }
        }
        if (policiesToReturn.isEmpty()) {
            throw new PolicyOrCardNotFoundException();
        }
        return policiesToReturn;
    }

    public void delete(Long id) throws PolicyNotFoundException {
        verifyIfExists(id);
        policyRepository.deleteById(id);
    }

    private Policy verifyIfExists(Long id) throws PolicyNotFoundException {
        return policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException(id));
    }

    public Policy findById(Long id) throws PolicyNotFoundException {
        return verifyIfExists(id);
    }

}