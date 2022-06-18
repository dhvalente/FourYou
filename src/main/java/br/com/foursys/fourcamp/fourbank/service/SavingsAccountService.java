package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.model.SavingsAccount;
import br.com.foursys.fourcamp.fourbank.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class SavingsAccountService {

    @Autowired
    private SavingsAccountRepository repository;


    public Optional<SavingsAccount> findById(Integer id) {
        return repository.findById(id);
    }

    public List<SavingsAccount> findAll() {

        return repository.findAll();
    }

    public SavingsAccount save(SavingsAccount obj) {
        return repository.save(obj);
    }

    public SavingsAccount update(Integer id, SavingsAccount obj) {
        SavingsAccount entity = repository.getOne(id);
        upData(entity, obj);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);

    }
    
    public void yield(SavingsAccount savings) {
    	if(savings.getBalance() > 00.00) {
    		savings.setYieldRate(1.0022);
    	}
    }
    
    public void addYield(SavingsAccount savings) {
    	Calendar calendar = Calendar.getInstance();
    	if(Calendar.HOUR_OF_DAY == calendar.get(Calendar.HOUR_OF_DAY)) {
    		savings.setBalance(savings.getBalance() * savings.getYieldRate());
    	}
    }

    private void upData(SavingsAccount entity, SavingsAccount obj) {
        entity.setBank(obj.getBank());
        entity.setNumAgency(obj.getNumAgency());
        entity.setNumber(obj.getNumber());

    }
}