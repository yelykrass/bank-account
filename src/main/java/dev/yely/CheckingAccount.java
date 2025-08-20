package dev.yely;

public class CheckingAccount extends BankAccount {

    private float overdraft;

    public CheckingAccount(float balance, float annualRate) {
        super(balance, annualRate);
        this.overdraft = 0;
    }

    @Override
    public void withdraw(float amount) {
        if (getBalance() < amount) {
            overdraft += (amount - getBalance());
            super.withdraw(getBalance());
        } else {
            super.withdraw(amount);
        }

    }

    @Override
    public void deposit(float amount) {
        if (overdraft > 0) {
            if (amount < overdraft) {
                overdraft -= amount;
            } else {
                super.deposit(amount - overdraft);
                overdraft = 0;
            }
        } else {
            super.deposit(amount);
        }
    }

    @Override
    public void monthlyStatement() {
        super.monthlyStatement();
    }

    @Override
    public String printInfo() {
        int transactions = getDepositCount() + getWithdrawCount();
        String result = "CheckingAccount { balance: %.2f, monthlyFee: %.2f, transactions: %d, overdraft: %.2f}"
                .formatted(getBalance(), getMonthlyFee(), transactions, overdraft);
        return result;
    }

    public float getOverdraft() {
        return overdraft;
    }
}
