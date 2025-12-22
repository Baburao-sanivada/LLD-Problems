package ParkingLotSystem.Controller;

import ParkingLotSystem.DTO.EntryResponse;
import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Models.ParkingSlot;
import ParkingLotSystem.Models.Ticket;
import ParkingLotSystem.Services.SlotService;
import ParkingLotSystem.Services.TicketService;

import java.time.LocalDateTime;
import java.util.Optional;

public class EntryController {
    private SlotService slotService;
    private TicketService ticketService;


    public EntryController(SlotService slotService, TicketService ticketService) {
        this.slotService = slotService;
        this.ticketService = ticketService;
    }


    public EntryResponse enterVehicle(String vehicleNumber, VehicleType vehicleType) {

        // Check for available slot
        Optional<ParkingSlot> slot = slotService.allocateSlot(vehicleType);
        if (slot.isEmpty()) {
            // No available slot
            return new EntryResponse(false,null, null ,"Sorry No Lot available" ); // or throw an exception
        }

        // Slot allocated, now generate ticket
        Ticket ticket = ticketService.generateTicket(vehicleType, slot.get().getId().toString(), LocalDateTime.now(), vehicleNumber);

        return new EntryResponse(true,ticket.getTicketId(), slot.get().getId().toString(),"Entry Successful" );
    }
}
