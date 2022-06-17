package br.com.foursys.fourcamp.fourbank.service;



import java.util.List;

import org.springframework.stereotype.Service;

import br.com.foursys.fourcamp.fourbank.dto.response.TransactionMessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.TransactionNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Transaction;
import br.com.foursys.fourcamp.fourbank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TransactionService {
	

	private TransactionRepository repository;
	
	public TransactionMessageResponseDTO createTransaction(Transaction transaction) {
		Transaction savedtransaction = getTransaction(transaction);
		return createMessageResponse(savedtransaction.getId(), "Criado ");
	}
	
	public TransactionMessageResponseDTO updateById(Long id, Transaction transaction) throws TransactionNotFoundException {
		verifyIfExists(id);
		Transaction updatetransaction = getTransaction(transaction);
		return createMessageResponse(updatetransaction.getId(), "Atualizado");
	}
	
	private TransactionMessageResponseDTO createMessageResponse(Integer id, String s) {
		return TransactionMessageResponseDTO.builder()
				.message(s + "Cliente com o id" + id)
				.build();
	}
	
	public List<Transaction> listAll(){
		List<Transaction> alltransactions = repository.findAll();		
		return alltransactions;
		
	}
	
	public void delete(Long id) throws TransactionNotFoundException {
		verifyIfExists(id);
		repository.deleteById(id);
	}
	
	private Transaction verifyIfExists(Long id) throws TransactionNotFoundException{
		return repository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException(id));
	}
	
	private Transaction getTransaction(Transaction transaction) {
		return repository.save(transaction);
	}
	
	
	
	public Transaction findById(Long id) throws TransactionNotFoundException{
		Transaction transaction = verifyIfExists(id);
		return transaction;
	}
}
