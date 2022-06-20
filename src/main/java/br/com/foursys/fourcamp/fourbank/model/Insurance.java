package br.com.foursys.fourcamp.fourbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_insurance")
public class Insurance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@NonNull
	private String rules;
	
	private Date date = new Date();

	@ManyToOne
	@JoinColumn(name="tb_creditcard_id", nullable=false)
	private CreditCard creditCard;
	
	@OneToOne
	private Policy policy;
	

}
