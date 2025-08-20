package dev.yely;

public class BankAccount {

    private float balance;
    private int depositCount;
    private int withdrawCount;
    private float annualRate;
    private float monthlyFee;

    public BankAccount(float balance, float annualRate) {
        this.balance = balance;
        this.annualRate = annualRate;
        this.depositCount = 0;
        this.withdrawCount = 0;
        this.monthlyFee = 0;
    }

    public void deposit(float amount) {
        balance += amount;
        depositCount++;
    }

    public void withdraw(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds for this withdraw.");
        }
        balance -= amount;
        withdrawCount++;
    }

    public void calculateMonthlyInterest() {
        final int MONTH_IN_YEAR = 12;
        final int PERCENT_DIVISOR = 100;
        float monthlyRate = (annualRate / MONTH_IN_YEAR / PERCENT_DIVISOR);
        float monthlyInterest = balance * monthlyRate;
        balance += monthlyInterest;
    }

    public void monthlyStatement() {
        balance -= monthlyFee;
        calculateMonthlyInterest();
    }

    public String printInfo() {
        String result = "Balance: %.2f; Deposit: %d; Withdrawal: %d; Annual Rate: %.2f; Monthly Fee: %.2f"
                .formatted(balance, depositCount, withdrawCount, annualRate, monthlyFee);
        return result;
    }

    public float getBalance() {
        return balance;
    }

    public float getAnnualRate() {
        return annualRate;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public int getDepositCount() {
        return depositCount;
    }

    public int getWithdrawCount() {
        return withdrawCount;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

}
