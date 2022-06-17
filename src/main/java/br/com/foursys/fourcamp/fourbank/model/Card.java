package br.com.foursys.fourcamp.fourbank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber;
import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Card implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	protected String number = generateNumber("MASTER");
	protected String customerCardName;
	protected String password;
	protected String expireDate;
	protected String label;
	protected Integer cvv;
	protected boolean isActive = false;
	@ManyToOne
	@JoinColumn(name="tb_account", referencedColumnName = "id")
	protected Account account;
	
	
	public String generateNumber(String label) {
        GenerateCardNumber.LABEL = Label.getLabel(label);
        String[] creditcardnumbers = GenerateCardNumber.generateCardNumbers();
		return this.number = creditcardnumbers[0];
	}
	


}
