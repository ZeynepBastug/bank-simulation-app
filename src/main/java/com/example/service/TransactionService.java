package com.example.service;

import com.example.model.Account;
import com.example.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount,String message, Date creationDate);

    List<Transaction> findAllTransaction();
}
