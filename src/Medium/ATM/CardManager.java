package Medium.ATM;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CardManager {
    private final Map<String,Card> cards;
    public CardManager(){
        cards = new ConcurrentHashMap<>();
    }

    public void addCard(Card card){
        cards.put(card.getCardNo(),card);
    }

    public void removeCard(Card card){
        cards.remove(card.getCardNo());
    }

    public Card validateCardDetails(String cardNo,String PIN){
        return cards.getOrDefault(cardNo,null);
    }
}
