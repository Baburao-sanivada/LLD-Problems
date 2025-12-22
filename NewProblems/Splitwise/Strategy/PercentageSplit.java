package Splitwise.Strategy;

import Splitwise.Models.Split;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class PercentageSplit extends SplitStrategy{

    public PercentageSplit(Map<String, Double> userInput, double totalAmount, String paidBy) {
        super(userInput, paidBy, totalAmount);

    }

    @Override
    public List<Split> getSplitInfo() {
        List<Split> result = new CopyOnWriteArrayList<>();
        for(String userId: this.userInputMap.keySet()){
            double percentage = this.userInputMap.get(userId);
            double splitAmount = (percentage / 100) * this.totalAmount;
            result.add(new Split(userId, splitAmount));
        }
        return result;
    }
}
