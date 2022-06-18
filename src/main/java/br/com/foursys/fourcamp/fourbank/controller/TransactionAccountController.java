package br.com.foursys.fourcamp.fourbank.controller;

import br.com.foursys.fourcamp.fourbank.dto.DepositDto;
import br.com.foursys.fourcamp.fourbank.dto.TransferDTO;
import br.com.foursys.fourcamp.fourbank.dto.WithdrawDTO;
import br.com.foursys.fourcamp.fourbank.service.TransactionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionAccountController {

    @Autowired
    TransactionAccountService transactionService;

    @PostMapping("/deposit")
    public Object DepositValue(@RequestBody DepositDto depositDto){
        return transactionService.depositValue(depositDto.getAccountId(), depositDto.getDepositValue());
    }
    @PostMapping("/withdraw")
    public Object withdrawValue(@RequestBody WithdrawDTO withdrawDto){
        return transactionService.withdrawValue(withdrawDto.getAccountId(), withdrawDto.getWithdrawValue());
    }

    @PostMapping("/transfer")
    public Object transferValue(@RequestBody TransferDTO transferDTO){
        return transactionService.transferValue(transferDTO.getPayerId(), transferDTO.getReceiverId(),
                transferDTO.getTransferValue());
    }
}
