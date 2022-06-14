package br.com.foursys.fourcamp.fourbank.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.repository.CreditCardRepository;

@Service
public class CreditCardService {
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	public CreditCard insert(CreditCard obj) {
		return creditCardRepository.save(obj);
	}

	public List<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}

	public CreditCard findById(Long id) {
		Optional<CreditCard> obj = creditCardRepository.findById(id);
		return obj.get();
	}
	
	public void delete(Long id) {
			creditCardRepository.deleteById(id);
	}
	
	public CreditCard update(Long id, CreditCard obj) {
		CreditCard entity = creditCardRepository.getReferenceById(id);
		updateData(entity, obj);
		return creditCardRepository.save(entity);
	}

	private void updateData(CreditCard entity, CreditCard obj) {
		//entity.setName(obj.getName());
		
	}
	
	public void updateStatus(Long id, boolean isActive) {
		Optional<CreditCard> obj = creditCardRepository.findById(id);
		obj.get().setActive(isActive);

	}
}