package hackerrank;

import java.util.ArrayList;
import java.util.List;

class Account {
    private static final String INSUFFICIENT_BALANCE_SUFFIX = " (Insufficient Balance)";
    private static final String DEPOSIT_MESSAGE = "Depositing $%d";
    private static final String WITHDRAW_MESSAGE = "Withdrawing $%d";
    private volatile int balance = 0;

    public synchronized String deposit(int money) {
        balance = balance + money;
        return String.format(DEPOSIT_MESSAGE, money);
    }

    public synchronized String withdraw(int money) {
        if (balance - money > 0) {
            balance -= money;
            return String.format(WITHDRAW_MESSAGE, money);
        }
        return String.format(WITHDRAW_MESSAGE + INSUFFICIENT_BALANCE_SUFFIX, money);
    }

    public synchronized int getBalance() {
        return balance;
    }
}

class Transaction {
    private final Account account;
    private final List<String> transaction = new ArrayList<>();

    public Transaction(Account account) {
        this.account = account;
    }

    public synchronized void deposit(int money) {
        transaction.add(account.deposit(money));
    }

    public synchronized void withdraw(int money) {
        transaction.add(account.withdraw(money));
    }

    public synchronized List<String> getTransaction() {

        return transaction;
    }
}

public class AccountTransactionTest {
}
