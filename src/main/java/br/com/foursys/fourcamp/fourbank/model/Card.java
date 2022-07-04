package br.com.foursys.fourcamp.fourbank.model;

import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@Entity
@Table(name = "tb_card")
public class Card {

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

    public String generateNumber(String label) {
        GenerateCardNumber.LABEL = GenerateCardNumber.Label.getLabel(label);
        String[] creditcardnumbers = GenerateCardNumber.generateCardNumbers();
        return this.number = creditcardnumbers[0];
    }
}
