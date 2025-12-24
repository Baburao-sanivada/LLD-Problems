package NewProblems.Splitwise;

import NewProblems.Splitwise.Models.SplitType;
import java.util.*;

public class SplitWiseSimulator {

    public static void main(String[] args) {
        System.out.println("Welcome to SplitWise Simulator!");

        SplitWise splitWise = SplitWise.getInstance();
        List<String> users = addUser(splitWise);
        String groupId = createGroup(splitWise, users);

        // Enable Simplify Debt
        splitWise.enableSimpleDebtCalculationForGroup(groupId);
        System.out.println("Simplify Debt Calculation Enabled");

        // ---------- TEST CASE 1 ----------
        System.out.println("\nTEST CASE 1: Equal split, User1 paid");
        addEqualExpense(splitWise, groupId, users, 0);
        splitWise.showGroupBalances(groupId);

        // ---------- TEST CASE 2 ----------
        System.out.println("\nTEST CASE 2: Equal split, User3 paid");
        addEqualExpense(splitWise, groupId, users, 2);
        splitWise.showGroupBalances(groupId);

        // ---------- TEST CASE 3 ----------
        System.out.println("\nTEST CASE 3: Unequal split");
        addUnequalExpense(splitWise, groupId, users);
        splitWise.showGroupBalances(groupId);

        // ---------- TEST CASE 4 ----------
        System.out.println("\nTEST CASE 4: Multiple expenses in same group");
        addEqualExpense(splitWise, groupId, users, 1);
        addEqualExpense(splitWise, groupId, users, 4);
        splitWise.showGroupBalances(groupId);

        // ---------- TEST CASE 5 ----------
        System.out.println("\nTEST CASE 5: One user pays for all");
        addSinglePayerExpense(splitWise, groupId, users);
        splitWise.showGroupBalances(groupId);

        // ---------- TEST CASE 6 ----------
        System.out.println("\nTEST CASE 6: Check individual user balance");
        splitWise.showUserBalances(users.get(0)); // User1
        splitWise.showUserBalances(users.get(3)); // User4
    }

    // ---------------- Helper Methods ----------------

    private static List<String> addUser(SplitWise splitWise) {
        List<String> userIds = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            userIds.add(
                    splitWise.addUser(
                            "User" + i,
                            "user" + i + "@gmail.com",
                            "+123456789" + i
                    )
            );
        }
        return userIds;
    }

    private static String createGroup(SplitWise splitWise, List<String> users) {
        return splitWise.addGroup(
                "Trip to Paris",
                "Friends trip expense",
                users
        );
    }

    // ---------- Equal Split ----------
    private static void addEqualExpense(
            SplitWise splitWise,
            String groupId,
            List<String> users,
            int paidIndex
    ) {
        Map<String, Double> splitsMap = new HashMap<>();
        for (String userId : users) {
            splitsMap.put(userId, 200.0); // equal share
        }

        splitWise.addExpenseToGroup(
                users.get(paidIndex),
                SplitType.EQUAL,
                1000.0,
                groupId,
                splitsMap
        );
    }

    // ---------- Unequal Split ----------
    private static void addUnequalExpense(
            SplitWise splitWise,
            String groupId,
            List<String> users
    ) {
        Map<String, Double> splitsMap = new HashMap<>();
        splitsMap.put(users.get(0), 400.0);
        splitsMap.put(users.get(1), 300.0);
        splitsMap.put(users.get(2), 200.0);
        splitsMap.put(users.get(3), 100.0);

        splitWise.addExpenseToGroup(
                users.get(0),  // User1 paid
                SplitType.EXACT,
                1000.0,
                groupId,
                splitsMap
        );
    }

    // ---------- One User Pays for All ----------
    private static void addSinglePayerExpense(
            SplitWise splitWise,
            String groupId,
            List<String> users
    ) {
        Map<String, Double> splitsMap = new HashMap<>();
        for (String userId : users) {
            splitsMap.put(userId, 100.0);
        }

        splitWise.addExpenseToGroup(
                users.get(4), // User5 paid
                SplitType.EQUAL,
                500.0,
                groupId,
                splitsMap
        );
    }
}