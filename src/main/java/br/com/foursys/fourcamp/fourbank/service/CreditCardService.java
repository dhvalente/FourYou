package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CreditLimitInsufficientException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.Transaction;
import br.com.foursys.fourcamp.fourbank.repository.CreditCardRepository;
import br.com.foursys.fourcamp.fourbank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class CreditCardService {
	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public CreditCardService(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}

	public MessageResponseDTO createCreditCard(CreditCard creditCard) {
		// Regra de negocio correta, falta instanciar customer e account
		// setCreditLimit(creditCard);
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
		return MessageResponseDTO.builder().message(s + "CreditCard com a id " + id).build();
	}

	private CreditCard verifyIfExists(Long id) throws CardNotFoundException {
		return creditCardRepository.findById(id).orElseThrow(() -> new CardNotFoundException(id));
	}

	private CreditCard creditCardIsValid(CreditCard creditCard) {
		// validações
		return creditCard;
	}

	public CreditCard findById(Long id) throws CardNotFoundException {
		return verifyIfExists(id);
	}

	public CreditCard findByNumber(String number) throws CardNotFoundException {
		return creditCardRepository.findByNumber(number);
	}

	public boolean assertCreditCardStillHasLimit(Double transactionValue, Long creditCardId) throws CardNotFoundException,
			CreditLimitInsufficientException {
		CreditCard creditCard = verifyIfExists(creditCardId);
		Double currentMonthValueTransaction = verifyMonthCreditLimit();
		Double creditLimit = creditCard.getCreditLimit();
		Double currentLimit = creditLimit - currentMonthValueTransaction;
		if (transactionValue > currentLimit) {
			throw new CreditLimitInsufficientException();
		}
		return true;
	}

	public Double verifyMonthCreditLimit() {
		Month currentMonth = LocalDateTime.now().getMonth();
		Double currentMonthValueTransaction = 0.00;
		List<Transaction> list = transactionRepository.findAll();
		for (Transaction transaction : list) {
			Month monthTransaction = transaction.getDate().getMonth();
			if (monthTransaction == currentMonth && transaction.getType() == PaymentTypeEnum.CREDIT) {
				currentMonthValueTransaction += transaction.getValue();
			}
		}
		return currentMonthValueTransaction;
	}

	public CreditCard updateStatus(String status, Long id) throws CardNotFoundException {
		CreditCard creditCard = verifyIfExists(id);
		if (status.equals("ativo")) {
			creditCard.setActive(true);
			creditCardRepository.save(creditCard);
		} else if (status.equals("desativado")) {
			creditCard.setActive(false);
			creditCardRepository.save(creditCard);
		}
		return creditCardRepository.save(creditCard);
	}

	public void setCreditLimit(CreditCard creditCard) {
		Double income = creditCard.getAccount().getCustomer().getIncome();
		Double limit = income * 2;
		creditCard.setCreditLimit(limit);
	}


}
