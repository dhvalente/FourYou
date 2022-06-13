package br.com.foursys.fourcamp.fourbank.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_insurance")
public class Insurance {
	private Integer id;
	private String name;
	private String rules;
}
