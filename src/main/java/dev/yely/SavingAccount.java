package dev.yely;

public class SavingAccount extends BankAccount {

    private boolean isActive;
    private static final float MIN_ACTIVE_BALANCE = 10_000f;
    private static final float EXTRA_WITHDRAW_FEE = 1000f;

    public SavingAccount(float balance, float annualRate) {
        super(balance, annualRate);
        this.isActive = balance >= MIN_ACTIVE_BALANCE;
    }

    private void updateActiveStatus() {
        this.isActive = getBalance() >= MIN_ACTIVE_BALANCE;
    }

    @Override
    public void deposit(float amount) {
        if (!isActive) {
            return;
        }
        super.deposit(amount);
        updateActiveStatus();
    }

    @Override
    public void withdraw(float amount) {
        if (!isActive) {
            return;
        }
        super.withdraw(amount);
        updateActiveStatus();
    }

    @Override
    public void monthlyStatement() {
        float extraFees = 0;
        if (getWithdrawCount() > 4) {
            extraFees = getWithdrawCount() - 4;
        }
        setMonthlyFee(extraFees * EXTRA_WITHDRAW_FEE);

        super.monthlyStatement();
        updateActiveStatus();
    }

    @Override
    public String printInfo() {
        int transactions = getDepositCount() + getWithdrawCount();
        String result = "SavingAccount { Balance: %.2f; MonthlyFee: %.2f; transations: %d; active: %b}"
                .formatted(getBalance(), getMonthlyFee(), transactions, isActive);
        return result;
    }

    public boolean getIsActive() {
        return isActive;
    }

}
