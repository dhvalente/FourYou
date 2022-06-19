package br.com.foursys.fourcamp.fourbank.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PaymentTypeEnum {
	CREDIT(0),
	DEBIT(1),
	PIX(2),
	TED(3),
	DOC(4),
	DEPOSIT(5),
	WITHDRAW(6),
	TRANSFER(7);
	
	private Integer type;
}