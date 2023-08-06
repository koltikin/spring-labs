package com.cydeo.loosely;

import com.cydeo.tightly.BalanceService;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        UUID user = UUID.randomUUID();

        Balance customerBalance = new CustomerBalance(user, BigDecimal.TEN);
        Balance giftCardBalance = new GiftCardBalance(user, BigDecimal.TEN);

        customerBalance.addBalance(new BigDecimal(150));
        giftCardBalance.addBalance(new BigDecimal(150));

        BalanceManager balanceManager = new BalanceManager();

        System.out.println(balanceManager.checkout(customerBalance,new BigDecimal(160)));
        System.out.println(balanceManager.checkout(giftCardBalance,new BigDecimal(160)));
    }

}
