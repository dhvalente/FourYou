package br.com.foursys.fourcamp.fourbank.model;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_checking_account")
public class CheckingAccount extends Account implements Serializable{
	private static final long serialVersionUID = 1L;


	@OneToMany
	protected Set<Pix> pix = new HashSet<>();
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	protected Set<CreditCard> creditCard = new HashSet<>();
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	protected Set<DebitCard> DebitCard = new HashSet<>();
	private Double maintenanceFee;


}
