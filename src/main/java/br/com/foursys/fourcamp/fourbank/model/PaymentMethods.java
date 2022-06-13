package br.com.foursys.fourcamp.fourbank.model;

import javax.persistence.Entity;

import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public abstract class PaymentMethods {
	private Integer id;
	private PaymentTypeEnum type;
	private String identifier;
	private Account account;
	private Account originAccount;
	private Account destinationAccount;
	private Double value;
	

	abstract void Transfer();
}
