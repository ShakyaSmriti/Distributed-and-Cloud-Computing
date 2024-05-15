
package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("BankSystem");
        ActorRef bankAccount = system.actorOf(Props.create(BankAccount.class), "bankAccount");

        // Print initial balance
        System.out.println("Initial Balance: £100");

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double amount = random.nextDouble() * 2000 - 1000; // Random value between -1000 to 1000
            if (amount > 0) {
                bankAccount.tell(new Deposit(amount), ActorRef.noSender());
            } else {
                bankAccount.tell(new Withdrawal(-amount), ActorRef.noSender());
            }
        }

        system.terminate();
    }
}
