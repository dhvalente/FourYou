package br.com.foursys.fourcamp.fourbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.foursys.fourcamp.fourbank.model.Address;


public interface AddressRepository extends JpaRepository<Address, Long>{

}
