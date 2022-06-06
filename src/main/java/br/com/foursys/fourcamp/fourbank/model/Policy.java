package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="policies")
public class Policy {

    @Id
    @Column(name = "nrpolicy", nullable = false)
    private Integer nrPolicy;

    private CreditCard creditCard;

    private Insurance insurance;

    private Double policyValue;

    private String termsDescription;

    public Integer generatePolicyNumber(CreditCard creditCard, Double policyValue, String termsDescription) {
        //todo
        return 0;
    }

}
