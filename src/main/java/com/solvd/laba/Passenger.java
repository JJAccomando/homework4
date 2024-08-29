package com.solvd.laba;

import com.solvd.laba.myinterfaces.UniqueIdInterface;

public class Passenger implements UniqueIdInterface {

    private static final int MAX_LUGGAGE;
    private static int numPassengers;
    private String firstName, lastName;
    private int iD = 0, countBags = 0;
    public Luggage[] myBags = new Luggage[MAX_LUGGAGE];
    private Seat seat;

    static 
    {
        numPassengers = 0;
        MAX_LUGGAGE = 10;
    }

    //Passenger Class constructor 
    //creates new passenger with first and last name as given String parameters
    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.iD = ++numPassengers;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    //creates a Luggage or OversizeLuggage object depending on the int parameter
    //adds bag to the "myBags" array if array is not full
    public final void addBags(Luggage bag) throws OversizeBagException, OverLimitException {
        if (countBags >= MAX_LUGGAGE) 
            throw new OverLimitException();
        if (bag.isOverweight())
            throw new OversizeBagException();
        myBags[countBags++] = bag;
        return;
    }

    //returns total number of bags this passenger has
    public final int getNumBags() {
        return countBags;
    }

    //assigns passenger with a seat number 
    //method is called in Flight Class when booking a passenger to a flight
    public final void setSeatNum(Seat seat) {
        this.seat = seat;
    }

    //returns this passengers seat number
    //if passenger does not belong to a flight then this will return -1
    public final Seat getSeat() throws NullSeatException {
        if (seat == null)
            throw new NullSeatException("Passenger does not have assigned seat!");
        return seat;
    }

    //returns unique id number created for every new passenger
    @Override
    public final int getId() {
        return iD;
    }

    //returns a String of a Passenger object as that object's id number
    @Override
    public final String toString() {
        String myString = String.format("Passenger ID#: %d", iD);
        return myString;
    }

    //compares 2 Passenger objects by comparing their object Strings
    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Passenger) {
            Passenger cast = (Passenger)obj;
            return this.getId() == cast.getId();
        }
        return false;
    }

    //Passenger objects hash code is set to its unique id#
    @Override
    public final int hashCode() {
        return this.getId();
    }
    
}
