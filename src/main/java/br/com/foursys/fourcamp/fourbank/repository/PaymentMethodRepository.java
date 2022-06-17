package br.com.foursys.fourcamp.fourbank.repository;

import br.com.foursys.fourcamp.fourbank.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
