package Medium.ATM;


public class Card {
    private final String cardNo;
    private final String pin;
    public Card(String cardNo,String pin){
        this.cardNo = cardNo;
        this.pin = pin;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getPin() {
        return pin;
    }
}
