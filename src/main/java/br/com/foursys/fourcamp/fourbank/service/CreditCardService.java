package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.dto.UpdatePasswordDTO;
import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.exceptions.AccountNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNumberNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CreditLimitInsufficientException;
import br.com.foursys.fourcamp.fourbank.exceptions.InvalidParametersException;
import br.com.foursys.fourcamp.fourbank.model.*;
import br.com.foursys.fourcamp.fourbank.repository.CheckingAccountRepository;
import br.com.foursys.fourcamp.fourbank.repository.CreditCardRepository;
import br.com.foursys.fourcamp.fourbank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {
	@Autowired
	private CheckingAccountRepository checkingAccountRepository;

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private InsuranceService insuranceService;

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
		setCreditLimit(creditCard);
		return creditCardRepository.save(creditCard);
	}

	private MessageResponseDTO createMessageResponse(Long id, String s) {
		return MessageResponseDTO.builder().message(s + "CreditCard com a id " + id).build();
	}

	private CreditCard verifyIfExists(Long id) throws CardNotFoundException {
		return creditCardRepository.findById(id).orElseThrow(() -> new CardNotFoundException(id));
	}

	private CreditCard creditCardIsValid(CreditCard creditCard) {
		return creditCard;
	}

	public CreditCard findById(Long id) throws CardNotFoundException {
		return verifyIfExists(id);
	}

	public CreditCard findByNumber(String number) throws CardNumberNotFoundException {
		CreditCard card = creditCardRepository.findByNumber(number);
		if(card == null) {
			throw new CardNumberNotFoundException(number);
		}
		return card;
	}

	public boolean assertCreditCardStillHasLimit(Double transactionValue, Long creditCardId)
			throws CardNotFoundException, CreditLimitInsufficientException {
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

	public CreditCard updateStatus(String status, Long id) throws CardNotFoundException, InvalidParametersException {
		CreditCard creditCard = verifyIfExists(id);
		if (status.equals("ativo")) {
			creditCard.setActive(true);
			creditCardRepository.save(creditCard);
		}
		else if (status.equals("inativo")) {
			creditCard.setActive(false);
			creditCardRepository.save(creditCard);
		} 
		else {
			throw new InvalidParametersException();
		}
		return creditCardRepository.save(creditCard);
	}

	public void setCreditLimit(CreditCard creditCard) {
		Optional<CheckingAccount> checkingAccount = checkingAccountRepository.findById(creditCard.getAccount().getId());
		checkingAccount.get();
		Double income = checkingAccount.get().getCustomer().getIncome();
		Double limit = income * 2;
		creditCard.setCreditLimit(limit);
	}

	public int createInsuranceAndGeneratePolicyNumber(Long id, String rules, Insurance insurance)
			throws CardNotFoundException {
		CreditCard creditCard = verifyIfExists(id);
		insuranceService.registerInsurance(rules, creditCard);
		return insurance.getPolicy().getPolicyNumber();
	}

	public CreditCard updatePassword(Long id, UpdatePasswordDTO password) throws CardNotFoundException {
		CreditCard creditCard = verifyIfExists(id);
		creditCard.setPassword(password.getPassword());
		return creditCardRepository.save(creditCard);
	}

}
