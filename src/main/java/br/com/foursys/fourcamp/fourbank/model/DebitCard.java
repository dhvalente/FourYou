package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber;
import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber.Label;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_debit_card")
public class DebitCard implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	protected String number = generateNumber("VISA");
	protected String customerCardName;
	protected String password;
	protected String expireDate;
	protected String label;
	protected Integer cvv;
	protected boolean isActive = false;
	@ManyToOne
	@JoinColumn(name="tb_account", referencedColumnName = "id")
	protected CheckingAccount account;
	private Double limitByTransaction;
	
	
	public String generateNumber(String label) {
        GenerateCardNumber.LABEL = Label.getLabel(label);
        String[] creditcardnumbers = GenerateCardNumber.generateCardNumbers();
		return this.number = creditcardnumbers[0];
	}
	



}
