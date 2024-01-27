package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping(value = "/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable("accountNumber") String accountNumber, @RequestBody DepositTransaction depositTransaction) throws InsufficientBalanceException {
        var account = accountService.findAccount(accountNumber);
        account.post(depositTransaction);
        accountService.updateAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(new TransactionStatus(TransactionStatusType.OK.name(), depositTransaction.getApprovalCode()));
    }


    @PostMapping(value = "/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable("accountNumber") String accountNumber, @RequestBody WithdrawalTransaction withdrawalTransaction) throws InsufficientBalanceException {
        var account = accountService.findAccount(accountNumber);
        account.post(withdrawalTransaction);
        accountService.updateAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(new TransactionStatus(TransactionStatusType.OK.name(), withdrawalTransaction.getApprovalCode()));
    }

}