package com.solvd.laba;

import com.solvd.laba.customexceptions.DuplicateBookingException;
import com.solvd.laba.customexceptions.EmptyPassengerException;
import com.solvd.laba.myenums.*;
import com.solvd.laba.myinterfaces.FlightInterface;
import com.solvd.laba.myinterfaces.UniqueIdInterface;


public final class Flight implements UniqueIdInterface, FlightInterface {

    private final int ID;
    public static int numFlights = 0;
    private String flightNum, departFrom, arriveTo;
    private PlaneType planeType;
    private AirplaneBase plane;
    private Passenger[] passengers;
    private int seatsAvailable, seatsInFirstCount = 0, seatsInBusinessCount = 0, seatsInEconCount = 0, numPassengers = 0;

    //Flight Object constructor 
    public Flight(AirplaneBase myPlane, String departFrom, String arriveTo) {
        this.plane = myPlane;
        this.planeType = myPlane.getPlaneType();
        this.seatsAvailable = planeType.TOTAL_SEATS;
        this.flightNum = planeType.CLASSIFICATION + plane.getId();
        this.departFrom = departFrom;
        this.arriveTo = arriveTo;
        this.passengers = new Passenger[planeType.TOTAL_SEATS];
        this.ID = ++numFlights;
    }

    //returns current number of Flight Objects instantiated
    public static final int getNumFlights() {
        return numFlights;
    }

    //adds Passenger to Flight Object by storing the Passenger in the Flight Object's Passenger array
    //this method is called inside bookSeat method which already checks for Seat availability
    //bookSeat method will only call this method if a Seat is available so a Passenger will always be able to be added to the array when this method is called
    private final void addPassenger(Passenger person) {
        passengers[numPassengers++] = person;
    }



    ////////////// FlightInterface Overrides /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns true if Flight Object has any availability regardless of SeatType
    @Override
    public final boolean getSeatsAvailable() {
        return seatsAvailable > 0;
    }

    //returns true if there are available Seats for given SeatType
    @Override
    public final boolean getSeatsAvailable(SeatType seatType) {
        switch (seatType) {
            case FIRST_CLASS:
                return seatsInFirstCount < planeType.SEATS_IN_FIRST;
            case BUSINESS_CLASS:
                return seatsInBusinessCount < planeType.SEATS_IN_BUSINESS;
            case ECONOMY_CLASS:
                return seatsInEconCount < planeType.SEATS_IN_ECON;
            default:
                break;
        }
        return false;
    }

    //adds a Passenger to the Flight Object Passenger array and assigns Passenger a Seat based on SeatType
    @Override
    public final boolean bookSeat(Passenger person, SeatType seatType) throws DuplicateBookingException {
        for (int i = 0; i < passengers.length; i++) {
            if (person.equals(passengers[i]))
                throw new DuplicateBookingException("Passenger has already booked a seat!");
        }
        if (getSeatsAvailable(seatType)) {
            switch (seatType) {
                case FIRST_CLASS:
                    plane.assignSeat(person, seatsInFirstCount, seatType);
                    seatsInFirstCount++;
                    break;

                case BUSINESS_CLASS:
                    plane.assignSeat(person, seatsInBusinessCount, seatType);
                    seatsInBusinessCount++;
                    break;

                case ECONOMY_CLASS:
                    plane.assignSeat(person, seatsInEconCount, seatType);
                    seatsInEconCount++;
                    break;
                    
                default:
                    return false;
            }
            seatsAvailable--;
            addPassenger(person);
            return true;
        }
        return false;
    }

    //returns Flight Object's number of Passengers
    @Override
    public final int getNumPassengers() {
        return numPassengers;
    }

    //returns Passenger array from Flight Object if array is not empty
    @Override
    public final Passenger[] getPassengers() throws EmptyPassengerException {
        if (numPassengers == 0)
            throw new EmptyPassengerException("Flight does not contain any passengers!");
        return passengers;
    }

    //returns Flight Object general information as a String
    @Override
    public final String flightInfo() {
        String flightNumber = "Flight#: " + flightNum;
        String departingFrom = "Departing from: " + departFrom;
        String arrivingTo = "Arriving to: " + arriveTo;
        String passengers = "Number of passengers: " + numPassengers;
        String planeInfo = String.format("Plane: %1$s %2$s", planeType.COMPANY, planeType.CLASSIFICATION);
        String myString = String.format("%1$s\n%2$s\n%3$s\n%4$s\n%5$s", flightNumber, departingFrom, arrivingTo, passengers, planeInfo);
        return myString;
    }



    ////////////// UniqueIdInterface Overrides ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //returns Flight Object's ID#
    @Override
    public final int getId() {
        return ID;
    }



    ////////////// Object Class Overrides ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns a String of a Flight Object as the Object's "flightNum"
    @Override
    public final String toString() {
        return "Flight#: " + flightNum;
    }

    //compares 2 Flight Object's by comparing their Object's hashcodes
    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Flight) {
            Flight cast = (Flight)obj;
            return this.hashCode() == cast.hashCode();
        }
        return false;
    }

    //Flight Object's hashcode is set to its ID#
    @Override
    public final int hashCode() {
        return this.getId();
    }
    
}
