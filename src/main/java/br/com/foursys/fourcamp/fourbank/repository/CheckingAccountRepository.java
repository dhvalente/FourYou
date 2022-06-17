package br.com.foursys.fourcamp.fourbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.foursys.fourcamp.fourbank.model.CheckingAccount;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer> {

}
