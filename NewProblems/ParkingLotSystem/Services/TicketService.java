package ParkingLotSystem.Services;

import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Models.Ticket;
import ParkingLotSystem.Repository.TicketRepository;

import java.time.LocalDateTime;

public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket getTicket(String id) {
        return ticketRepository.findById(id);
    }

    public Ticket generateTicket(VehicleType vehicleType, String slotId, LocalDateTime entryTime, String vehicleNumber) {
        Ticket ticket = new Ticket(vehicleNumber, entryTime, vehicleType,slotId);
        ticketRepository.save(ticket);
        return ticket;

    }
}
