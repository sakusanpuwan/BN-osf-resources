package com.kata.bddtdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinition {

    private BankAccount account;
    private Exception exceptionThrown;

    @Given("customer {string} has an account with balance {int} pounds")
    public void get_bank_account(String name,int openingBalance){
        account = new BankAccount(name,openingBalance);
    }

    @When("{string} opens a bank account with {int} pounds")
    public void opens_a_bank_account_with_pounds(String name, Integer openingBalance) {
        try {
            account = new BankAccount(name, openingBalance);
        } catch (Exception e){
            exceptionThrown = e;
        }
    }

    @When("{string} checks their balance")
    public void checks_their_account_balance(String name){
        if (!name.equals(account.getHolderName())) {
            throw new IllegalArgumentException("No account with that name");
        }
    }

    @Then("{string} account is created")
    public void account_is_created(String expectedNamed) {
        assertEquals(expectedNamed, account.getHolderName());
    }

    @Then("the balance is {int} pounds")
    public void the_balance_is_pounds(int expectedBalance) {
        assertEquals(expectedBalance, account.getBalance());
    }

    @Then("account is not created due to insufficient opening balance")
    public void account_is_not_created(){
        assertTrue(exceptionThrown instanceof IllegalArgumentException);
    }
}
