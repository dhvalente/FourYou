package br.com.foursys.fourcamp.fourbank.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum KeyTypeEnum {

	CPF(0),
	EMAIL(1),
	PHONE(2),
	RANDOM(3);
	
	private Integer type;
	
	
	
}
