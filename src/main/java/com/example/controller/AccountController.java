package com.example.controller;

import com.example.enums.AccountType;
import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String indexView(Model model){
        model.addAttribute("accounts", accountService.listAllAccount());
        return "/account/index";
    }

    @GetMapping("/create-form")
    public String createAccountView(Model model){
       model.addAttribute("account", Account.builder().build());
                                                // empty Account object is provided
       model.addAttribute("accountTypes", AccountType.values());

       return "/account/create-account";

    }

    @PostMapping("/index")
    public String createNewAccount(Account account, Model model){
        accountService.createNewAccount(account.getBalance(), new Date(), account.getAccountType(), account.getUserId());

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount (@PathVariable("id") String id, Model model){
        accountService.deleteAccount(UUID.fromString(id));
        return "redirect:/index";
    }

    @GetMapping("/activate/{id}")
    public String activateAccount (@PathVariable("id") String id, Model model){
        accountService.activateAccount(UUID.fromString(id));
        return "redirect:/index";
    }

}
