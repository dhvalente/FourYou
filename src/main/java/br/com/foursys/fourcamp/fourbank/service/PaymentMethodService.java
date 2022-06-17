package br.com.foursys.fourcamp.fourbank.service;


import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.AccountNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InvalidParametersException;
import br.com.foursys.fourcamp.fourbank.exceptions.PaymentNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.UnregisteredPaymentMethodException;
import br.com.foursys.fourcamp.fourbank.model.PaymentMethod;
import br.com.foursys.fourcamp.fourbank.repository.CheckingAccountRepository;
import br.com.foursys.fourcamp.fourbank.repository.PaymentMethodRepository;
import br.com.foursys.fourcamp.fourbank.repository.SavingsAccountRepository;
import br.com.foursys.fourcamp.fourbank.util.PaymentMethodValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public MessageResponseDTO createPaymentMethod(PaymentMethod paymentMethod) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException {
        PaymentMethod savedPaymentMethod = getPaymentMethod(paymentMethod);
        return createMessageResponse(savedPaymentMethod.getId(), "Created ");
    }

    public List<PaymentMethod> listAll() {
        return paymentMethodRepository.findAll();
    }

    private PaymentMethod getPaymentMethod(PaymentMethod paymentMethod) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException {
        PaymentMethod validPaymentMethod = paymentMethodIsValid(paymentMethod);
        return paymentMethodRepository.save(paymentMethod);
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + "PaymentMethod com a id " + id)
                .build();
    }

    private PaymentMethod verifyIfExists(Long id) throws PaymentNotFoundException {
        return paymentMethodRepository.findById(id).
                orElseThrow(() ->  new PaymentNotFoundException(id));
    }

    private PaymentMethod paymentMethodIsValid(PaymentMethod paymentMethod) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException {
        if (!PaymentMethodValidations.paymentMethodValidation(paymentMethod.getType().getType(),
                paymentMethod.getIdentifier())) {
            throw new InvalidParametersException();
        }
        if (!PaymentMethodValidations.checkPaymentBoundsToAccount(paymentMethod.getOriginAccount(),
                paymentMethod.getType().getType(), paymentMethod.getIdentifier())){
            throw new UnregisteredPaymentMethodException();
        }
        if (savingsAccountRepository.findById(paymentMethod.getOriginAccount().getId()).isEmpty() ||
                checkingAccountRepository.findById(paymentMethod.getOriginAccount().getId()).isEmpty() ) {
            throw new AccountNotFoundException(paymentMethod.getOriginAccount().getId());
        }
        //checar limites
        return paymentMethod;
    }

    public PaymentMethod findById(Long id) throws PaymentNotFoundException {
        return verifyIfExists(id);
    }

}