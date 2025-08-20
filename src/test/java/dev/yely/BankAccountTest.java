package dev.yely;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(4000f, 4);
    }

    @Test
    void testCalculateMonthlyInterest() {
        float monthlyRateExpected = account.getAnnualRate() / 12 / 100;
        float monthlyInterestExpected = monthlyRateExpected * account.getBalance();
        float expected = account.getBalance() + monthlyInterestExpected;

        account.calculateMonthlyInterest();
        float result = account.getBalance();

        assertThat(result, is(equalTo(expected)));

    }

    @Test
    void testDeposit() {
        int amount = 600;
        float expected = 4600f;

        account.deposit(amount);

        float result = account.getBalance();

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    void testGetBalance() {
        assertThat(account.getBalance(), is(4000f));
    }

    @Test
    void testMonthlyStatement() {
        float expected = 4013.3333F;

        account.monthlyStatement();

        float result = account.getBalance();

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    void testPrintInfo() {
        String expected = "Balance: 4000,00; Deposit: 0; Withdrawal: 0; Annual Rate: 4,00; Monthly Fee: 0,00";

        String result = account.printInfo();

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    void testWithdraw() {
        int amount = 500;
        float expected = 3500f;

        account.withdraw(amount);

        float result = account.getBalance();

        assertThat(result, is(equalTo(expected)));
    }
}
