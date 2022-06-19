package br.com.foursys.fourcamp.fourbank.repository;

import br.com.foursys.fourcamp.fourbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionAccountRepository extends JpaRepository<Transaction, Integer> {
}
