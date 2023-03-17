package com.example.repository;

import com.example.exception.RecordNotFoundException;
import com.example.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List<Account> accountsList = new ArrayList<>();

    public Account save(Account account){
        accountsList.add(account);
        return account;
    }

    public List<Account> findAll(){
        return accountsList;
    }

    public Account findById(UUID id){
        // TASK
        // write a method, that find the account inside the list, if not
        // throws RecordNotFoundException
        return accountsList.stream().filter(account -> account.getId().equals(id))
                .findAny().orElseThrow(() -> new RecordNotFoundException("Account is not exist in database"));
    }


}
