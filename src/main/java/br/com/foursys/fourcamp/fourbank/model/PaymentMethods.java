package br.com.foursys.fourcamp.fourbank.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_transaction")
public class PaymentMethods {
	private Integer id;
	private PaymentTypeEnum type;
	private String identifier;
	private Account account;
	private Account originAccount;
	private Account destinationAccount;
	private Double value;
	

}
