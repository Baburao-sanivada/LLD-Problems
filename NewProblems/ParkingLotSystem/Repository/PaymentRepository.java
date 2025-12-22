package ParkingLotSystem.Repository;

import ParkingLotSystem.Models.Payment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentRepository {
    private Map<String, Payment> payments = new ConcurrentHashMap<>();

    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }

    public Payment findById(String paymentId) {
        return payments.get(paymentId);
    }


    public void deleteById(String paymentId) {
        payments.remove(paymentId);
    }

    public void clearAll() {
        payments.clear();
    }

}
