package com.example.controller;

import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
       model.addAttribute("account", new Account() );

       return "/account/create-account";

    }

    @PostMapping("/create-form")
    public String createNewAccount(Account account){

        return "redirect:/account/create-account";
    }

}
