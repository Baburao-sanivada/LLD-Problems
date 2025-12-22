package ParkingLotSystem.Controller;

import ParkingLotSystem.DTO.ExitResponse;
import ParkingLotSystem.Enums.PaymentMethod;
import ParkingLotSystem.Models.Reciept;
import ParkingLotSystem.Models.Ticket;
import ParkingLotSystem.Services.PaymentService;
import ParkingLotSystem.Services.RecieptService;
import ParkingLotSystem.Services.SlotService;
import ParkingLotSystem.Services.TicketService;

import java.time.LocalDateTime;

public class ExitController {
    private SlotService slotService;
    private RecieptService recieptService;
    private TicketService ticketService;
    private PaymentService paymentService;

    public ExitController(SlotService slotService, RecieptService recieptService, TicketService ticketService, PaymentService paymentService) {
        this.slotService = slotService;
        this.recieptService = recieptService;
        this.ticketService = ticketService;
        this.paymentService = paymentService;
    }

    public double calculateParkingFees(String ticketId){
        return 50.00;
    }

    public ExitResponse exitVehicle(String ticketId, PaymentMethod paymentMethod) {
        // Fetch the ticket
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket == null) {
            return new ExitResponse(false, "Invalid Ticket ID", 0, null);
        }

        // Calculate the parking fee
        double amount = calculateParkingFees(ticketId);
        // collect payment
        if(!paymentService.collectPayment(ticketId,amount,paymentMethod)){
            return new ExitResponse(false, "Payment Failed", amount , null);
        }
        // Process payment (Assuming payment is always successful for simplicity)
        Reciept receiptId = recieptService.generateReciept(ticket.getTicketId(), amount, LocalDateTime.now());

        slotService.releaseSlot(ticket.getSlotId());

        return new ExitResponse(true, "Payment Successful", amount, receiptId.getRecieptId());
    }
}
