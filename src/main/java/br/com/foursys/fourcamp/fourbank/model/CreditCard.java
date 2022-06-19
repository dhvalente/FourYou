package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
@Table(name = "tb_credit_card")
public class CreditCard extends Card implements Serializable{
	private static final long serialVersionUID = 1L;

	private Double creditLimit;

	@OneToMany(mappedBy = "creditCard")
	private List<Insurance> insuranceProducts;

	//private List<CreditCardInstallment> creditCardInstallments;

}
