package br.com.foursys.fourcamp.fourbank.controller;


import br.com.foursys.fourcamp.fourbank.exceptions.AccountNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.SavingsAccount;
import br.com.foursys.fourcamp.fourbank.service.SavingsAccountService;
import br.com.foursys.fourcamp.fourbank.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> findById(@PathVariable Integer id) throws AccountNotFoundException{
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch(AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseModel(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SavingsAccount> save(@RequestBody SavingsAccount obj) {
    	service.yield(obj);
    	service.addYield(obj);
        obj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> delete(@PathVariable Integer id) throws AccountNotFoundException{
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseModel(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody SavingsAccount obj) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, obj));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }
}