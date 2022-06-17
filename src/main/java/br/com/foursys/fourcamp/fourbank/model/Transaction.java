package br.com.foursys.fourcamp.fourbank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String identifier;
	private Double value;
	private PaymentTypeEnum type;
	
	@OneToOne
	private Account account;
	
	@OneToOne
	private Account originAccount;
	
	@OneToOne
	private Account destinationAccount;
	

	

}
