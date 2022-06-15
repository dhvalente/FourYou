package com.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_policy")
public class Policy {

    @Id
    @Column(name = "nrpolicy", nullable = false)
    private Long id;
    @NonNull
    private CreditCard creditCard;
    //OnetoOne?
    @OneToOne
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    @NonNull
    private Double policyValue;
    @NonNull
    private String termsDescription;

    public Integer generatePolicyNumber(CreditCard creditCard, Double policyValue, String termsDescription) {
        //todo
        return 0;
    }

}
