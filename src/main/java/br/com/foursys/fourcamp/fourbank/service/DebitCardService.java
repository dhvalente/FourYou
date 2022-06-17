package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.CardNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.repository.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
