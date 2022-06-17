package br.com.foursys.fourcamp.fourbank.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance
public class CheckingAccount extends Account implements Serializable{
	private static final long serialVersionUID = 1L;


	private Double maintenanceFee;


}
