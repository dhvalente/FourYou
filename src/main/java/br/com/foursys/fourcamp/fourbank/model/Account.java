package br.com.foursys.fourcamp.fourbank.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@Entity
@Table(name = "tb_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String number;
    protected Integer numAgency;
    protected String bank;
    protected Double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    protected Customer customer;

}
