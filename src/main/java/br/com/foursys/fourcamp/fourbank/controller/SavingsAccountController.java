package com.fourcamp.fourcamp22.java.group4.controller;


import com.fourcamp.fourcamp22.java.group4.model.SavingsAccount;
import com.fourcamp.fourcamp22.java.group4.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

