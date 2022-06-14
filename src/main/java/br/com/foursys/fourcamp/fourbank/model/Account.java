package com.fourcamp.fourcamp22.java.group4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String number;
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

}
