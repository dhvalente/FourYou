package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.exceptions.*;
import br.com.foursys.fourcamp.fourbank.model.Transaction;
import br.com.foursys.fourcamp.fourbank.service.TransactionService;
import br.com.foursys.fourcamp.fourbank.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentMethod")
public class TransactionController {

    @Autowired
    private final TransactionService paymentMethodService;

    @Autowired
    public TransactionController(TransactionService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody Transaction paymentMethod) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodService.createPaymentMethod(paymentMethod));
        } catch (InvalidParametersException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseModel(HttpStatus.NOT_ACCEPTABLE,
                    HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));
        } catch (UnregisteredPaymentMethodException e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseModel(HttpStatus.FAILED_DEPENDENCY,
                    HttpStatus.FAILED_DEPENDENCY.value(), e.getMessage()));
        } catch (AccountNotFoundException | CardNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (CreditLimitInsufficientException | InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ResponseModel(HttpStatus.PRECONDITION_FAILED,
                    HttpStatus.PRECONDITION_FAILED.value(), e.getMessage()));
        }
    }

    @GetMapping("/listall/{id}")
    public List<Transaction> listAllByAccount(@PathVariable Integer id) {
        return paymentMethodService.listAllByAccount(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) throws TransactionNotFoundException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentMethodService.findById(id));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

}
