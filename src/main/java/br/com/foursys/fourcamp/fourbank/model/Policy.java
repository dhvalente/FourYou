package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.*;

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

