package br.com.foursys.fourcamp.fourbank.model;

import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.enums.PhoneProvider;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class PhoneRecharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private PhoneProvider phoneProvider;
    private String phoneNumber;
    private Double valueRecharge;
    private PaymentTypeEnum paymentTypeEnum;

}
