package Splitwise.Strategy;

import Splitwise.Models.Split;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExactSplit extends SplitStrategy{

    public ExactSplit(Map<String, Double> userInput, double totalAmount, String paidBy) {
        super(userInput, paidBy, totalAmount);

    }

    @Override
    public List<Split> getSplitInfo() {
        List<Split> result = new java.util.concurrent.CopyOnWriteArrayList<>();
        for(String userId: this.userInputMap.keySet()){
            double splitAmount = this.userInputMap.get(userId);
            result.add(new Split(userId, splitAmount));
        }
        return result;
    }
}
