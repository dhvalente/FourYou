package br.com.foursys.fourcamp.fourbank.service;


import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.exceptions.AccountNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InsufficientFundsException;
import br.com.foursys.fourcamp.fourbank.model.CheckingAccount;
import br.com.foursys.fourcamp.fourbank.model.TransactionAccount;
import br.com.foursys.fourcamp.fourbank.repository.TransactionAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionCheckingAccountService {

    @Autowired
    TransactionAccountRepository transactionAccountRepository;

    @Autowired
    CheckingAccountService checkingAccountService;

    public Object depositValue(Integer accountId, Double depositValue) throws AccountNotFoundException {
        CheckingAccount checkingAccount = checkingAccountService.findById(accountId);
        checkingAccount.setBalance(checkingAccount.getBalance() + depositValue);
        checkingAccountService.save(checkingAccount);

        TransactionAccount transaction = new TransactionAccount();
        transaction.setPaymentTypeEnum(PaymentTypeEnum.DEPOSIT);
        transaction.setValue(depositValue);
        transaction.setPayerId(accountId);
        transaction.setReceiverId(accountId);
        transactionAccountRepository.save(transaction);

        return checkingAccount;
    }

    public Object withdrawValue(Integer accountId, Double withdrawValue) throws InsufficientFundsException, AccountNotFoundException {
        CheckingAccount account = checkingAccountService.findById(accountId);
        if (account.getBalance() >= withdrawValue) {
            account.setBalance(account.getBalance() - withdrawValue);
            checkingAccountService.save(account);

            TransactionAccount transaction = new TransactionAccount();
            transaction.setPaymentTypeEnum(PaymentTypeEnum.WITHDRAW);
            transaction.setValue(withdrawValue);
            transaction.setPayerId(accountId);
            transaction.setReceiverId(accountId);
            transactionAccountRepository.save(transaction);

            return account;
        } else {
            throw new InsufficientFundsException();
        }
    }

    public Object transferValue(String paymentType, Integer payerId, Integer receiverId, Double transferValue) throws InsufficientFundsException, AccountNotFoundException {
        CheckingAccount accountPayer = checkingAccountService.findById(payerId);
        CheckingAccount accountReceiver = checkingAccountService.findById(receiverId);
        if (accountPayer.getBalance() >= (transferValue * 1.03)) {
            if (paymentType.equals("debit")) {
                //todo test
                accountPayer.setBalance(accountPayer.getBalance() - transferValue * 1.03);
            } else {
                accountPayer.setBalance(accountPayer.getBalance() - transferValue);
            }
            checkingAccountService.save(accountPayer);
            accountReceiver.setBalance(accountReceiver.getBalance() + transferValue);
            checkingAccountService.save(accountReceiver);

            TransactionAccount transaction = new TransactionAccount();
            transaction.setPaymentTypeEnum(PaymentTypeEnum.TRANSFER);
            transaction.setValue(transferValue);
            transaction.setPayerId(payerId);
            transaction.setReceiverId(receiverId);
            transactionAccountRepository.save(transaction);

            return accountPayer;
        } else {
            throw new InsufficientFundsException();
        }

    }
}
