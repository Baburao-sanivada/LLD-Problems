package Splitwise.Managers;

import Splitwise.Models.Expense;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ExpenseManager {
    Map<String, Expense> expenses;

    private static ExpenseManager instance = null;

    public static ExpenseManager getInstance() {
        if (instance == null) {
            synchronized (ExpenseManager.class) {
                if (instance == null) {
                    instance = new ExpenseManager();
                }
            }
        }
        return instance;
    }

    private ExpenseManager() {
        expenses = new ConcurrentHashMap<>();
    }

    public void addExpense(Expense expense) {
        expenses.put(expense.getExpenseId().toString(), expense);
    }

    public Optional<Expense> getExpenseById(String expenseId) {
        return Optional.ofNullable(expenses.get(expenseId));
    }
}
