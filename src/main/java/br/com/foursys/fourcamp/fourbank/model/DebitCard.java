package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance
@Table(name = "tb_debit_card")
public class DebitCard extends Card implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double limitByTransaction;

}
