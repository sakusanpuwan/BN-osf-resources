package com.kata.bddtdd;

public class BankAccount {


    private String accountHolder;
    private int balance;

    public BankAccount(String accountHolder, int openingBalance) {
        if (openingBalance < 100){
            throw new IllegalArgumentException("Minimum opening balance must be £100");
        }
        this.accountHolder = accountHolder;
        this.balance = openingBalance;
    }

    public String getHolderName() {
        return accountHolder;
    }

    public int getBalance() {
        return balance;
    }
}
