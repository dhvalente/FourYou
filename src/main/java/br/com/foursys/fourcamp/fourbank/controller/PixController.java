package com.fourcamp.fourbank.controller;


import com.fourcamp.fourbank.dto.response.MessageResponseDTO;
import com.fourcamp.fourbank.exceptions.PixNotFoundException;
import com.fourcamp.fourbank.model.Pix;
import com.fourcamp.fourbank.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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