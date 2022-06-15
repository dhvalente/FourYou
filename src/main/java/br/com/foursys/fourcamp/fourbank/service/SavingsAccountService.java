package com.fourcamp.fourcamp22.java.group4.service;

import com.fourcamp.fourcamp22.java.group4.model.SavingsAccount;
import com.fourcamp.fourcamp22.java.group4.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingsAccountService {

    @Autowired
    private SavingsAccountRepository repository;


    public SavingsAccount findById(Integer id) {
        Optional<SavingsAccount> obj = repository.findById(id);
        return obj.get();
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

    private void upData(SavingsAccount entity, SavingsAccount obj) {
        entity.setBank(obj.getBank());
        entity.setNumAgency(obj.getNumAgency());
        entity.setNumber(obj.getNumber());

    }
}