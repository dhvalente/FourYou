package br.com.foursys.fourcamp.fourbank.model;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Random;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_policy")
public class Policy implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CreditCard creditCard;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    @NonNull
    private Double policyValue;
    @NonNull
    private String termsDescription;
    
    private Integer policyNumber = generatePolicyNumber();
    
	public Policy(CreditCard creditCard, Insurance insurance, @NonNull Double policyValue,
			@NonNull String termsDescription) {
		super();
		this.creditCard = creditCard;
		this.insurance = insurance;
		this.policyValue = policyValue;
		this.termsDescription = termsDescription;
	}
    
    public static Integer generatePolicyNumber() {
    	Integer policyNumber = new Random().nextInt(999999999);
    	return policyNumber;
    }




}

