package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.AccountNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InvalidParametersException;
import br.com.foursys.fourcamp.fourbank.exceptions.PaymentNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.UnregisteredPaymentMethodException;
import br.com.foursys.fourcamp.fourbank.model.PaymentMethod;
import br.com.foursys.fourcamp.fourbank.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentMethod")
public class PaymentMethodController {

    @Autowired
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPaymentMethod(@RequestBody PaymentMethod paymentMethod) throws
            UnregisteredPaymentMethodException, InvalidParametersException, AccountNotFoundException {
        return paymentMethodService.createPaymentMethod(paymentMethod);
    }

    @GetMapping
    public List<PaymentMethod> listAllByAccount(Integer accountId) {
        return paymentMethodService.listAllByAccount(accountId);
    }

    @GetMapping("/{id}")
    public PaymentMethod findById(@PathVariable Long id) throws PaymentNotFoundException {
        return paymentMethodService.findById(id);
    }

}
