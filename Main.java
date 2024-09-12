package BusTicketSystem;

import BusTicketSystem.Exceptions.InvalidBusException;
import BusTicketSystem.Exceptions.TicketAlreadyExistException;
import BusTicketSystem.Exceptions.TicketDoesNotPresentException;
import BusTicketSystem.Service.Bus;
import BusTicketSystem.Service.BusTicketManagement;
import BusTicketSystem.Service.Passenger;
import BusTicketSystem.Service.SeniorCitizen;
import BusTicketSystem.Service.Ticket;

import javax.naming.LimitExceededException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create BusTicketManagement system
        BusTicketManagement management = new BusTicketManagement();

        // Create 3 buses
        Bus busA = new Bus(1, "StationA", "StationB");
        Bus busB = new Bus(2, "StationC", "StationD");
        Bus busC = new Bus(3, "StationE", "StationF");

        // Add buses to the management system
        management.setBus(busA);
        management.setBus(busB);
        management.setBus(busC);

        // Create 5 passengers
        Passenger p1 = new Passenger("P1", "Male", "StationA", "StationB");
        Passenger p2 = new Passenger("P2", "Female", "StationC", "StationD");
        Passenger p3 = new Passenger("P3", "Male", "StationE", "StationF");
        Passenger p4 = new Passenger("P4", "Female", "StationA", "StationB");
        Passenger p5 = new Passenger("P5", "Male", "StationC", "StationD");

        // Create 2 senior citizens
        SeniorCitizen sc1 = new SeniorCitizen("SC1", "Male", "StationA", "StationB", 70);
        SeniorCitizen sc2 = new SeniorCitizen("SC2", "Female", "StationC", "StationD", 65);

        // Create tickets and assign passengers/senior citizens to buses randomly
        try {
            Ticket t1 = new Ticket(management.getT().generateTicketNo(1), 100, p1);
            management.issueTicket(1, t1);

            Ticket t2 = new Ticket(management.getT().generateTicketNo(2), 100, p2);
            management.issueTicket(2, t2);

            Ticket t3 = new Ticket(management.getT().generateTicketNo(3), 100, p3);
            management.issueTicket(3, t3);

            Ticket t4 = new Ticket(management.getT().generateTicketNo(1), 100, p4);
            management.issueTicket(1, t4);

            Ticket t5 = new Ticket(management.getT().generateTicketNo(2), 100, p5);
            management.issueTicket(2, t5);

            Ticket t6 = new Ticket(management.getT().generateTicketNo(1), 50, sc1);
            management.issueTicket(1, t6);

            Ticket t7 = new Ticket(management.getT().generateTicketNo(2), 50, sc2);
            management.issueTicket(2, t7);
        } catch (TicketAlreadyExistException | LimitExceededException e) {
            System.out.println(e.getMessage());
        }


        try {
            // Get count of senior citizens in each bus
            HashMap<Integer, Integer> seniorCitizenCount = management.getCountOfSeniorCitizens();
            System.out.println("Senior Citizen Count by Bus:");
            for (Map.Entry<Integer, Integer> entry : seniorCitizenCount.entrySet()) {
                System.out.println("Bus No: " + entry.getKey() + ", Count: " + entry.getValue());
            }

            // Get passengers by gender
            ArrayList<Passenger> malePassengers = management.getPassengersByGender("Male");
            System.out.println("\nMale Passengers:");
            for (Passenger passenger : malePassengers) {
                System.out.println(passenger);
            }

            ArrayList<Passenger> femalePassengers = management.getPassengersByGender("Female");
            System.out.println("\nFemale Passengers:");
            for (Passenger passenger : femalePassengers) {
                System.out.println(passenger);
            }

            // Get count of passengers between two stations
            long passengerCount = management.getCountOfPassengers("StationA", "StationB");
            System.out.println("\nNumber of passengers from StationA to StationB: " + passengerCount);

            // Cancel a ticket
            System.out.println("\nCanceling Ticket for P4 (Bus 1, Ticket 4)...");
            boolean canceled = management.cancelTicket(1, 4);
            if (canceled) {
                System.out.println("Ticket canceled successfully.");
            }

            // Show updated list of male passengers
            malePassengers = management.getPassengersByGender("Male");
            System.out.println("\nUpdated Male Passengers:");
            for (Passenger passenger : malePassengers) {
                System.out.println(passenger);
            }
        } catch (TicketDoesNotPresentException | InvalidBusException e) {
            System.out.println(e.getMessage());
        }
    }
}


//import BusTicketSystem.Service.Bus;
//import BusTicketSystem.Service.BusTicketManagement;
//import BusTicketSystem.Service.Passenger;
//import BusTicketSystem.Service.Ticket;
//import BusTicketSystem.Utility.BusManagementInterface;
//
//import java.util.ArrayList;
//import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Enter the number of tickets you want to bus :");
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        String name, source, destination;
//        String gender;
//        double ticketCost;
//        ArrayList<Passenger> passengers = new ArrayList<>();
//        ArrayList<Ticket> tickets = new ArrayList<>();
//        for(int i = 0; i < n; i++){
//            System.out.println("Enter passenger name :");
//            name = input.next();
//            System.out.println("Passenger gender: ");
//            gender = input.next();
//            System.out.println("Source: ");
//            source = input.next();
//            System.out.println("Destination: ");
//            destination = input.next();
//            Passenger passenger = new Passenger(name, gender, source, destination);
//            passengers.add(passenger);
//            ticketCost = Math.random() * 1000;
//            Ticket ticket = new Ticket((i*10), ticketCost, passenger);
//            tickets.add(ticket);
//        }
//
//        System.out.println("Tickets created for " + n + " users");
//        tickets.forEach(System.out::println);
//    }
//}
