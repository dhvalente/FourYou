package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.CreditLimitInsufficientException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.repository.DebitCardRepository;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class DebitCardService {

	private DebitCardRepository debitCardRepository;

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
		return MessageResponseDTO.builder()
				.message(s + "DebitCard com a id " + id)
				.build();
	}

	private DebitCard verifyIfExists(Long id) throws CardNotFoundException {
		return debitCardRepository.findById(id)
				.orElseThrow(() -> new CardNotFoundException(id));
	}

	private DebitCard debitCardIsValid(DebitCard debitCard) {
		//validações
		return debitCard;
	}

	public DebitCard findById(Long id) throws CardNotFoundException {
		return verifyIfExists(id);
	}
	
	public DebitCard findByNumber(String number) throws CardNotFoundException {
		return debitCardRepository.findByNumber(number);
	}
	
	public void discountDebitDayLimit(Double valor, Long id) throws CardNotFoundException{
		DebitCard debitCard = verifyIfExists(id);
		Double currentMonthValueTransaction = verifyDebitDayLimit();
		Double debitLimit = debitCard.getLimitByTransaction();	
		Double currentLimit = debitLimit - currentMonthValueTransaction ;
		if(valor > currentLimit) {
			throw new CreditLimitInsufficientException();
		} 
	} 		
	//todo arrumar import de Transaction quando tiver a classe.
	public Double verifyDebitDayLimit() {
		int currentDay = LocalDateTime.now().getDayOfMonth();
		Double currentDayValueTransaction = 0.00;
		List <Transaction> list = transactionRepository.findAll();		
		list.forEach((transaction) -> {
			int dayOfTransaction = transaction.getDate().getDayOfMonth();
			if(dayOfTransaction == currentDay && transaction.getPaymentTypeEnum() == PaymentTypeEnum.CREDIT) {
				currentDayValueTransaction += transaction.getValue();
			}
		});
		return currentDayValueTransaction;
	}
	
	public DebitCard updateStatus(String status , Long id) throws CardNotFoundException {
		DebitCard debitCard = verifyIfExists(id);
		if(status.equals("ativo")) {
			debitCard.setActive(true);
			debitCardRepository.save(debitCard);
		} else if (status.equals("desativado")){
			debitCard.setActive(false);
			debitCardRepository.save(debitCard);
		}
		
		return debitCardRepository.save(debitCard);
	}
	
	public DebitCard updateLimitByTransaction(Double limit , Long id) throws CardNotFoundException {
		DebitCard debitCard = verifyIfExists(id);	
			debitCard.setLimitByTransaction(limit);
		return debitCardRepository.save(debitCard);
	}

}
