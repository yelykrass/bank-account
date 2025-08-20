package dev.yely;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingAccountTest {

    private CheckingAccount richAccount;
    private CheckingAccount poorAccount;

    @BeforeEach
    void setUp() {
        richAccount = new CheckingAccount(40000, 4);
        poorAccount = new CheckingAccount(400, 4);
    }

    @Test
    void testDepositRichAccount() {
        int amount = 6000;

        float exceptedBalance = 46000f;
        float exceptedOverdraft = 0;

        richAccount.deposit(amount);

        assertThat(richAccount.getBalance(), is(equalTo(exceptedBalance)));
        assertThat(richAccount.getOverdraft(), is(equalTo(exceptedOverdraft)));
    }

    @Test
    void testDepositPoorAccount() {
        int depositAmount = 200;
        float expectedBalance = 0f;
        float expectedOverdraft = 400f;

        poorAccount.withdraw(1000);

        poorAccount.deposit(depositAmount);

        assertThat(poorAccount.getBalance(), is(equalTo(expectedBalance)));
        assertThat(poorAccount.getOverdraft(), is(equalTo(expectedOverdraft)));
    }

    @Test
    void testPrintInfo() {
        String excepted = "CheckingAccount { balance: 40000,00, monthlyFee: 0,00, transactions: 0, overdraft: 0,00}";

        String result = richAccount.printInfo();

        assertThat(result, is(equalTo(excepted)));
    }

    @Test
    void testWithdrawWithRichAccount() {
        int amount = 600;
        float expectedBalance = 39400f;
        float exceptedOverdraft = 0f;

        richAccount.withdraw(amount);

        assertThat(richAccount.getBalance(), is(equalTo(expectedBalance)));
        assertThat(richAccount.getOverdraft(), is(equalTo(exceptedOverdraft)));
    }

    @Test
    void testWithdrawWithPoorAccount() {
        int amount = 600;
        float expectedBalance = 0f;
        float exceptedOverdraft = 200f;

        poorAccount.withdraw(amount);

        assertThat(poorAccount.getBalance(), is(equalTo(expectedBalance)));
        assertThat(poorAccount.getOverdraft(), is(equalTo(exceptedOverdraft)));
    }
}
