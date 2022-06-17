package br.com.foursys.fourcamp.fourbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foursys.fourcamp.fourbank.model.CheckingAccount;
import br.com.foursys.fourcamp.fourbank.repository.CheckingAccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CheckingAccountService {

    @Autowired
    private CheckingAccountRepository repository;


    public CheckingAccount findById(Integer id) {
        Optional<CheckingAccount> obj = repository.findById(id);
        return obj.get();
    }

    public List<CheckingAccount> findAll() {

        return repository.findAll();
    }

    public CheckingAccount save(CheckingAccount obj) {
        return repository.save(obj);
    }

    public CheckingAccount update(Integer id, CheckingAccount obj) {
        CheckingAccount entity = repository.getOne(id);
        upData(entity, obj);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);

    }

    private void upData(CheckingAccount entity, CheckingAccount obj) {
        entity.setBank(obj.getBank());
        entity.setNumAgency(obj.getNumAgency());
        entity.setNumber(obj.getNumber());

    }
}