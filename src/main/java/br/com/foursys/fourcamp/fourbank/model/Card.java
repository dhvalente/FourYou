package br.com.foursys.fourcamp.fourbank.model;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
abstract class Card {

	protected String number;
	protected String customerCardName;
	protected String password;
	protected String expireDate;
	protected String flag;
	protected Integer cvv;
	protected boolean isActive = false;
	//@ManyToOne
	//@JoinColumn(name="tb_account", referencedColumnName = "id")
	//protected Account account;
}
