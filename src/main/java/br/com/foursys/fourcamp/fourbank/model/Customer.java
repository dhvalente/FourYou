package br.com.foursys.fourcamp.fourbank.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.*;
import br.com.foursys.fourcamp.fourbank.enums.CustomerType;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_customer")
public class Customer {
	private Integer id;
	private String cpf;
	private String name;
	private LocalDateTime dateOfBirth;
	private CustomerType type;
	private Address address;
	
	
}
