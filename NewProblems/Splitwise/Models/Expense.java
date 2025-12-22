package Splitwise.Models;

import Splitwise.Strategy.SplitStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Expense {

    private String expenseId;
    private String description;
    private String paidByUserId;
    private double amount;
    private SplitType splitType;
    private List<Split> splitList;
    private String groupId;

    public Expense(String id, String description, String userId, double amount, SplitType splitType, List<Split> splits, String groupId) {
        this.expenseId = id;
        this.description = description;
        this.paidByUserId = userId;
        this.amount = amount;
        this.splitType = splitType;
        this.splitList = splits;
        this.groupId = groupId;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public double getAmount() {
        return amount;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public List<Split> getSplitList() {
        return splitList;
    }

    public String getGroupId() {
        return groupId;
    }

    public static class ExpenseBuilder {
        private String description;
        private String userId;
        private double amount;
        private SplitType splitType;
        private SplitStrategy splitStrategy;
        private String groupId;

        public ExpenseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }
        public ExpenseBuilder setPaidUserId(String userId) {
            this.userId = userId;
            return this;
        }
        public ExpenseBuilder setAmount(double amount) {
            this.amount = amount;
            return this;
        }
        public ExpenseBuilder setSplitType(SplitType splitType) {
            this.splitType = splitType;
            return this;
        }

        public Expense build(String id) {
            List<Split> splits = new ArrayList<>();
            if (splitStrategy != null) {
                splits = splitStrategy.getSplitInfo();
            }
            return new Expense(id,description, userId, amount, splitType, splits, groupId);
        }

        public ExpenseBuilder setGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public ExpenseBuilder setSplitStrategy(SplitStrategy splitStrategy) {
            this.splitStrategy = splitStrategy;
            return this;
        }
    }


}
