package com.fourcamp.fourbank.controller;

import com.fourcamp.fourbank.dto.response.MessageResponseDTO;
import com.fourcamp.fourbank.exceptions.InsuranceNotFoundException;
import com.fourcamp.fourbank.model.Insurance;
import com.fourcamp.fourbank.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private final InsuranceService insuranceService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createInsurance(@RequestBody Insurance insurance) {
        return insuranceService.createInsurance(insurance);
    }

    @GetMapping
    public List<Insurance> listAll() {
        return insuranceService.listAll();
    }

    @GetMapping("/{id}")
    public Insurance findById(@PathVariable Long id) throws InsuranceNotFoundException {
        return insuranceService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody Insurance insurance)
            throws InsuranceNotFoundException {
        return insuranceService.updateById(id, insurance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws InsuranceNotFoundException {
        insuranceService.delete(id);
    }
}