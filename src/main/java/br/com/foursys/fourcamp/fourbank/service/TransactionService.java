package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.exceptions.*;
import br.com.foursys.fourcamp.fourbank.model.*;
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
	TransactionCheckingAccountService transactionCheckingAccountService;
	@Autowired
	TransactionSavingsAccountService transactionSavingsAccountService;

	@Autowired
	private TransactionAccountRepository transactionAccountRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	public MessageResponseDTO createPaymentMethod(Transaction transaction)
			throws InvalidParametersException, UnregisteredPaymentMethodException, AccountNotFoundException,
			CardNotFoundException, CreditLimitInsufficientException, InsufficientFundsException {
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

	private Transaction getPaymentMethod(Transaction transaction)
			throws InvalidParametersException, UnregisteredPaymentMethodException, AccountNotFoundException,
			CardNotFoundException, CreditLimitInsufficientException, InsufficientFundsException {
		Transaction validTransaction = transactionIsValid(transaction);
		return transactionRepository.save(validTransaction);
	}

	private MessageResponseDTO createMessageResponse(Long id, String s) {
		return MessageResponseDTO.builder().message(s + "Transação com a id " + id).build();
	}

	private Transaction verifyIfExists(Long id) throws TransactionNotFoundException {
		return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
	}

	private Transaction transactionIsValid(Transaction transaction)
			throws InvalidParametersException, UnregisteredPaymentMethodException, AccountNotFoundException,
			CardNotFoundException, CreditLimitInsufficientException, InsufficientFundsException {
		validateTransaction(transaction);
		checkIfAccountIsRegistered(transaction);
		checkIfCreditCardHasLimit(transaction);
		checkIfDebitCardHasLimit(transaction);
		updateAccountBalance(transaction);
		return transaction;
	}

	private void updateAccountBalance(Transaction transaction) throws InsufficientFundsException, AccountNotFoundException {
		PaymentTypeEnum paymentTypeEnum = transaction.getType();
		Double transactionValue = transaction.getValue();
		switch (paymentTypeEnum) {
			case DEBIT -> {
				checkFundsAndPerformTransaction("debit", transaction, transactionValue);
			}
			case PIX, TED, DOC, TRANSFER -> {
				checkFundsAndPerformTransaction("other", transaction, transactionValue);

			}
		}

	}

	private void checkFundsAndPerformTransaction(String paymentType, Transaction transaction,
												 Double transactionValue) throws InsufficientFundsException, AccountNotFoundException {
		CheckingAccount account = transaction.getOriginAccount();
		transactionCheckingAccountService.transferValue(paymentType, account.getId(),
				transaction.getDestinationAccount().getId(), transaction.getValue());
	}

	private void checkIfAccountIsRegistered(Transaction transaction) throws AccountNotFoundException {
		Integer id = transaction.getOriginAccount().getId();
		checkingAccountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

	}

	private void validateTransaction(Transaction transaction) throws InvalidParametersException {
		if (!PaymentMethodValidations.paymentMethodValidation(transaction.getType().getType(),
				transaction.getIdentifier())) {
			throw new InvalidParametersException();
		}

	}

	private void checkIfCreditCardHasLimit(Transaction transaction)
			throws CardNotFoundException, CreditLimitInsufficientException {
		if (transaction.getType().equals(CREDIT)) {
			CreditCard creditCard = creditCardRepository.findByNumber(transaction.getIdentifier());
			creditCardService.assertCreditCardStillHasLimit(transaction.getValue(), creditCard.getId());
		}
	}

	private void checkIfDebitCardHasLimit(Transaction transaction)
			throws CardNotFoundException, CreditLimitInsufficientException {
		if (transaction.getType().equals(DEBIT)) {
			DebitCard debitCard = debitCardRepository.findByNumber(transaction.getIdentifier());
			creditCardService.assertCreditCardStillHasLimit(transaction.getValue(), debitCard.getId());
		}
	}

	public Transaction findById(Long id) throws TransactionNotFoundException {
		return verifyIfExists(id);
	}

	public void phoneRecharge(PhoneRecharge phoneRecharge) {
		TransactionAccount transactionAccount = new TransactionAccount();
		transactionAccount.setPaymentTypeEnum(phoneRecharge.getPaymentTypeEnum());
		transactionAccount.setValue(phoneRecharge.getValueRecharge());
		transactionAccount.setDescription("Efetuação de recarga no número: " + phoneRecharge.getPhoneNumber()
				+ " No valor de: " + phoneRecharge.getValueRecharge());
		transactionAccountRepository.save(transactionAccount);
	}
}