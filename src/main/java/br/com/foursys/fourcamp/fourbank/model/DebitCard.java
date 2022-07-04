package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber;
import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber.Label;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_debit_card")
@PrimaryKeyJoinColumn(name = "id_card")
public class DebitCard extends Card implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double limitByTransaction;


	



}
