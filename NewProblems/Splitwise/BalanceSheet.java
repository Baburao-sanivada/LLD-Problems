package NewProblems.Splitwise;

import NewProblems.Splitwise.Models.Expense;
import NewProblems.Splitwise.Models.Split;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    Map<String, Map<String, Map<String, Double>>> balances;
    Map<String, Map<String,Double>> userBalances;
    Set<String> simpleDebtEnabledGroups;

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
        simpleDebtEnabledGroups = ConcurrentHashMap.newKeySet();
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

//        if(!userBalances.containsKey(paidBy)){
//            userBalances.put(paidBy, new ConcurrentHashMap<>());
//        }

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

        // Check if simple debt simplification is enabled for this group
        if(simpleDebtEnabledGroups.contains(groupId)){
            simplifyDebt(groupId);
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

    private void simplifyDebt(String groupId){
        // Step 1: Calculate net balances for each user
        Map<String, Double> netBalances = new ConcurrentHashMap<>();
        Map<String, Map<String, Double>> groupBalances = balances.get(groupId);
        for(String userId : groupBalances.keySet()) {
            double netBalance = 0.0;
            Map<String, Double> userBalanceMap = groupBalances.get(userId);
            for (double amount : userBalanceMap.values()) {
                netBalance += amount;
            }
            netBalances.put(userId, netBalance);
        }

        // Step 2: Create lists of creditors and debtors, sorted by amount (descending)
        List<Map.Entry<String, Double>> creditorsList = new ArrayList<>();
        List<Map.Entry<String, Double>> debtorsList = new ArrayList<>();

        for(Map.Entry<String, Double> entry : netBalances.entrySet()) {
            String userId = entry.getKey();
            double netBalance = entry.getValue();
            if (netBalance > 0) {
                creditorsList.add(new AbstractMap.SimpleEntry<>(userId, netBalance));
            } else if (netBalance < 0) {
                debtorsList.add(new AbstractMap.SimpleEntry<>(userId, -netBalance));
            }
        }

        // Sort in descending order by amount
        creditorsList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        debtorsList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // Step 3: Settle debts using index pointers
        int creditorIdx = 0;
        int debtorIdx = 0;

        List<Map.Entry<String, Map.Entry<String, Double>>> settlements = new ArrayList<>(); // Store [debtorId, creditorId, amount]

        while (debtorIdx < debtorsList.size() && creditorIdx < creditorsList.size()) {
            String debtorId = debtorsList.get(debtorIdx).getKey();
            double debtAmount = debtorsList.get(debtorIdx).getValue();

            String creditorId = creditorsList.get(creditorIdx).getKey();
            double creditAmount = creditorsList.get(creditorIdx).getValue();

            double settlementAmount = Math.min(debtAmount, creditAmount);

            // Store settlement
            settlements.add(new AbstractMap.SimpleEntry<>(
                    debtorId,
                    new AbstractMap.SimpleEntry<>(creditorId, settlementAmount)
            ));

            // Update amounts
            debtorsList.get(debtorIdx).setValue(debtAmount - settlementAmount);
            creditorsList.get(creditorIdx).setValue(creditAmount - settlementAmount);

            // Move to next debtor if current one is settled
            if (debtorsList.get(debtorIdx).getValue() == 0) {
                debtorIdx++;
            }
            // Move to next creditor if current one is settled
            if (creditorsList.get(creditorIdx).getValue() == 0) {
                creditorIdx++;
            }
        }

        // Step 4: Clear existing balances and rebuild with simplified settlements
        for(String userId : groupBalances.keySet()) {
            groupBalances.get(userId).clear();
        }


        // Step 5: Update groupBalances with only the simplified settlements
        for (Map.Entry<String, Map.Entry<String, Double>> settlement : settlements) {
            String debtorId = settlement.getKey();
            String creditorId = settlement.getValue().getKey();
            double amount = settlement.getValue().getValue();

            // Debtor owes creditor
            groupBalances.get(creditorId).put(debtorId, groupBalances.get(creditorId).getOrDefault(debtorId, 0.0) + amount);
            groupBalances.get(debtorId).put(creditorId, groupBalances.get(debtorId).getOrDefault(creditorId, 0.0) - amount);
        }
    }



    public void enableSimpleDebtCalculationForGroup(String groupId){
        simpleDebtEnabledGroups.add(groupId);
    }

    public void settleBalance(String groupId, String fromUserId, String toUserId, double amount){
        if(!balances.containsKey(groupId)){
            System.out.println("No balances for group: " + groupId);
            return;
        }
        Map<String, Map<String, Double>> groupBalances = balances.get(groupId);
        if(!groupBalances.containsKey(toUserId) || !groupBalances.get(toUserId).containsKey(fromUserId)){
            System.out.println("No balance to settle between " + fromUserId + " and " + toUserId + " in group " + groupId);
            return;
        }
        double currentBalance = groupBalances.get(toUserId).get(fromUserId);
        if(currentBalance < amount){
            System.out.println("Settlement amount exceeds the owed amount. Current owed amount: " + currentBalance);
            return;
        }

        // Update group balances
        groupBalances.get(toUserId).put(fromUserId, currentBalance - amount);
        groupBalances.get(fromUserId).put(toUserId, groupBalances.get(fromUserId).getOrDefault(toUserId, 0.0) + amount);

        // Update user balances
        userBalances.get(toUserId).put(fromUserId, userBalances.get(toUserId).get(fromUserId) - amount);
        userBalances.get(fromUserId).put(toUserId, userBalances.get(fromUserId).getOrDefault(toUserId, 0.0) + amount);

        System.out.println("Settled " + amount + " from " + fromUserId + " to " + toUserId + " in group " + groupId);

    }
}
