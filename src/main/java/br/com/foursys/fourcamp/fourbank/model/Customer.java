package br.com.foursys.fourcamp.fourbank.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.*;
import br.com.foursys.fourcamp.fourbank.enums.CustomerTypeEnum;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_customer")
public class Customer {
	private Long id;
	private String name;
	private LocalDateTime dateOfBirth;
	private String cpf;
	private String nCell;
	private Double income;
	private String Email;
	private Address address;
	private String password;
	private CustomerTypeEnum type;
	private String imgUrl;
	
	
	
}
