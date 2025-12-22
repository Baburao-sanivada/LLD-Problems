package Splitwise;

import Splitwise.Models.Expense;
import Splitwise.Models.Split;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    Map<String, Map<String, Map<String, Double>>> balances;
    Map<String, Map<String,Double>> userBalances;

    private static BalanceSheet instance = null;

    public static BalanceSheet getInstance(){
        if(instance == null){
            synchronized (BalanceSheet.class){
                if(instance == null){
                    instance = new BalanceSheet();
                }
            }
        }
        return instance;
    }

    private BalanceSheet(){
        balances = new ConcurrentHashMap<>();
        userBalances = new ConcurrentHashMap<>();
    }

    public void updateBalancesForExpense(Expense expense){
        String paidBy = expense.getPaidByUserId();
        List<Split> splits = expense.getSplitList();
        double totalAmount = expense.getAmount();
        String groupId = expense.getGroupId();

        if(!balances.containsKey(groupId)){
            balances.put(groupId, new ConcurrentHashMap<>());
        }
        Map<String, Map<String, Double>> groupBalances = balances.get(groupId);

        if(!groupBalances.containsKey(paidBy)){
            groupBalances.put(paidBy, new ConcurrentHashMap<>());
        }

        if(!userBalances.containsKey(paidBy)){
            userBalances.put(paidBy, new ConcurrentHashMap<>());
        }

        for(Split split: splits){
            String owedBy = split.getUserId();
            double amountOwed = split.getAmount();

            if(owedBy.equals(paidBy)){
                continue; // No need to update balance if the user paid for themselves
            }

            // Update group balances
            groupBalances.get(paidBy).put(owedBy, groupBalances.get(paidBy).getOrDefault(owedBy, 0.0) + amountOwed);
            if(!groupBalances.containsKey(owedBy)){
                groupBalances.put(owedBy, new ConcurrentHashMap<>());
            }
            groupBalances.get(owedBy).put(paidBy, groupBalances.get(owedBy).getOrDefault(paidBy, 0.0) - amountOwed);

            // Update User balances
            userBalances.get(paidBy).put(owedBy, userBalances.get(paidBy).getOrDefault(owedBy, 0.0) + amountOwed);
            if(!userBalances.containsKey(owedBy)) {
                userBalances.put(owedBy, new ConcurrentHashMap<>());
            }
            userBalances.get(owedBy).put(paidBy, userBalances.get(owedBy).getOrDefault(paidBy, 0.0) - amountOwed);

        }
    }

    public void showUserBalances(String userId){
        if(!userBalances.containsKey(userId)){
            System.out.println("No balances for user: " + userId);
            return;
        }
        Map<String, Double> balancesMap = userBalances.get(userId);
        System.out.println("Balances for user: " + userId);
        for(Map.Entry<String, Double> entry : balancesMap.entrySet()){
            String otherUserId = entry.getKey();
            double amount = entry.getValue();
            if(amount > 0){
                System.out.println(otherUserId + " owes " + userId + ": " + amount);
            } else if(amount < 0){
                System.out.println(userId + " owes " + otherUserId + ": " + (-amount));
            }
        }
    }

    public void showGroupBalances(String groupId){
        if(!balances.containsKey(groupId)){
            System.out.println("No balances for group: " + groupId);
            return;
        }
        Map<String, Map<String, Double>> groupBalances = balances.get(groupId);
        System.out.println("Balances for group: " + groupId);
        for(String userId : groupBalances.keySet()){
            Map<String, Double> userBalanceMap = groupBalances.get(userId);
            for(Map.Entry<String, Double> entry : userBalanceMap.entrySet()){
                String otherUserId = entry.getKey();
                double amount = entry.getValue();
                if(amount > 0){
                    System.out.println(otherUserId + " owes " + userId + ": " + amount);
                }
            }
        }
    }
}
