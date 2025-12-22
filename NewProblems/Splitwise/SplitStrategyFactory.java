package Splitwise;

import Splitwise.Models.SplitType;
import Splitwise.Strategy.EqualSplit;
import Splitwise.Strategy.ExactSplit;
import Splitwise.Strategy.PercentageSplit;
import Splitwise.Strategy.SplitStrategy;

import java.util.Map;

public class SplitStrategyFactory {

    public static SplitStrategy getSplitStrategy(SplitType splitType,
                                                 Map<String, Double> userInputMap,
                                                 String paidBy,
                                                 double totalAmount) {
        switch (splitType) {
            case EQUAL:
                return new EqualSplit(userInputMap,totalAmount, paidBy);
            case EXACT:
                return new ExactSplit(userInputMap, totalAmount, paidBy);
            case PERCENT:
                return new PercentageSplit(userInputMap, totalAmount, paidBy);
            default:
                throw new IllegalArgumentException("Invalid split type: " + splitType);
        }
    }
}
