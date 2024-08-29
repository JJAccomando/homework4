package com.solvd.laba;

import java.util.Scanner;
import com.solvd.laba.myenums.SeatType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {

    public static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.info("Program start successful.");
        Boeing737 plane = new Boeing737();
        Flight flight = new Flight(plane, "LAX", "JFK");
        Passenger person1 = new Passenger("John", "Smith");
        Passenger person2 = new Passenger("George", "Washington");
        SeatType seat = SeatType.ECONOMY_CLASS;
        
        try {
            flight.bookSeat(person1, seat); //Books passenger on flight
            flight.bookSeat(person1, seat); //Trys to book same passenger on same flight and throws DoubleBookException
        } catch (DoubleBookException dbe) {
            System.out.println(dbe.getMessage()); 
            logger.error("Failed to add passenger to flight. Error: {DoubleBookException}");
        }

        System.out.println(flight.getNumPassengers()); //Prints out 1 because only one passenger was added since second passenger was a duplicate

        try {
            Passenger person3 = flight.getPassenger(1); //Trys to return passenger at designated index and throws NullPassengerException
            System.out.println(person3.getFirstName()); //Passenger name will not print because exception was thrown
        } catch (NullPassengerException npe) {
            System.out.println(npe.getMessage()); 
            logger.error("Failed to retrieve passenger information. Error: {NullPassengerException}");
        }

        try {
            System.out.println(person1.getSeat().getId()); //Prints person1's seat number
            System.out.println(person2.getSeat().getId()); //Trys to print person2's seat number and throws NullSeatException
        } catch (NullSeatException nse) {
            System.out.println(nse.getMessage()); 
            logger.error("Failed to retrieve seat information. Error: {NullSeatException}");
        }

        String firstName = "";
        String lastName = "";
        try (Scanner input = new Scanner(System.in)) { //Creates scanner within try-catch with resources block which will close scanner when finished
            logger.warn("Receiving user input.");
            System.out.print("Enter first name: ");
            firstName = input.nextLine();
            System.out.print("Enter last name: ");
            lastName = input.nextLine();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
        logger.info("User input successful.");

        Passenger person4 = new Passenger(firstName, lastName);
        Luggage overweightBag = new Luggage(55);
        logger.warn("Over weight luggage created.");
        Luggage[] bags = new Luggage[15];
        int weight = 45;
        for (int i = 0; i < bags.length; i++) {
            Luggage bag = new Luggage(weight);
            bags[i] = bag;
        }

        try {
            person4.addBags(overweightBag); //Trys to add overweight bag to passenger and throws OversizeBagException
        } catch (OversizeBagException obe) {
            System.out.println("Cannot add bag to passenger: Bag is overweight!"); 
            logger.error("Failed to add passenger luggage. Error: {OversizeBagException}");
        } catch (OverLimitException ole) {
            System.out.println("Cannot add bag to passenger: Bag limit reached!");
        }

        System.out.println(person4.getNumBags()); //Displays 0 since no bags were added

        try {
            for (int i = 0; i < bags.length; i++) { //Trys to add more than maximum allowed bags to passenger and throws OverLimitException
                person4.addBags(bags[i]);
            }
        } catch (OversizeBagException obe) {
            System.out.println("Cannot add bag to passenger: Bag is overweight!");
        } catch (OverLimitException ole) {
            System.out.println("Cannot add bag to passenger: Bag limit reached!");
            logger.error("Failed to add passenger luggage. Error {OverLimitException}");
        }

        System.out.println(person4.getNumBags()); //Displays 10 because passenger has maximum number of bags
        logger.info("Program success. Shutting down...");
    }  
    
}
