package Medium.CarRentalSystem;

public class CarRentalDemo {
    public static void run() {
        CarRentalSystem carRentalSystem = CarRentalSystem.getInstance();

        User user1 = new User("1","Babu","Babu@gmail.com","9390639493","DlNo1");
        User user2 = new User("2","Kiran","Babu@gmail.com","9390639493","DlNo1");
        User user3 = new User("3","Vinay","Babu@gmail.com","9390639493","DlNo1");

        carRentalSystem.addUser(user1);
        carRentalSystem.addUser(user2);
        carRentalSystem.addUser(user3);

        carRentalSystem.addCar(new Car("1","Toyota", "Camry", 2022, "ABC123", 500));
        carRentalSystem.addCar(new Car("2","Honda", "Civic", 2021, "XYZ789", 450));
        carRentalSystem.addCar(new Car("3","Ford", "Mustang", 2023, "DEF456", 800));
        carRentalSystem.addCar(new Car("4","TATA", "NEXON", 2023, "DEF456", 900));

        carRentalSystem.showCarsAvailableOnDate("06-Sep");

        carRentalSystem.bookCar(user1,"1","06-Sep");
        carRentalSystem.bookCar(user1,"4","06-Sep");

        carRentalSystem.showCarsAvailableOnDate("06-Sep");
        carRentalSystem.filterByPrice(500,1000,"06-Sep");

        carRentalSystem.showCarsAvailableOnDate("09-Oct");
        carRentalSystem.bookCar(user1,"4","09-Oct");


    }
}
