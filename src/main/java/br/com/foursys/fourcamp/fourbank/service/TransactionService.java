package br.com.foursys.fourcamp.fourbank.service;


import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.AccountNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InvalidParametersException;
import br.com.foursys.fourcamp.fourbank.exceptions.PaymentNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.UnregisteredPaymentMethodException;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.CreditCardInstallment;
import br.com.foursys.fourcamp.fourbank.model.Transaction;
import br.com.foursys.fourcamp.fourbank.repository.CheckingAccountRepository;
import br.com.foursys.fourcamp.fourbank.repository.CreditCardRepository;
import br.com.foursys.fourcamp.fourbank.repository.TransactionRepository;
import br.com.foursys.fourcamp.fourbank.repository.SavingsAccountRepository;
import br.com.foursys.fourcamp.fourbank.util.PaymentMethodValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository paymentMethodRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    public TransactionService(TransactionRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public MessageResponseDTO createPaymentMethod(Transaction paymentMethod) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException {
        Transaction savedPaymentMethod = getPaymentMethod(paymentMethod);
        return createMessageResponse(savedPaymentMethod.getId(), "Criada ");
    }

    public List<Transaction> listAllByAccount(Integer accountId) {
        List<Transaction> paymentMethodList = new ArrayList<>();
        for (Transaction paymentMethod : paymentMethodRepository.findAll()) {
            if (paymentMethod.getOriginAccount().getId().equals(accountId)) {
                paymentMethodList.add(paymentMethod);
            }
        }
        return paymentMethodList;
    }

    private Transaction getPaymentMethod(Transaction paymentMethod) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException {
        Transaction validPaymentMethod = paymentMethodIsValid(paymentMethod);
        return paymentMethodRepository.save(paymentMethod);
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + "Transação com a id " + id)
                .build();
    }

    private Transaction verifyIfExists(Long id) throws PaymentNotFoundException {
        return paymentMethodRepository.findById(id).
                orElseThrow(() ->  new PaymentNotFoundException(id));
    }

    private Transaction paymentMethodIsValid(Transaction paymentMethod) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException {
        validatePaymentMethod(paymentMethod);
        checkIfAccountHasMethod(paymentMethod);
        checkIfAccountIsRegistered(paymentMethod);
       // UpdateCreditCardInstallments(paymentMethod);
        //checar limite do cartão de débito
        //Alterar limite do cartão de crédito, ou o saldo da conta
        return paymentMethod;
    }

    /*private void UpdateCreditCardInstallments(Transaction paymentMethod) {
        if (paymentMethod.getType().equals(0)) {
            //Diogo irá criar o findByCardNumber
            CreditCard creditCard = creditCardRepository.findByNumber(paymentMethod.getIdentifier());
            CreditCardInstallment installment = new CreditCardInstallment(LocalDate.now(),
                    paymentMethod.getNumberOfInstallments());
            creditCard.getCreditCardInstallments().add(installment);
        }
    }*/

    private void checkIfAccountIsRegistered(Transaction paymentMethod) throws AccountNotFoundException {
        if (savingsAccountRepository.findById(paymentMethod.getOriginAccount().getId()).isEmpty() ||
                checkingAccountRepository.findById(paymentMethod.getOriginAccount().getId()).isEmpty() ) {
            throw new AccountNotFoundException(paymentMethod.getOriginAccount().getId());
        }
    }

    private void checkIfAccountHasMethod(Transaction paymentMethod) throws UnregisteredPaymentMethodException {
        if (!PaymentMethodValidations.checkPaymentBoundsToAccount(paymentMethod.getOriginAccount(),
                paymentMethod.getType().getType(), paymentMethod.getIdentifier())){
            throw new UnregisteredPaymentMethodException();
        }
    }

    private void validatePaymentMethod(Transaction paymentMethod) throws InvalidParametersException {
        if (!PaymentMethodValidations.paymentMethodValidation(paymentMethod.getType().getType(),
                paymentMethod.getIdentifier())) {
            throw new InvalidParametersException();
        }
    }

    public Transaction findById(Long id) throws PaymentNotFoundException {
        return verifyIfExists(id);
    }



}