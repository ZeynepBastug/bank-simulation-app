package com.example.service.implemantion;

import com.example.exception.BadRequestException;
import com.example.exception.RecordNotFoundException;
import com.example.model.Account;
import com.example.model.Transaction;
import com.example.repository.AccountRepository;
import com.example.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
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

        // Verify if we have sender and receiver in the database
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
