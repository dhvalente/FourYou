package com.fourcamp.fourcamp22.java.group4.model;
package br.com.foursys.fourcamp.fourbank.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_account")
public abstract class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer numAgency;
	protected String bank;
	protected Double balance;

	//@ManyToOne
	//@JoinColumn(name = "costumer_id")
	//protected Costumer costumer;

	//@ManyToOne
	//protected Set<Pix> pix = new HashSet<>();

	//@OneToMany
	//protected Set<Card> card = new HashSet<>();

	//@OneToMany
	//protected Set<PaymentMethods> paymentMethod = new HashSet<>();
  
	protected Double balance;

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "cliente_id") protected Costumer costumer;
	 * 
	 * @OneToMany(mappedBy = "conta") private Set<Card> cards = new HashSet<>();
	 * 
	 * @OneToMany(mappedBy = "conta") private Set<Pix> pix = new HashSet<>();
	 */
}
