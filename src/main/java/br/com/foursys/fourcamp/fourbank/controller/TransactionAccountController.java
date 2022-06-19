package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.DepositDto;
import br.com.foursys.fourcamp.fourbank.dto.TransferDTO;
import br.com.foursys.fourcamp.fourbank.dto.WithdrawDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.InsufficientFundsException;
import br.com.foursys.fourcamp.fourbank.service.TransactionCheckingAccountService;
import br.com.foursys.fourcamp.fourbank.service.TransactionSavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object DepositValueSavings(@RequestBody DepositDto depositDto){
        return transactionSavingsService.depositValue(depositDto.getAccountId(), depositDto.getDepositValue());
    }
    @PostMapping("/savings/withdraw")
    public Object withdrawValueSavings(@RequestBody WithdrawDTO withdrawDto){
        return transactionSavingsService.withdrawValue(withdrawDto.getAccountId(), withdrawDto.getWithdrawValue());
    }

    @PostMapping("/savings/transfer")
    public Object transferValueSavings(@RequestBody TransferDTO transferDTO){
        return transactionSavingsService.transferValue(transferDTO.getPayerId(), transferDTO.getReceiverId(),
                transferDTO.getTransferValue());
    }

    @PostMapping("/checking/deposit")
    public Object DepositValueChecking(@RequestBody DepositDto depositDto){
        return transactionCheckingAccountService.depositValue(depositDto.getAccountId(), depositDto.getDepositValue());
    }
    @PostMapping("/checking/withdraw")
    public Object withdrawValueChecking(@RequestBody WithdrawDTO withdrawDto) throws InsufficientFundsException {
        return transactionCheckingAccountService.withdrawValue(withdrawDto.getAccountId(), withdrawDto.getWithdrawValue());
    }

    @PostMapping("/checking/transfer")
    public Object transferValueChecking(@RequestBody TransferDTO transferDTO) throws InsufficientFundsException {
        return transactionCheckingAccountService.transferValue(transferDTO.getPayerId(), transferDTO.getReceiverId(),
                transferDTO.getTransferValue());
    }
}
