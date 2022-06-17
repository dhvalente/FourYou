package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CreditLimitInsufficientException;
import br.com.foursys.fourcamp.fourbank.exceptions.UptadeStatusInvalidParametersException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {
	@Autowired
	private CreditCardRepository creditCardRepository;
	

	public CreditCardService(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}

	public MessageResponseDTO createCreditCard(CreditCard creditCard) {
		CreditCard savedCreditCard = getCreditCard(creditCard);
		return createMessageResponse(savedCreditCard.getId(), "Created ");
	}

	public List<CreditCard> listAll() {
		return creditCardRepository.findAll();
	}

	public MessageResponseDTO updateById(Long id, CreditCard creditCard) throws CardNotFoundException {
		verifyIfExists(id);
		CreditCard validCreditCard = creditCardIsValid(creditCard);
		CreditCard updatedCreditCard = getCreditCard(validCreditCard);
		return createMessageResponse(updatedCreditCard.getId(), "Updated ");
	}

	public void delete(Long id) throws CardNotFoundException {
		verifyIfExists(id);
		creditCardRepository.deleteById(id);
	}

	private CreditCard getCreditCard(CreditCard creditCard) {
		CreditCard validCreditCard = creditCardIsValid(creditCard);
		return creditCardRepository.save(creditCard);
	}

	private MessageResponseDTO createMessageResponse(Long id, String s) {
		return MessageResponseDTO.builder()
				.message(s + "CreditCard com a id " + id)
				.build();
	}

	private CreditCard verifyIfExists(Long id) throws CardNotFoundException {
		return creditCardRepository.findById(id)
				.orElseThrow(() -> new CardNotFoundException(id));
	}

	private CreditCard creditCardIsValid(CreditCard creditCard) {
		//validações
		return creditCard;
	}

	public CreditCard findById(Long id) throws CardNotFoundException {
		return verifyIfExists(id);
	}
	
	public void discountCreditLimit(Double valor, Long id){
		Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
		CreditCard creditCard = creditCardOptional.get();
		Double creditLimit = creditCard.getCreditLimit();
		if(valor > creditLimit) {
			throw new CreditLimitInsufficientException();
		} else {
			creditCard.setCreditLimit(creditLimit - valor);
		}
	} 	

	
	
	public CreditCard updateStatus(String status , Long id) {
		Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
		CreditCard creditCard = creditCardOptional.get();
		if(status == "ativo") {
			creditCard.setActive(true);	
		}
		if(status == "desativado") {
			creditCard.setActive(false);
		}
		return creditCardRepository.save(creditCard);
	}

}
