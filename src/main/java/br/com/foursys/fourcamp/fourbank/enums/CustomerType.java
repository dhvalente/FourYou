package br.com.foursys.fourcamp.fourbank.enums;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CustomerType {
	STANDARD(0),
	SUPER(1),
	PREMIUM(2);
	
	private Integer type;
	
	
}
