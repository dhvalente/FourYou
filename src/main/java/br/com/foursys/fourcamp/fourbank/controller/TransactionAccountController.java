package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.DepositDto;
import br.com.foursys.fourcamp.fourbank.dto.WithdrawDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.AccountNotFoundException;
import br.com.foursys.fourcamp.fourbank.exceptions.InsufficientFundsException;
import br.com.foursys.fourcamp.fourbank.service.TransactionCheckingAccountService;
import br.com.foursys.fourcamp.fourbank.service.TransactionSavingsAccountService;
import br.com.foursys.fourcamp.fourbank.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionAccountController {

    @Autowired
    TransactionSavingsAccountService transactionSavingsService;

    @Autowired
    TransactionCheckingAccountService transactionCheckingAccountService;

    @PostMapping("/savings/deposit")
    public ResponseEntity<Object> DepositValueSavings(@RequestBody DepositDto depositDto) throws AccountNotFoundException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionSavingsService.depositValue(depositDto.
                getAccountId(), depositDto.getDepositValue()));
    }
    @PostMapping("/savings/withdraw")
    public ResponseEntity<Object> withdrawValueSavings(@RequestBody WithdrawDTO withdrawDto) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionSavingsService.withdrawValue(withdrawDto.
                    getAccountId(), withdrawDto.getWithdrawValue()));
        } catch (InsufficientFundsException | AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseModel(HttpStatus.NOT_ACCEPTABLE,
                    HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));
        }
    }

    @PostMapping("/checking/deposit")
    public ResponseEntity<Object> DepositValueChecking(@RequestBody DepositDto depositDto) throws AccountNotFoundException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionCheckingAccountService.depositValue(
                depositDto.getAccountId(), depositDto.getDepositValue()));
    }
    @PostMapping("/checking/withdraw")
    public ResponseEntity<Object> withdrawValueChecking(@RequestBody WithdrawDTO withdrawDto) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(transactionCheckingAccountService.withdrawValue(
                    withdrawDto.getAccountId(), withdrawDto.getWithdrawValue()));
        } catch (InsufficientFundsException | AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseModel(HttpStatus.NOT_ACCEPTABLE,
                    HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()));
        }
    }



}
