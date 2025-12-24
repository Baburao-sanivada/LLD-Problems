package NewProblems.Splitwise.Managers;

import NewProblems.Splitwise.BalanceSheet;
import NewProblems.Splitwise.Models.Expense;
import NewProblems.Splitwise.Models.Group;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GroupManager {
    Map<String, Group> groups;

    private static GroupManager instance = null;
    private ExpenseManager expenseManager;
    private BalanceSheet balanceSheet;
    public static GroupManager getInstance(){
        if(instance == null){
            synchronized (GroupManager.class){
                if(instance == null){
                    instance = new GroupManager();
                }
            }
        }
        return instance;
    }
    private GroupManager(){
        groups = new ConcurrentHashMap<>();
        expenseManager = ExpenseManager.getInstance();
        balanceSheet = BalanceSheet.getInstance();
    }

    public void addGroup(Group group){
        groups.put(group.getGroupId(),group);
    }

    public Optional<Group> getGroupById(String groupId){
        return Optional.ofNullable(groups.get(groupId));
    }

    public void addExpenseToGroup(String groupId, Expense expense){
        expenseManager.addExpense(expense);
        balanceSheet.updateBalancesForExpense(expense);
    }

    public void enableSimpleDebtCalculationForGroup(String groupId) {
        balanceSheet.enableSimpleDebtCalculationForGroup(groupId);
    }

    public void settleBalance(String groupId, String fromUserId, String toUserId, double amount) {
        balanceSheet.settleBalance(groupId, fromUserId, toUserId, amount);
    }
}
