package br.com.foursys.fourcamp.fourbank.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SavingsAccount extends Account{

	
	private Double yieldRate;
	
	
	public Double addIncome(Double balance, Double yieldRate) {
	
		this.balance += balance + yieldRate;
		return this.balance;
	}
	
}
