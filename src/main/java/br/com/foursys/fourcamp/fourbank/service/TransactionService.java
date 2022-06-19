package br.com.foursys.fourcamp.fourbank.service;


import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.*;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.DebitCard;
import br.com.foursys.fourcamp.fourbank.model.Transaction;
import br.com.foursys.fourcamp.fourbank.repository.*;
import br.com.foursys.fourcamp.fourbank.util.PaymentMethodValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum.CREDIT;
import static br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum.DEBIT;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public MessageResponseDTO createPaymentMethod(Transaction transaction) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException, CardNotFoundException,
            CreditLimitInsufficientException {
        Transaction savedTransaction = getPaymentMethod(transaction);
        return createMessageResponse(savedTransaction.getId(), "Criada ");
    }

    public List<Transaction> listAllByAccount(Integer accountId) {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction : transactionRepository.findAll()) {
            if (transaction.getOriginAccount().getId().equals(accountId)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    private Transaction getPaymentMethod(Transaction transaction) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException, CardNotFoundException,
            CreditLimitInsufficientException {
        Transaction validTransaction = transactionIsValid(transaction);
        return transactionRepository.save(validTransaction);
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + "Transação com a id " + id)
                .build();
    }

    private Transaction verifyIfExists(Long id) throws TransactionNotFoundException {
        return transactionRepository.findById(id).
                orElseThrow(() ->  new TransactionNotFoundException(id));
    }

    private Transaction transactionIsValid(Transaction transaction) throws InvalidParametersException,
            UnregisteredPaymentMethodException, AccountNotFoundException, CardNotFoundException,
            CreditLimitInsufficientException {
        validateTransaction(transaction);
        checkIfAccountHasMethod(transaction);
        checkIfAccountIsRegistered(transaction);
        checkIfCreditCardHasLimit(transaction);
        checkIfDebitCardHasLimit(transaction);
       // UpdateCreditCardInstallments(transaction);
        //alterar saldo de contas caso pix, ted ou doc
        return transaction;
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

    private void checkIfAccountIsRegistered(Transaction transaction) throws AccountNotFoundException {
        if (savingsAccountRepository.findById(transaction.getOriginAccount().getId()).isEmpty() ||
                checkingAccountRepository.findById(transaction.getOriginAccount().getId()).isEmpty() ) {
            throw new AccountNotFoundException(transaction.getOriginAccount().getId());
        }
    }

    private void checkIfAccountHasMethod(Transaction transaction) throws UnregisteredPaymentMethodException {
        if (!PaymentMethodValidations.checkPaymentBoundsToAccount(transaction.getOriginAccount(),
                transaction.getType().getType(), transaction.getIdentifier())){
            throw new UnregisteredPaymentMethodException();
        }
    }

    private void validateTransaction(Transaction transaction) throws InvalidParametersException {
        if (!PaymentMethodValidations.paymentMethodValidation(transaction.getType().getType(),
                transaction.getIdentifier())) {
            throw new InvalidParametersException();
        }

    }

    private void checkIfCreditCardHasLimit(Transaction transaction) throws CardNotFoundException,
            CreditLimitInsufficientException {
        if (transaction.getType().equals(CREDIT)) {
            CreditCard creditCard = creditCardRepository.findByNumber(transaction.getIdentifier());
            creditCardService.assertCreditCardStillHasLimit(transaction.getValue(), creditCard.getId());
        }
    }

    private void checkIfDebitCardHasLimit(Transaction transaction) throws CardNotFoundException,
            CreditLimitInsufficientException {
        if (transaction.getType().equals(DEBIT)) {
            DebitCard debitCard = debitCardRepository.findByNumber(transaction.getIdentifier());
            creditCardService.assertCreditCardStillHasLimit(transaction.getValue(), debitCard.getId());
        }
    }

    public Transaction findById(Long id) throws TransactionNotFoundException {
        return verifyIfExists(id);
    }



}