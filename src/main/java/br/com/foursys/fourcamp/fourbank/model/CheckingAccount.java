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
public class CheckingAccount extends Account{

	private Double maintenanceFee;
	
	public Double discountFee(Double balance, Double maintenanceFee) {
		Double fee;
		this.balance -= maintenanceFee;
		return this.balance;
	}
}
