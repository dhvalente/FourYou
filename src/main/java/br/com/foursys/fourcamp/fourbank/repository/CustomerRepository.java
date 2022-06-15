package br.com.foursys.fourcamp.fourbank.repository;

import br.com.foursys.fourcamp.fourbank.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}