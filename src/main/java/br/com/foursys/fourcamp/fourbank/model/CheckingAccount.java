package br.com.foursys.fourcamp.fourbank.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance
public class CheckingAccount implements Serializable{
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
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	protected Set<CreditCard> creditCard = new HashSet<>();
	
	@OneToMany(mappedBy = "account")
	protected Set<DebitCard> DebitCard = new HashSet<>();
	private Double maintenanceFee;


}
