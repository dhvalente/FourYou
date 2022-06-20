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
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.Insurance;
import br.com.foursys.fourcamp.fourbank.model.Policy;
import br.com.foursys.fourcamp.fourbank.repository.InsuranceRepository;
import br.com.foursys.fourcamp.fourbank.repository.PolicyRepository;

@Service
public class InsuranceService {

	private InsuranceRepository insuranceRepository;
	@Autowired
	private PolicyRepository policyRepository;

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

	private Insurance getInsurance(Insurance insurance) {
		Insurance validInsurance = insuranceIsValid(insurance);
		return insuranceRepository.save(insurance);
	}

	private MessageResponseDTO createMessageResponse(Long id, String s) {
		return MessageResponseDTO.builder().message(s + "Insurance com a id " + id).build();
	}



	private Insurance insuranceIsValid(Insurance insurance) {
		// validações
		return insurance;
	}

	public Insurance findById(Long id) throws InsuranceNotFoundException {
		return verifyIfExists(id);
	}

	public void registerInsurance(String rules, Insurance insurance, CreditCard creditCard) {

		Integer policyNumber = insurance.getPolicy().generatePolicyNumber();
		switch (rules) {
		case "automovel":
			insurance.setRules(rules);
			Policy policyAutomovel = new Policy(null, creditCard, insurance, 35.00, "Automóvel no valor minímo de 10 salários minímos", policyNumber);			
			policyRepository.save(policyAutomovel);
			insuranceRepository.save(insurance);
			break;
		case "vida":
			insurance.setRules(rules);
			Policy policyVida = new Policy(null, creditCard, insurance, 48.00, "Não pode ser acionado para pessoas com profissões de risco", policyNumber);			
			policyRepository.save(policyVida);
			insuranceRepository.save(insurance);
			break;
		case "residencial":
			insurance.setRules(rules);
			Policy policyResidencial = new Policy(null, creditCard, insurance, 48.00, "Não pode ser acionado para pessoas com profissões de risco", policyNumber);			
			policyRepository.save(policyResidencial);
			insuranceRepository.save(insurance);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + rules);
		}
	}

    private Insurance verifyIfExists(Long id) throws InsuranceNotFoundException {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException(id));
    }

}
