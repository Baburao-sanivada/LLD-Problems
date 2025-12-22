package ParkingLotSystem.DTO;

public class EntryResponse {
    private boolean success;
    private String ticketId;
    private String slotId;
    private String message;

    public EntryResponse(boolean success, String ticketId, String slotId, String message) {
        this.success = success;
        this.ticketId = ticketId;
        this.slotId = slotId;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getTicketId() {
        return ticketId;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getMessage() {
        return message;
    }

}
