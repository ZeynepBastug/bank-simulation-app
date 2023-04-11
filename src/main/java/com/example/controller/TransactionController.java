package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    @GetMapping("/make-transfer")
    public String makeTransferVew(){

        return "/transaction/make-transfer";
    }




}
