package Splitwise.Strategy;

import Splitwise.Models.Split;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EqualSplit extends SplitStrategy{

    public EqualSplit(Map<String, Double> userInput, double totalAmount, String paidBy) {
        super(userInput, paidBy, totalAmount);

    }

    @Override
    public List<Split> getSplitInfo() {
        List<Split> result = new CopyOnWriteArrayList<>();
        double splitAmount = this.totalAmount / this.userInputMap.size();
        for(String userId: this.userInputMap.keySet()){
            result.add(new Split(userId, splitAmount));
        }
        return result;
    }
}
