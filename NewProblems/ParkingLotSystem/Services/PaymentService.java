package ParkingLotSystem.Services;

import ParkingLotSystem.Adapter.PaymentStrategy;
import ParkingLotSystem.Enums.PaymentMethod;
import ParkingLotSystem.Enums.PaymentStatus;
import ParkingLotSystem.Factory.PaymentFactory;
import ParkingLotSystem.Models.Payment;
import ParkingLotSystem.Repository.PaymentRepository;

import java.time.LocalDateTime;

public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public boolean collectPayment(String ticketId, double amount, PaymentMethod paymentMethod) {
        PaymentStrategy paymentStrategy = PaymentFactory.getPaymentStrategy(paymentMethod);

        if(paymentStrategy.processPayment(ticketId, amount)) {
            Payment payment = new Payment(amount, LocalDateTime.now(),paymentMethod, PaymentStatus.COMPLETED, ticketId);
            paymentRepository.save(payment);
            return true;
        }
        return false;
    }

}
