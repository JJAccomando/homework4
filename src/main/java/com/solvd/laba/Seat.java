package com.solvd.laba;

import com.solvd.laba.customexceptions.NullPassengerException;
import com.solvd.laba.myinterfaces.SeatInterface;
import com.solvd.laba.myinterfaces.UniqueIdInterface;

public final class Seat implements UniqueIdInterface, SeatInterface {

    private final int SEAT_NUM;
    private final char SEAT_LETTER;
    private final int ID;
    private static int numTotalSeats = 0;
    private Passenger passenger;
    private boolean availabe = true;

    //Seat Object constructor
    public Seat(int seatNum, char seatLetter) {
        SEAT_NUM = seatNum;
        SEAT_LETTER = seatLetter;
        ID = ++numTotalSeats;
    }

    //returns total number of Seat Objects instantiated in program
    public final int getNumTotalSeats() {
        return numTotalSeats;
    }



    ////////////// SeatInterface Overrides /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns Passenger Object assigned to this Seat Object
    //throws exception if no Passenger was assigned
    @Override
    public final Passenger getPassenger() throws NullPassengerException {
        if (!availabe) 
            throw new NullPassengerException("Seat has no assigned passenger!");
        return passenger;
    }

    //returns true if Seat Object does not have assigned Passenger
    @Override
    public final boolean isAvailable() {
        return availabe;
    }

    //assigns Passenger to this Seat object and changes Seat availability to false 
    @Override
    public final boolean addPassenger(Passenger passenger) {
        this.passenger = passenger;
        return availabe = false;
    }

    //returns Seat row number
    @Override
    public final int getSeatNum() {
        return SEAT_NUM;
    }

    //returns Seat column letter
    @Override
    public final char getSeatLetter() {
        return SEAT_LETTER;
    }
    


    ////////////// UniqueIdInterface Overrides ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns Seat Object's ID#
    @Override
    public final int getId() {
        return ID;
    }



    ////////////// Object Class Overrides ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns a String of a Seat Object as that Object's Seat "SEAT_NUM", and "SEAT_LETTER"
    @Override
    public final String toString() {
        StringBuilder tempString = new StringBuilder();
        tempString.append(SEAT_NUM);
        tempString.append(SEAT_LETTER);
        return "Seat#: " + tempString.toString();
    }

    //compares 2 Seat Objects by comparing their Object's hashcodes
    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Seat) {
            Seat cast = (Seat)obj;
            return this.hashCode() == cast.hashCode();
        }
        return false;
    }

    //Seat Object's hashcode is set to its unique ID#
    @Override
    public final int hashCode() {
        return this.getId();
    }

}
