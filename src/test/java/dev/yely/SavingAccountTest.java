package dev.yely;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    private SavingAccount inactiveAccount;
    private SavingAccount activeAccount;

    @BeforeEach
    void setUp() {
        inactiveAccount = new SavingAccount(4000, 4);
        activeAccount = new SavingAccount(40000, 4);
    }

    @Test
    void testDepositInactive() {
        int amount = 500;
        float expected = 4000;

        inactiveAccount.deposit(amount);

        float result = inactiveAccount.getBalance();

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    void testDepositActive() {
        int amount = 500;
        float expected = 40500;

        activeAccount.deposit(amount);

        float result = activeAccount.getBalance();

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    void testMonthlyStatement() {
        for (int i = 0; i < 5; i++) {
            activeAccount.withdraw(500f);
        }

        float extraFees = (activeAccount.getWithdrawCount() - 4) * 1000;
        float balanceAfterFees = activeAccount.getBalance() - extraFees;
        float monthlyInterest = balanceAfterFees * (activeAccount.getAnnualRate() / 12 / 100);
        float expected = balanceAfterFees + monthlyInterest;

        activeAccount.monthlyStatement();
        float result = activeAccount.getBalance();

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    void testPrintInfo() {
        String expected = "SavingAccount { Balance: 40000,00; MonthlyFee: 0,00; transations: 0; active: true}";

        String result = activeAccount.printInfo();

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    void testWithdrawNotActive() {
        int amount = 500;
        float expected = 4000;

        inactiveAccount.withdraw(amount);

        float result = inactiveAccount.getBalance();

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    void testWithdrawIsActive() {
        int amount = 500;
        float expected = 39500;

        activeAccount.withdraw(amount);

        float result = activeAccount.getBalance();

        assertThat(result, is(equalTo(expected)));
    }

}
