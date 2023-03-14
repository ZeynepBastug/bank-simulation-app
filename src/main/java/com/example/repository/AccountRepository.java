package com.example.repository;

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

    public void findById(UUID id){
        // TASK

        // write a method, that find the account inside the list, if not
        // throws RecordNotFoundException

    }


}
