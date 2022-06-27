package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.exceptions.InvalidParametersException;
import br.com.foursys.fourcamp.fourbank.exceptions.PixNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Pix;
import br.com.foursys.fourcamp.fourbank.service.PixService;
import br.com.foursys.fourcamp.fourbank.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private final PixService pixService;

    @Autowired
    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createPix(@RequestBody Pix pix) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(pixService.createPix(pix));
        } catch (InvalidParametersException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseModel(HttpStatus.NOT_ACCEPTABLE,
                    HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));
        }
    }

    @GetMapping
    public List<Pix> listAll() {
        return pixService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(pixService.findById(id));
        } catch (PixNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable String id, @RequestBody Pix pix) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(pixService.updateById(id, pix));
        } catch (PixNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (InvalidParametersException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseModel(HttpStatus.NOT_ACCEPTABLE,
                    HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id) {
        try {
            pixService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Pix descadastrado com sucesso!");
        } catch (PixNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

}