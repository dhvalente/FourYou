package br.com.foursys.fourcamp.fourbank.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber;
import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber.Label;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
@Table(name = "tb_credit_card")
public class CreditCard extends Card implements Serializable{
	private static final long serialVersionUID = 1L;

	private Double creditLimit;	

}
