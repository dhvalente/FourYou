package br.com.foursys.fourcamp.fourbank.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_policy")
public class Policy {
	private Integer numPolicy;
	private CreditCard creditCard;
	private Insurance insurance;
	private Double policyValue;
	private String conditions;
	
}
