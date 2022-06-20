package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.model.PhoneRecharge;
import br.com.foursys.fourcamp.fourbank.service.PhoneRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phonerecharge")
public class PhoneRechargeController {

    @Autowired
    private PhoneRechargeService phoneRechargeService;

    @PostMapping
    public ResponseEntity<Object> phoneRecharge(@RequestBody PhoneRecharge phoneRecharge) {
        PhoneRecharge phoneRecharges = new PhoneRecharge();
        phoneRecharges.setPhoneProvider(phoneRecharge.getPhoneProvider());
        phoneRecharges.setPhoneNumber(phoneRecharge.getPhoneNumber());
        phoneRecharges.setValueRecharge(phoneRecharge.getValueRecharge());
        phoneRecharges.setPaymentTypeEnum(phoneRecharge.getPaymentTypeEnum());

        return ResponseEntity.status(HttpStatus.CREATED).body(phoneRechargeService.phoneRecharge(phoneRecharge));
    }
}
