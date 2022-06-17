package br.com.foursys.fourcamp.fourbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.foursys.fourcamp.fourbank.model.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {
}
