package br.com.foursys.fourcamp.fourbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.foursys.fourcamp.fourbank.model.CheckingAccount;
import br.com.foursys.fourcamp.fourbank.service.CheckingAccountService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/checkingaccount")
public class CheckingAccountController {

    @Autowired
    private CheckingAccountService service;

    @GetMapping
    public ResponseEntity<List<CheckingAccount>> findAll() {
        List<CheckingAccount> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CheckingAccount> findById(@PathVariable Integer id) {
        CheckingAccount obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<CheckingAccount> save(@RequestBody CheckingAccount obj) {
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
    public ResponseEntity<CheckingAccount> update(@PathVariable Integer id, @RequestBody CheckingAccount obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
