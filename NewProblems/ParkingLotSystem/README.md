1. Clarify Requirements
- Multi floor parking lot
- Supports different types of vehicles (Car, Bike, Van)
- Multiple Entry points
- Handle Concurrency

2. User Journey
Enter -> Fetch Parking slot if Available -> Generate token -> Park Vehicle -> Exit -> 
Calculate Fare -> Generate Receipt

3. Core Entities
ParkingSlot -> Id, Floor, SlotAvailable, VehicleType
ParkingTicket -> Id, Vehicle Number, Vehicle Type, In time
Receipt -> Id, Fare, Payment Mode, Vehicle Number, Token Id
Floor -> Floor Id, Capacity
Vehicle Type Enum -> Car, VAN, BIKE


4. Outline Interfaces & Abstract Classes
ParkingLot - checkAvailability(VehicleType) - checks with slots manager , getAvailableSlot(VehicleType) - checks with slots manager, BookSlot(Vehicle Details) - checks with slots manager,
             getParkingTicketDetails(Ticket ticketId) - TicketService, GenerateReciept(TicketId, PricingRule ) - ReceiptService
Floor Manager - Holds floor details
Slot Manager - Manages Slots and holds booked slots - Also holds floor manager instance inside
ParkingTicketService - Generates parking ticket - Map with TicketId and Obj
ReceiptService - Generates receipt by passing ticket details and Pricing RUle, Payment type


5. Implement Main Methods



6. Explain Design Choices
   Open/Closed Principle (OCP): The system must allow the addition of new Vehicle types or new 
   PricingStrategy logic (e.g., weekend rates) without modifying the compiled ParkingLot class.
