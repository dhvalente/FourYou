package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber;
import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber.Label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_credit_card")
@PrimaryKeyJoinColumn(name = "id_card")
public class CreditCard extends Card implements Serializable{
	private static final long serialVersionUID = 1L;

	private Double creditLimit;
	@JsonIgnore
	@OneToMany(mappedBy = "creditCard")
	private List<Insurance> insuranceProducts = new ArrayList<Insurance>();
	


}
