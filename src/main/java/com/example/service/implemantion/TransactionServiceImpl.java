package com.example.service.implemantion;

import com.example.enums.AccountType;
import com.example.exception.AccountOwnershipException;
import com.example.exception.BadRequestException;
import com.example.exception.BalanceNotSufficientException;
import com.example.exception.UnderConstructionException;
import com.example.model.Account;
import com.example.model.Transaction;
import com.example.repository.AccountRepository;
import com.example.repository.TransactionRepository;
import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}") // @Value sign help you to read properties file
    private boolean underConstruction;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository; // setting as final, that way you need to set in constructor!

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, String message, Date creationDate) {

        /**
         * If sender or receiver is null
         * If sender and receiver is the same account
         * if sender has enough balance
         * if both accounts are checking, if not, one of them saving, it needs to be same userID
         */

        if(!underConstruction) {

            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);

            /**
             * After all validation are completed, and money is transferred, we need to create Transaction inject and save/return it.
             */

            Transaction transaction = Transaction.builder().amount(amount).sender(sender.getId())
                    .receiver(receiver.getId()).creationDate(creationDate).message(message).build();

            return transactionRepository.save(transaction);
        }else{
            throw new UnderConstructionException("App is under construction, try again later.");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if(checkSenderBalance (sender,amount)){
            // make balance transfer between sender and receiver
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else{
            throw new BalanceNotSufficientException("");
        }
    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        return sender.getBalance().compareTo(amount) >= 0;
    }

    private void checkAccountOwnership(Account sender, Account receiver) {
        /**
         * Write an if statement that checks if one of the account is saving,
         * and user of sender or receiver is not the same, throw Account OwnershipException
         */

        if((sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))
            && !sender.getUserId().equals(receiver.getUserId())){

            throw new AccountOwnershipException("Since you are using a savings account, the sender and receiver userID must be the same");

        }




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
        return transactionRepository.findAll();
    }
}
