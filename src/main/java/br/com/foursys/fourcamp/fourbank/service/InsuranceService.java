package br.com.foursys.fourcamp.fourbank.service;

import java.util.List;

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

    public MessageResponseDTO createInsurance(Insurance insurance) {
        Insurance savedInsurance = getInsurance(insurance);
        return createMessageResponse(savedInsurance.getId(), "Created ");
    }

    public List<Insurance> listAll() {
        return insuranceRepository.findAll();
    }

    public MessageResponseDTO updateById(Long id, Insurance insurance) throws InsuranceNotFoundException {
        verifyIfExists(id);
        Insurance validInsurance = insuranceIsValid(insurance);
        Insurance updatedInsurance = getInsurance(validInsurance);
        return createMessageResponse(updatedInsurance.getId(), "Updated ");
    }

    public void delete(Long id) throws InsuranceNotFoundException {
        verifyIfExists(id);
        insuranceRepository.deleteById(id);
    }

    private Insurance getInsurance(Insurance insurance) {
        Insurance validInsurance = insuranceIsValid(insurance);
        return insuranceRepository.save(insurance);
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + "Insurance com a id " + id)
                .build();
    }

    private Insurance verifyIfExists(Long id) throws InsuranceNotFoundException {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException(id));
    }

    private Insurance insuranceIsValid(Insurance insurance) {
        //validações
        return insurance;
    }

    public Insurance findById(Long id) throws InsuranceNotFoundException {
        return verifyIfExists(id);
    }

}
