package com.example.model;

import com.example.enums.AccountType;
import com.example.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
// custom constructor
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private UUID id;
    private BigDecimal balance;
    private AccountType accountType;
    private Date creationDate;
    private Long userId;
    private Status status;
}
