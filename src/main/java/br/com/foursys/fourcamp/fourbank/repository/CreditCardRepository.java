package br.com.foursys.fourcamp.fourbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.DebitCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
	
	public CreditCard findByNumber(String number);
}
