package br.com.foursys.fourcamp.fourbank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.foursys.fourcamp.fourbank.exceptions.InsuranceOrCardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.PolicyOrCardNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InsuranceNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.Insurance;
import br.com.foursys.fourcamp.fourbank.model.Policy;
import br.com.foursys.fourcamp.fourbank.repository.CreditCardRepository;
import br.com.foursys.fourcamp.fourbank.repository.InsuranceRepository;
import br.com.foursys.fourcamp.fourbank.repository.PolicyRepository;

@Service
public class InsuranceService {

	private InsuranceRepository insuranceRepository;
	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	public InsuranceService(InsuranceRepository insuranceRepository) {
		this.insuranceRepository = insuranceRepository;
	}

	public MessageResponseDTO createInsurance(String rules, Long id) throws CardNotFoundException {
		CreditCard creditCard = creditCardService.findById(id);
		Insurance savedInsurance = registerInsurance(rules, creditCard);
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

	public Insurance registerInsurance(String rules, CreditCard creditCard) {
		Insurance insurance = new Insurance();
		insuranceRepository.save(insurance);
		Integer policyNumber = insurance.getPolicy().generatePolicyNumber();
		switch (rules) {
		case "card":
			insurance.setRules(rules);
			Policy policyAutomovel = new Policy(creditCard, insurance, 35.00, "Seguro contra roubo valor do reembolso equivalente à 10 salários minímos");			
			policyRepository.save(policyAutomovel);
			insurance.setPolicy(policyAutomovel);
			insurance.setCreditCard(creditCard);
			insuranceRepository.save(insurance);
			break;
		case "vida":
			insurance.setRules(rules);
			Policy policyVida = new Policy(creditCard, insurance, 48.00, "Não pode ser acionado para pessoas com profissões de risco");			
			policyRepository.save(policyVida);
			insurance.setPolicy(policyVida);
			insurance.setCreditCard(creditCard);
			insuranceRepository.save(insurance);
			break;
		case "residencial":
			insurance.setRules(rules);
			Policy policyResidencial = new Policy(creditCard, insurance, 113.00, "Não pode ser acionado para pessoas com profissões de risco");			
			policyRepository.save(policyResidencial);
			insurance.setPolicy(policyResidencial);
			insurance.setCreditCard(creditCard);
			insuranceRepository.save(insurance);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + rules);
		}
		insuranceRepository.save(insurance);
		return insurance;
	}

    private Insurance verifyIfExists(Long id) throws InsuranceNotFoundException {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException(id));
    }

}
