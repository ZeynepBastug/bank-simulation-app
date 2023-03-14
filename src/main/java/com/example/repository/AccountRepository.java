package com.example.repository;

import com.example.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {

    public static List<Account> accountsList = new ArrayList<>();

    public Account save(Account account){
        accountsList.add(account);
        return account;
    }
}
