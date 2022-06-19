package br.com.foursys.fourcamp.fourbank.service;


import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;
import br.com.foursys.fourcamp.fourbank.model.CheckingAccount;
import br.com.foursys.fourcamp.fourbank.model.Transaction;
import br.com.foursys.fourcamp.fourbank.repository.TransactionAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionCheckingAccountService {

    @Autowired
    TransactionAccountRepository transactionAccountRepository;

    @Autowired
    CheckingAccountService checkingAccountService;

    public Object depositValue(Integer accountId, Double depositValue) {
        CheckingAccount checkingAccount = checkingAccountService.findById(accountId).get();
        checkingAccount.setBalance(checkingAccount.getBalance() + depositValue);
        checkingAccountService.save(checkingAccount);

        Transaction transaction = new Transaction();
        transaction.setPaymentTypeEnum(PaymentTypeEnum.DEPOSIT);
        transaction.setValue(depositValue);
        transaction.setPayerId(accountId);
        transaction.setReceiverId(accountId);
        transactionAccountRepository.save(transaction);

        return checkingAccount;
    }

    public Object withdrawValue(Integer accountId, Double withdrawValue) {
        CheckingAccount account = checkingAccountService.findById(accountId).get();
        if (account.getBalance() >= withdrawValue) {
            account.setBalance(account.getBalance() - withdrawValue);
            checkingAccountService.save(account);

            Transaction transaction = new Transaction();
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
        CheckingAccount accountPayer = checkingAccountService.findById(payerId).get();
        CheckingAccount accountReceiver = checkingAccountService.findById(receiverId).get();
        if (accountPayer.getBalance() >= transferValue) {
            accountPayer.setBalance(accountPayer.getBalance() - transferValue);
            checkingAccountService.save(accountPayer);
            accountReceiver.setBalance(accountReceiver.getBalance() + transferValue);
            checkingAccountService.save(accountReceiver);

            Transaction transaction = new Transaction();
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
