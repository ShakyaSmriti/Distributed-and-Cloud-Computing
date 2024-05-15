package com.example;

import akka.actor.AbstractActor;

public class BankAccount extends AbstractActor {
    private double balance = 100; // Initial balance

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Deposit.class, this::handleDeposit)
                .match(Withdrawal.class, this::handleWithdrawal)
                .build();
    }

    private void handleDeposit(Deposit deposit) {
        balance += deposit.getAmount();
        System.out.println("Deposited: £" + deposit.getAmount() + ", New Balance: £" + balance);
    }

    private void handleWithdrawal(Withdrawal withdrawal) {
        if (balance >= withdrawal.getAmount()) {
            balance -= withdrawal.getAmount();
            System.out.println("Withdrawn: £" + withdrawal.getAmount() + ", New Balance: £" + balance);
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }
}
