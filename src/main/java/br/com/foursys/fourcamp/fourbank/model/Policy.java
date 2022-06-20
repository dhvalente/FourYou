package br.com.foursys.fourcamp.fourbank.model;

import lombok.*;

import javax.persistence.*;
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
    @Column(name = "nrpolicy", nullable = false)
    private Long id;

    @ManyToOne
    private CreditCard creditCard;
    @OneToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    @NonNull
    private Double policyValue;
    @NonNull
    private String termsDescription;
    
    private Integer policyNumber;
    
    public Integer generatePolicyNumber() {
    	Integer policyNumber = new Random().nextInt(999999999);
    	return policyNumber;
    }

}

