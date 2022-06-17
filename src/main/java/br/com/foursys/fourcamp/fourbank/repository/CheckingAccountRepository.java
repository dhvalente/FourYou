package br.com.foursys.fourcamp.fourbank.repository;


import br.com.foursys.fourcamp.fourbank.model.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer> {

}
