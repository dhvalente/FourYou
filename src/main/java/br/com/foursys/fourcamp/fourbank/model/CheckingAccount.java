package com.fourcamp.fourcamp22.java.group4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckingAccount extends Account{

	private Double maintenanceFee;


}
