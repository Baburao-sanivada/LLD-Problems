package NewProblems.Splitwise;

import Splitwise.Managers.ExpenseManager;
import Splitwise.Managers.GroupManager;
import Splitwise.Managers.UserManager;
import Splitwise.Models.Expense;
import Splitwise.Models.Group;
import Splitwise.Models.SplitType;
import Splitwise.Models.User;
import Splitwise.Strategy.SplitStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SplitWise {
    private UserManager userManager;
    private ExpenseManager expenseManager;
    private Splitwise.BalanceSheet balanceSheet;
    private GroupManager groupManager;
    private static SplitWise instance = null;
    private AtomicInteger userIdCounter = new AtomicInteger(0);
    private AtomicInteger groupIdCounter = new AtomicInteger(0);
    private AtomicInteger expenseIdCounter = new AtomicInteger(0);

    public static SplitWise getInstance(){
        if(instance == null){
            synchronized (SplitWise.class){
                if(instance == null){
                    instance = new SplitWise();
                }
            }
        }
        return instance;
    }

    private SplitWise(){
        this.balanceSheet = Splitwise.BalanceSheet.getInstance();
        this.userManager = UserManager.getInstance();
        this.expenseManager = ExpenseManager.getInstance();
        this.groupManager = GroupManager.getInstance();
    }


    public String addUser(String name, String email, String phoneNumber){
        String userId = "U" + userIdCounter.getAndIncrement();

        userManager.addUser(new User(userId,name,email,phoneNumber));
        return userId;
    }

    public String addGroup(String groupName, String groupDescription, List<String> users){
        String groupId = "G" + groupIdCounter.getAndIncrement();
        Group groupInstance = new Group(groupId,groupName,groupDescription);
        for(String userId : users){
            groupInstance.addMember(userId);
        }
        groupManager.addGroup(groupInstance);
        return groupId;
    }

    public void addExpenseToGroup(String paidBy, SplitType splitType, double amount, String groupId, Map<String, Double> involvedUsers){
        String expenseId = "EXP" + expenseIdCounter.getAndIncrement();
        SplitStrategy strategy = Splitwise.SplitStrategyFactory.getSplitStrategy(splitType, involvedUsers, paidBy, amount);
        Expense expense = new Expense.ExpenseBuilder().setGroupId(groupId).setPaidUserId(paidBy).setAmount(amount).setSplitType(splitType).setSplitStrategy(strategy).build(expenseId);
        groupManager.addExpenseToGroup(groupId,expense);
    }

    public void showGroupBalances(String groupId){
        balanceSheet.showGroupBalances(groupId);
    }

    public void showUserBalances(String userId){
        balanceSheet.showUserBalances(userId);
    }


}
