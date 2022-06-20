package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.model.PhoneRecharge;
import br.com.foursys.fourcamp.fourbank.repository.PhoneRechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneRechargeService {

    @Autowired
    private PhoneRechargeRepository phoneRechargeRepository;
    @Autowired
    private TransactionService transactionService;

    public Object phoneRecharge(PhoneRecharge phoneRecharge) {
        transactionService.phoneRecharge(phoneRecharge);
        return phoneRechargeRepository.save(phoneRecharge);
    }
}
