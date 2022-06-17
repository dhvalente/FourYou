package br.com.foursys.fourcamp.fourbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.PixNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Pix;
import br.com.foursys.fourcamp.fourbank.service.PixService;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private final PixService pixService;

    @Autowired
    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPix(@RequestBody Pix pix) {
        return pixService.createPix(pix);
    }

    @GetMapping
    public List<Pix> listAll() {
        return pixService.listAll();
    }

    @GetMapping("/{id}")
    public Pix findById(@PathVariable String id) throws PixNotFoundException {
        return pixService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable String id, @RequestBody Pix pix)
            throws PixNotFoundException {
        return pixService.updateById(id, pix);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) throws PixNotFoundException {
        pixService.delete(id);
    }
}
