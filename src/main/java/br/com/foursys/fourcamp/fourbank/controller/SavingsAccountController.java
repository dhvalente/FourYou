package br.com.foursys.fourcamp.fourbank.controller;


import br.com.foursys.fourcamp.fourbank.model.SavingsAccount;
import br.com.foursys.fourcamp.fourbank.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        Optional<SavingsAccount> obj = service.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(obj.get());
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