package com.example.service.implemantion;

import com.example.exception.BadRequestException;
import com.example.exception.RecordNotFoundException;
import com.example.model.Account;
import com.example.model.Transaction;
import com.example.repository.AccountRepository;
import com.example.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransationServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    public TransationServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, String message, Date creationDate) {
        validateAccount(sender,receiver);
        return null;
    }

    private void validateAccount(Account sender, Account receiver){

        /**
         * if any of the account is null
         * if account ids are the same (same accountt)
         * if the accounts exist in the database(repo)
         */

        if (sender == null || receiver == null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver");
        }

        if(accountRepository.findById(sender.getId()).equals(accountRepository.findById(receiver.getId()))){
            throw new RecordNotFoundException("Sender and Receiver can not be the same");
        }

        findAccountByID(sender.getId());
        findAccountByID(receiver.getId());

    }

    private void findAccountByID(UUID id){
        accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
