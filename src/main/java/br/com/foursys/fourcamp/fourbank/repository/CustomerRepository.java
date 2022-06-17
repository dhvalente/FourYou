package br.com.foursys.fourcamp.fourbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foursys.fourcamp.fourbank.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
