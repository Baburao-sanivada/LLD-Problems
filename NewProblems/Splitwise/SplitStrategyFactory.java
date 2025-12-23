package NewProblems.Splitwise;

import NewProblems.Splitwise.Models.SplitType;
import NewProblems.Splitwise.Strategy.EqualSplit;
import NewProblems.Splitwise.Strategy.ExactSplit;
import NewProblems.Splitwise.Strategy.PercentageSplit;
import NewProblems.Splitwise.Strategy.SplitStrategy;

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
