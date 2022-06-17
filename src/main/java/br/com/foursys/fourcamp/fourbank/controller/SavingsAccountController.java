package br.com.foursys.fourcamp.fourbank.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.foursys.fourcamp.fourbank.model.SavingsAccount;
import br.com.foursys.fourcamp.fourbank.service.SavingsAccountService;

@RestController
@RequestMapping(value = "/savingsaccount")
public class SavingsAccountController {

    @Autowired
    private SavingsAccountService service;

    @GetMapping
    public ResponseEntity<List<SavingsAccount>> findAll() {
        List<SavingsAccount> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SavingsAccount> findById(@PathVariable Integer id) {
        SavingsAccount obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<SavingsAccount> save(@RequestBody SavingsAccount obj) {
    	service.yield(obj);
    	service.addYield(obj);
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SavingsAccount> update(@PathVariable Integer id, @RequestBody SavingsAccount obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}