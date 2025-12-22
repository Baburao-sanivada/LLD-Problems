package ParkingLotSystem;

import ParkingLotSystem.Controller.AdminController;
import ParkingLotSystem.Controller.EntryController;
import ParkingLotSystem.Controller.ExitController;
import ParkingLotSystem.DTO.EntryResponse;
import ParkingLotSystem.DTO.ExitResponse;
import ParkingLotSystem.Enums.PaymentMethod;
import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Models.Reciept;
import ParkingLotSystem.Models.Ticket;
import ParkingLotSystem.Repository.*;
import ParkingLotSystem.Services.*;

import java.util.Map;

public class ParkingLotSimulator {

    public static void main(String[] args) {
        //repository objects
        SlotRepository slotRepository = new SlotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        FloorRepository floorRepository = new FloorRepository();
        PricingRuleRepository pricingRuleRepository = new PricingRuleRepository();
        RecieptRepository recieptRepository = new RecieptRepository();
        PaymentRepository paymentRepository = new PaymentRepository();

        //create service objects
        SlotService slotService = new SlotService(slotRepository);
        TicketService ticketService = new TicketService(ticketRepository);
        FloorService floorService = new FloorService(floorRepository);
        PricingService pricingService = new PricingService(pricingRuleRepository);
        RecieptService recieptService = new RecieptService(recieptRepository);
        PaymentService paymentService = new PaymentService(paymentRepository);
        AdminService adminService = new AdminService(slotService, pricingService, floorService);

        // create controller objects
        EntryController entryController = new EntryController(slotService, ticketService);
        ExitController exitController = new ExitController(slotService, recieptService,ticketService , paymentService);
        AdminController adminController = new AdminController(adminService);

        System.out.println("\n ============== Initialising Parking Lot ================ \n");
        initializeBasicDetails(adminController);

        // Simulate vehicle entry
        EntryResponse entryResponse1 = simulateVehicleEntry(entryController,"KA-01-HH-1234", VehicleType.CAR);
        if(entryResponse1.isSuccess()){
            printTicketDetails(ticketService,entryResponse1.getTicketId());
        }

        // Simulate bike Entry
        EntryResponse bikeEntryResponse1 = simulateVehicleEntry(entryController,"KA-01-HH-9999", VehicleType.BIKE);
        if(bikeEntryResponse1.isSuccess()){
            printTicketDetails(ticketService,bikeEntryResponse1.getTicketId());
        }

        EntryResponse bikeEntryResponse2 = simulateVehicleEntry(entryController,"KA-01-HH-2023", VehicleType.BIKE);

        if(bikeEntryResponse2.isSuccess()){
            printTicketDetails(ticketService,bikeEntryResponse2.getTicketId());
        }

        showCurrentFloorStatus(adminController,0);

        try{
            Thread.sleep(2000); // Sleep for 2 seconds to simulate time passage
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simulate vehicle exit
        System.out.println("\n============ Exiting Parking Lot ============\n");
        ExitResponse exitResponse1 = simulateVehicleExit(exitController,entryResponse1.getTicketId(), PaymentMethod.CARD);
        if(exitResponse1.isSuccess()){
            printRecieptDetails(recieptService,exitResponse1.getReceiptId());
        }
        ExitResponse exitResponse2 = simulateVehicleExit(exitController,bikeEntryResponse1.getTicketId(), PaymentMethod.GATEWAY);
        if(exitResponse2.isSuccess()){
            printRecieptDetails(recieptService,exitResponse2.getReceiptId());
        }


        showCurrentFloorStatus(adminController,0);


    }

    public static void printRecieptDetails(RecieptService recieptService, String recieptId){
        Reciept reciept = recieptService.getRecieptById(recieptId);
        System.out.println("\n=============Reciept Details: =============\n");
        System.out.println("Reciept ID: " + reciept.getRecieptId());
        System.out.println("Ticket ID: " + reciept.getTicketId());
        System.out.println("Amount Paid: " + reciept.getAmount());
        System.out.println("Exit Time: " + reciept.getExitTime());
        System.out.println("\n===============================\n");
    }

    public static void printTicketDetails(TicketService ticketService, String ticketId){
        Ticket ticket = ticketService.getTicket(ticketId);
        System.out.println("\n=============Ticket Details: =============\n");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Vehicle Number: " + ticket.getVehicleNumber());
        System.out.println("Vehicle Type: " + ticket.getVehicleType());
        System.out.println("Slot ID: " + ticket.getSlotId());
        System.out.println("Entry Time: " + ticket.getEntryTime());
        System.out.println("\n===============================\n");
    }

    public static EntryResponse simulateVehicleEntry(EntryController entryController,String vehicleNumber, VehicleType vehicleType){
        EntryResponse entryResponse = entryController.enterVehicle(vehicleNumber, vehicleType);
        if(entryResponse.isSuccess()){
            System.out.println("Vehicle entered successfully. Ticket ID: " + entryResponse.getTicketId() + ", Slot ID: " + entryResponse.getTicketId());
        }
        else{
            System.out.println("Error: " + entryResponse.getMessage());
        }


        return entryResponse;
    }

    public static ExitResponse simulateVehicleExit(ExitController exitController, String ticketId, PaymentMethod paymentMethod){
        ExitResponse exitStatus = exitController.exitVehicle(ticketId,paymentMethod);
        if(exitStatus.isSuccess()){
            System.out.println("Vehicle exited successfully. Reciept ID: " + exitStatus.getReceiptId() + ", Amount Paid: " + exitStatus.getAmount());
        }
        else{
            System.out.println("Error in vehicle exit for Ticket ID: " + ticketId + " Vehicle Does Not Exists");
        }
        return exitStatus;
    }

    public static void initializeBasicDetails(AdminController adminController){
        // Create 3 floors
        for (int i = 0; i < 3; i++) {
            adminController.addFloor(i);
        }

        // Add slots to floor 0
        adminController.addSlotsToFloor(0, VehicleType.BIKE, 1);
        adminController.addSlotsToFloor(0, VehicleType.CAR, 2);
        adminController.addSlotsToFloor(0, VehicleType.TRUCK, 3);

//        // Add slots to floor 1
//        adminController.addSlotsToFloor(1, VehicleType.CAR, 40);
//        adminController.addSlotsToFloor(1, VehicleType.TRUCK, 10);
//
//        // Add slots to floor 2
//        adminController.addSlotsToFloor(2, VehicleType.CAR, 35);
//        adminController.addSlotsToFloor(2, VehicleType.TRUCK, 15);

        // Initialize default pricing rules
        adminController.initializeDefaultPricingRules();

        System.out.println("[SERVICE] Parking lot initialization completed with initial floors and slots.");
        showCurrentFloorStatus(adminController,0);
    }

    public static void showCurrentFloorStatus(AdminController adminController,int floorNumber){
        // Can be implemented to show current status of parking lot
        Map<VehicleType,Integer> currentFloorStatus = adminController.getAvailableSlots(floorNumber);

        System.out.println("\n===============================\n");
        System.out.println("[SERVICE] Parking lot available slots:");
        for(VehicleType vehicleType: currentFloorStatus.keySet()){
            System.out.println("Vehicle Type: " + vehicleType + ", Available Slots: " + currentFloorStatus.get(vehicleType));
        }
        System.out.println("\n===============================\n");
    }

}
