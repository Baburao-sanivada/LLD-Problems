package Splitwise.Strategy;

import Splitwise.Models.Split;

import java.util.List;
import java.util.Map;

public abstract class SplitStrategy {
    Map<String, Double> userInputMap;
    String paidBy;
    double totalAmount;

    public abstract List<Split> getSplitInfo();

    public SplitStrategy(Map<String, Double> userInputMap, String paidBy, double totalAmount) {
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.userInputMap = userInputMap;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public double getTotalAmount(){
        return totalAmount;
    }
}
