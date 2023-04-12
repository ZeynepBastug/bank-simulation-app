package com.example.service.implemantion;

import com.example.enums.AccountType;
import com.example.enums.Status;
import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {
        // We need to create Account object
        Account account = Account.builder().id(UUID.randomUUID()).userId(userId).balance(balance).accountType(accountType)
                .creationDate(creationDate).
                status(Status.ACTIVE).build();

        // Save into the database(repository)
        // return the object created
        return accountRepository.save(account);


    }

    @Override
    public List<Account> listAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(UUID id) {
        accountRepository.findById(id).setStatus(Status.DELETED);
    }

    @Override
    public void activateAccount(UUID id) {
        accountRepository.findById(id).setStatus(Status.ACTIVE);
    }
}
