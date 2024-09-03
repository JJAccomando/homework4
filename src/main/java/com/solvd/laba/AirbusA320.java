package com.solvd.laba;

import com.solvd.laba.customexceptions.DoubleBookException;
import com.solvd.laba.myenums.*;

public final class AirbusA320 extends AirplaneBase {

    private final int ID;
    private static int numA320 = 0;
    private final Seat[] FIRST_CLASS_SEATS, BUSINESS_CLASS_SEATS, ECONOMY_CLASS_SEATS;
    private static final PlaneType PLANE_TYPE = PlaneType.A320;

    //AirbusA320 Object constructor
    public AirbusA320() {
        ++numA320;
        this.ID = super.getTotalPlanes();
        this.FIRST_CLASS_SEATS = new Seat[PLANE_TYPE.SEATS_IN_FIRST];
        this.BUSINESS_CLASS_SEATS = new Seat[PLANE_TYPE.SEATS_IN_BUSINESS];
        this.ECONOMY_CLASS_SEATS = new Seat[PLANE_TYPE.SEATS_IN_ECON];
        this.populateSeats();
    }

    //private method used in constructor that assigns Seat arrays with Seat Objects
    //each Seat array will completely fill their capacity with Seat Objects
    //each Seat Object will contain a Seat number and letter 
    //Seat number is based on rows and Seat letter is based on columns
    //number of rows and columns is based on PlaneType
    private final void populateSeats() {
        int first = PLANE_TYPE.SEATS_IN_FIRST;
        int totalRowFirst = (first / PLANE_TYPE.NUM_COLUMNS_FIRST);
        int bus = PLANE_TYPE.SEATS_IN_BUSINESS;
        int totalRowBus = (bus / PLANE_TYPE.NUM_COLUMNS_BUSINESS);
        int econ = PLANE_TYPE.SEATS_IN_ECON;
        int totalRowEcon = (econ / PLANE_TYPE.NUM_COLUMNS_ECON);

        for (int row = 1; row <= totalRowFirst; row++) {
            char seatLetter = 'A';
            for (int i = 0; i < PLANE_TYPE.NUM_COLUMNS_FIRST; i++) {
                Seat seat = new Seat(row, seatLetter);
                for (int j = 0; j < FIRST_CLASS_SEATS.length; j++) {
                    if (FIRST_CLASS_SEATS[j] == null) {
                        FIRST_CLASS_SEATS[j] = seat;
                        break;
                    }
                }
                ++seatLetter;
            }
        }

        for (int row = totalRowFirst + 1; row <= totalRowFirst + totalRowBus; row++) {
            char seatLetter = 'A';
            for (int i = 0; i < PLANE_TYPE.NUM_COLUMNS_BUSINESS; i++) {
                Seat seat = new Seat(row, seatLetter);
                for (int j = 0; j < BUSINESS_CLASS_SEATS.length; j++) {
                    if (BUSINESS_CLASS_SEATS[j] == null) {
                        BUSINESS_CLASS_SEATS[j] = seat;
                        break;
                    }
                }
                ++seatLetter;
            }
        }

        for (int row = totalRowFirst + totalRowBus + 1; row <= totalRowFirst + totalRowBus + totalRowEcon; row++) {
            char seatLetter = 'A';
            for (int i = 0; i < PLANE_TYPE.NUM_COLUMNS_ECON; i++) {
                Seat seat = new Seat(row, seatLetter);
                for (int j = 0; j < ECONOMY_CLASS_SEATS.length; j++) {
                    if (ECONOMY_CLASS_SEATS[j] == null) {
                        ECONOMY_CLASS_SEATS[j] = seat;
                        break;
                    }
                }
                ++seatLetter;
            }
        }
    }

    //returns current number of AirbusA320 Objects instantiated
    public static final int getNumA320() {
        return numA320;
    }



    ////////////// AirplaneBase Class Overrides //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //assigns Passenger Object to Seat Object and vice versa
    //Seat Object is pulled from specific Seat array based on SeatType parameter
    //specific Seat Object is pulled based on given index parameter
    //method is called in a Flight Object's method and index parameter is provided in that Flight Object's method
    @Override
    public final boolean assignSeat(Passenger person, int index, SeatType seat) {
        switch (seat) {
            case FIRST_CLASS:
                FIRST_CLASS_SEATS[index].addPassenger(person);
                try {
                    person.setSeatNum(FIRST_CLASS_SEATS[index]);
                } catch (DoubleBookException e) {
                    return false;
                }
                break;

            case BUSINESS_CLASS:
                BUSINESS_CLASS_SEATS[index].addPassenger(person);
                try {
                    person.setSeatNum(BUSINESS_CLASS_SEATS[index]);
                } catch (DoubleBookException e) {
                    return false;
                }
                break;

            case ECONOMY_CLASS:
                ECONOMY_CLASS_SEATS[index].addPassenger(person);
                try {
                    person.setSeatNum(ECONOMY_CLASS_SEATS[index]);
                } catch (DoubleBookException e) {
                    return false;
                }
                break;

            default:
                return false;
        }
        return true;
    }

    //returns this Object's PlaneType
    @Override
    public final PlaneType getPlaneType() {
        return PLANE_TYPE;
    }



    ////////////// UniqueIdInterface Overrides ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns AirbusA320 Object's ID#
    @Override
    public final int getId() {
        return ID;
    }



    ////////////// Object Class Overrides ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns a String of an AirbusA320 Object as the Object's "COMPANY", "CLASSIFICATION", and ID#
    @Override
    public final String toString() {
        String myString = String.format("%1$s %2$s\nPlane ID#: %d", PLANE_TYPE.COMPANY, PLANE_TYPE.CLASSIFICATION, ID);
        return myString;
    }

    //compares 2 AirbusA320 Objects by comparing their Object's hashcodes
    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof AirbusA320) {
            AirbusA320 cast = (AirbusA320) obj;
            return this.hashCode() == cast.hashCode();
        }
        return false;
    }

    //AirbusA320 Object's hashcode is set to its unique ID#
    @Override
    public final int hashCode() {
        return this.getId();
    }

}
