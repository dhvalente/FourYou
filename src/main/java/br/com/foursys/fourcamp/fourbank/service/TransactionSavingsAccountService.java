package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.model.SavingsAccount;
import br.com.foursys.fourcamp.fourbank.model.TransactionAccount;
import br.com.foursys.fourcamp.fourbank.repository.TransactionAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionSavingsAccountService {

    @Autowired
    SavingsAccountService savingsAccountService;
    @Autowired
    TransactionAccountRepository transactionAccountRepository;

    public Object depositValue(Integer accountId, Double depositValue) {
        SavingsAccount savingsAccount = savingsAccountService.findById(accountId).get();
        savingsAccount.setBalance(savingsAccount.getBalance() + depositValue);
        savingsAccountService.save(savingsAccount);

        TransactionAccount transaction = new TransactionAccount();
        transaction.setPaymentTypeEnum(PaymentTypeEnum.DEPOSIT);
        transaction.setValue(depositValue);
        transaction.setPayerId(accountId);
        transaction.setReceiverId(accountId);
        transactionAccountRepository.save(transaction);

        return savingsAccount;
    }

    public Object withdrawValue(Integer accountId, Double withdrawValue) {
        SavingsAccount account = savingsAccountService.findById(accountId).get();
        if (account.getBalance() >= withdrawValue) {
            account.setBalance(account.getBalance() - withdrawValue);
            savingsAccountService.save(account);

            TransactionAccount transaction = new TransactionAccount();
            transaction.setPaymentTypeEnum(PaymentTypeEnum.WITHDRAW);
            transaction.setValue(withdrawValue);
            transaction.setPayerId(accountId);
            transaction.setReceiverId(accountId);
            transactionAccountRepository.save(transaction);

            return account;
        } else {
            return null;
        }
    }

    public Object transferValue(Integer payerId, Integer receiverId, Double transferValue) {
        SavingsAccount accountPayer = savingsAccountService.findById(payerId).get();
        SavingsAccount accountReceiver = savingsAccountService.findById(receiverId).get();
        if (accountPayer.getBalance() >= transferValue) {
            accountPayer.setBalance(accountPayer.getBalance() - transferValue);
            savingsAccountService.save(accountPayer);
            accountReceiver.setBalance(accountReceiver.getBalance() + transferValue);
            savingsAccountService.save(accountReceiver);

            TransactionAccount transaction = new TransactionAccount();
            transaction.setPaymentTypeEnum(PaymentTypeEnum.TRANSFER);
            transaction.setValue(transferValue);
            transaction.setPayerId(payerId);
            transaction.setReceiverId(receiverId);
            transactionAccountRepository.save(transaction);

            return accountPayer;
        } else {
            return null;
        }

    }
}
