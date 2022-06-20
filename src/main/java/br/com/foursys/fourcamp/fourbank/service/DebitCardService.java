package br.com.foursys.fourcamp.fourbank.service;


import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CreditLimitInsufficientException;
import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.model.Transaction;
import br.com.foursys.fourcamp.fourbank.repository.DebitCardRepository;
import br.com.foursys.fourcamp.fourbank.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DebitCardService {
	@Autowired
	private DebitCardRepository debitCardRepository;
	
	
	//private DebitCardMapper debitCardMapper = DebitCardMapper.INSTANCE;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	public DebitCardService(DebitCardRepository debitCardRepository) {
		this.debitCardRepository = debitCardRepository;
	}

	public MessageResponseDTO createDebitCard(DebitCard debitCard) {
		DebitCard savedDebitCard = getDebitCard(debitCard);
		return createMessageResponse(savedDebitCard.getId(), "Created ");
	}

	public List<DebitCard> listAll() {
		return debitCardRepository.findAll();
	}

	public MessageResponseDTO updateById(Long id, DebitCard debitCard) throws CardNotFoundException {
		verifyIfExists(id);
		DebitCard validDebitCard = debitCardIsValid(debitCard);
		DebitCard updatedDebitCard = getDebitCard(validDebitCard);
		return createMessageResponse(updatedDebitCard.getId(), "Updated ");
	}

	public void delete(Long id) throws CardNotFoundException {
		verifyIfExists(id);
		debitCardRepository.deleteById(id);
	}

	private DebitCard getDebitCard(DebitCard debitCard) {
		DebitCard validDebitCard = debitCardIsValid(debitCard);
		return debitCardRepository.save(debitCard);
	}

	private MessageResponseDTO createMessageResponse(Long id, String s) {
		return MessageResponseDTO.builder().message(s + "DebitCard com a id " + id).build();
	}

	private DebitCard verifyIfExists(Long id) throws CardNotFoundException {
		return debitCardRepository.findById(id).orElseThrow(() -> new CardNotFoundException(id));
	}

	private DebitCard debitCardIsValid(DebitCard debitCard) {
		// validações
		return debitCard;
	}

	public DebitCard findById(Long id) throws CardNotFoundException {
		return verifyIfExists(id);
	}

	public DebitCard findByNumber(String number) throws CardNotFoundException {
		return debitCardRepository.findByNumber(number);
	}

	public Boolean assertDebitCardHasLimit(Double transactionValue, Long cardId) throws CardNotFoundException,
			CreditLimitInsufficientException {
		DebitCard debitCard = verifyIfExists(cardId);
		Double currentMonthValueTransaction = verifyDebitDayLimit();
		Double debitLimit = debitCard.getLimitByTransaction();
		Double currentLimit = debitLimit - currentMonthValueTransaction;
		if (transactionValue > currentLimit) {
			throw new CreditLimitInsufficientException();
		}
		return true;
	}

	public Double verifyDebitDayLimit() {
		int currentDay = LocalDateTime.now().getDayOfMonth();
		Double currentDayValueTransaction = 0.00;
		List<Transaction> list = transactionRepository.findAll();
		for (Transaction transaction : list) {
			int dayOfTransaction = transaction.getDate().getDayOfMonth();
			if (dayOfTransaction == currentDay && transaction.getType() == PaymentTypeEnum.CREDIT) {
				currentDayValueTransaction += transaction.getValue();
			}
		}
		return currentDayValueTransaction;
	}

	public DebitCard updateStatus(String status, Long id) throws CardNotFoundException {
		DebitCard debitCard = verifyIfExists(id);
		if (status.equals("ativo")) {
			debitCard.setActive(true);
			debitCardRepository.save(debitCard);
		} else if (status.equals("desativado")) {
			debitCard.setActive(false);
			debitCardRepository.save(debitCard);
		}

		return debitCardRepository.save(debitCard);
	}

	public DebitCard updateLimitByTransaction(Double limit, Long id) throws CardNotFoundException {
		DebitCard debitCard = verifyIfExists(id);
		debitCard.setLimitByTransaction(limit);
		return debitCardRepository.save(debitCard);
	}
	
	public DebitCard updatePassword(Long id, String password) throws CardNotFoundException {
		DebitCard debitCard = verifyIfExists(id);
		debitCard.setPassword(password);
		return debitCardRepository.save(debitCard);
	}

}
