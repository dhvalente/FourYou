package br.com.foursys.fourcamp.fourbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.repository.DebitCardRepository;

@Service
public class DebitCardService {
	@Autowired
	private DebitCardRepository debitCardRepository;
	
	public DebitCard insert(DebitCard obj) {
		return debitCardRepository.save(obj);
	}

	public List<DebitCard> findAll() {
		return debitCardRepository.findAll();
	}

	public DebitCard findById(Long id) {
		Optional<DebitCard> obj = debitCardRepository.findById(id);
		return obj.get();
	}
	
	public void delete(Long id) {
			debitCardRepository.deleteById(id);
	}
	
	public DebitCard update(Long id, DebitCard obj) {
		DebitCard entity = debitCardRepository.getReferenceById(id);
		updateData(entity, obj);
		return debitCardRepository.save(entity);
	}

	private void updateData(DebitCard entity, DebitCard obj) {
		//entity.setName(obj.getName());
		
	}
	
	public void updateStatus(Long id, boolean isActive) {
		Optional<DebitCard> obj = debitCardRepository.findById(id);
		obj.get().setActive(isActive);

	}
}