package ParkingLotSystem.Factory;

import ParkingLotSystem.Adapter.CardPayment;
import ParkingLotSystem.Adapter.CashPayment;
import ParkingLotSystem.Adapter.PaymentStrategy;
import ParkingLotSystem.Adapter.RazorpayPayment;
import ParkingLotSystem.Enums.PaymentMethod;

public class PaymentFactory {
//    private PaymentStrategy UPIpaymentStrategy;
//    private PaymentStrategy CARDpaymentStrategy;
//    private PaymentStrategy CASHpaymentStrategy;


    public static PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case GATEWAY:
                return new RazorpayPayment();
            case CARD:
                return new CardPayment();
            case CASH:
                return new CashPayment();
            default:
                throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }
}
