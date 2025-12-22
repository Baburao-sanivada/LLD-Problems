package ParkingLotSystem.Repository;

import ParkingLotSystem.Models.Ticket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketRepository {
    private Map<String, Ticket> tickets = new ConcurrentHashMap<>();

    public Ticket save(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public Ticket findById(String ticketId) {
        return tickets.get(ticketId);
    }

    public void deleteById(String ticketId) {
        tickets.remove(ticketId);
    }

    public void clearAll() {
        tickets.clear();
    }
}
