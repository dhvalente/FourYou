package br.com.foursys.fourcamp.fourbank.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
import lombok.*;
import br.com.foursys.fourcamp.fourbank.enums.CustomerTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String dateOfBirth;
	private String cpf;
	private String nCell;
	private Double income;
	private String Email;
	private String password;
	private CustomerTypeEnum type;
	private String imgUrl;
	private Integer age;
	private String momName;
	private String dadName;
	private String rg;
	private String landline;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Address address;

	public Customer(Long id, String name, String dateOfBirth, String cpf, String nCell, Double income, String email,
			String password, CustomerTypeEnum type, String imgUrl) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.cpf = cpf;
		this.nCell = nCell;
		this.income = income;
		Email = email;
		this.password = password;
		this.type = type;
		this.imgUrl = imgUrl;
		this.age = setAge(dateOfBirth);
	}

	public Integer setAge(String dateOfBirth) {
		DateTimeFormatter inputFormatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateOfBirthFormated = LocalDate.parse(dateOfBirth, inputFormatData);
		LocalDateTime today = LocalDateTime.now();
		if (dateOfBirthFormated.getMonthValue() > today.getMonthValue()) {
			Integer age = today.getYear() - dateOfBirthFormated.getYear();
			return age;
		} else {
			Integer age = today.getYear() - dateOfBirthFormated.getYear() - 1;
			return age;
		}
	}

}
