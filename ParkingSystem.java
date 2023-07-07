import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Vehicle {
    private String licensePlate;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public abstract String getType();
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }


    public String getType() {
        return "Car";
    }
}

class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate);
    }


    public String getType() {
        return "Truck";
    }
}

class ParkingSystem {
    private int totalSlots;
    private int availableSlots;
    private List<Vehicle> parkedVehicles;

    public ParkingSystem(int totalSlots) {
        this.totalSlots = totalSlots;
        this.availableSlots = totalSlots;
        this.parkedVehicles = new ArrayList<>();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Park a vehicle");
            System.out.println("2. Remove a vehicle");
            System.out.println("3. View parked vehicles");
            System.out.println("4. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    parkVehicle(sc);
                    break;
                case 2:
                    removeVehicle(sc);
                    break;
                case 3:
                    viewParkedVehicles();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void parkVehicle(Scanner sc) {
        if (availableSlots == 0) {
            System.out.println("Sorry, there are no available parking slots.");
            return;
        }

        System.out.println("Enter the license plate number of the vehicle:");
        String licensePlate = sc.next();

        Vehicle vehicle;
        System.out.println("Enter the type of the vehicle (1. Car, 2. Truck):");
        int vehicleType = sc.nextInt();
        switch (vehicleType) {
            case 1:
                vehicle = new Car(licensePlate);
                break;
            case 2:
                vehicle = new Truck(licensePlate);
                break;
            default:
                System.out.println("Invalid vehicle type. Please try again.");
                return;
        }

        parkedVehicles.add(vehicle);
        availableSlots--;
        System.out.println("Vehicle parked successfully. Available slots: " + availableSlots);
    }

    public void removeVehicle(Scanner sc) {
        if (availableSlots == totalSlots) {
            System.out.println("There are no parked vehicles.");
            return;
        }

        System.out.println("Enter the license plate number of the vehicle to be removed:");
        String licensePlate = sc.next();
        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : parkedVehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            parkedVehicles.remove(vehicleToRemove);
            availableSlots++;
            System.out.println("Vehicle removed successfully. Available slots: " + availableSlots);
        } else {
            System.out.println("The vehicle is not parked here.");
        }
    }

    public void viewParkedVehicles() {
        if (availableSlots == totalSlots) {
            System.out.println("There are no parked vehicles.");
            return;
        }

        System.out.println("Parked vehicles:");
        for (Vehicle vehicle : parkedVehicles) {
            System.out.println(vehicle.getLicensePlate() + " - " + vehicle.getType());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the total number of parking slots:");
        int totalSlots = sc.nextInt();

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);
        parkingSystem.start();
    }
}
