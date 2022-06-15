package com.fourcamp.fourcamp22.java.group4.model;
package br.com.foursys.fourcamp.fourbank.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

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
