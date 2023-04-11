package com.example.service;

import com.example.enums.AccountType;
import com.example.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId);

    List<Account> listAllAccount();

}
