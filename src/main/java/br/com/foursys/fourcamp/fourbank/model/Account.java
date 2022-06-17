package br.com.foursys.fourcamp.fourbank.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_account")
public abstract class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String number;
	protected Integer numAgency;
	protected String bank;
	protected Double balance;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	protected Customer customer;

	@OneToMany
	protected Set<Pix> pix = new HashSet<>();

	@OneToMany(mappedBy = "account")
	protected Set<Card> card = new HashSet<>();

}
