package br.com.foursys.fourcamp.fourbank.repository;

import br.com.foursys.fourcamp.fourbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
