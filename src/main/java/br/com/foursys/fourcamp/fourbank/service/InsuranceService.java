package br.com.foursys.fourcamp.fourbank.service;

import java.util.ArrayList;
import java.util.List;

import br.com.foursys.fourcamp.fourbank.exceptions.InsuranceOrCardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.PolicyOrCardNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.InsuranceNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Insurance;
import br.com.foursys.fourcamp.fourbank.repository.InsuranceRepository;

@Service
public class InsuranceService {

    private InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public List<Insurance> listAllByCreditCard(String creditCardNumber) throws InsuranceOrCardNotFoundException {
        List<Insurance> insurancesToReturn = new ArrayList<>();
        for (Insurance insurance : insuranceRepository.findAll()) {
            if (insurance.getCreditCard().getNumber().equals(creditCardNumber)) {
                insurancesToReturn.add(insurance);
            }
        }
        if (insurancesToReturn.isEmpty()) {
            throw new InsuranceOrCardNotFoundException();
        }
        return insurancesToReturn;
    }

    public void delete(Long id) throws InsuranceNotFoundException {
        verifyIfExists(id);
        insuranceRepository.deleteById(id);
    }

    private Insurance verifyIfExists(Long id) throws InsuranceNotFoundException {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException(id));
    }

    public Insurance findById(Long id) throws InsuranceNotFoundException {
        return verifyIfExists(id);
    }

}
