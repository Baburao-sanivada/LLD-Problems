package ParkingLotSystem.DTO;

public class ExitResponse {
    private boolean success;
    private String message;
    private double amount;
    private String receiptId;

    public ExitResponse(boolean success, String message, double amount, String receiptId) {
        this.success = success;
        this.message = message;
        this.amount = amount;
        this.receiptId = receiptId;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
