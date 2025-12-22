package Splitwise;

import Splitwise.Models.SplitType;

import java.util.*;

public class SplitWiseSimulator {

    public static void main(String[] args) {
        System.out.println("Welcome to SplitWise Simulator!");
        SplitWise splitWise = SplitWise.getInstance();
        List<String> users = addUser(splitWise);
        String groupId = createGroup(splitWise, users);
        addExpense(splitWise, groupId, users, 0);
        splitWise.showGroupBalances(groupId);

        splitWise.showUserBalances(users.get(2));

    }

    private static List<String> addUser(SplitWise splitWise) {
        List<String> userIds = new ArrayList<>();
        for(int i=1;i<=5;i++) {
            userIds.add(splitWise.addUser("User"+i, "user"+i+"@gmai.com","+123456789"+i));
        }
        return userIds;
    }

    private static String createGroup(SplitWise splitWise, List<String> users) {
        return splitWise.addGroup("Trip to Paris", "Description" , users);
    }

    private static void addExpense(SplitWise splitWise, String groupId, List<String> users, int paidIndex) {
        Map<String, Double> splitsMap = new HashMap<>();
        for(String userId : users) {
            splitsMap.put(userId, 200.0);
        }
        splitWise.addExpenseToGroup(
                users.get(paidIndex),
                SplitType.EQUAL,
                1000.0,
                groupId,
                splitsMap
        );
    }
}
