package com.solvd.laba;

public final class Seat {

    private final int SEAT_NUM;
    private final char SEAT_LETTER;
    private final String I_D;
    private Passenger passenger;
    private boolean availabe = true;

    //seat constructor
    //called when creating a flight
    public Seat(int seatNum, char seatLetter) {
        SEAT_NUM = seatNum;
        SEAT_LETTER = seatLetter;
        I_D = String.format("%d%c", SEAT_NUM, SEAT_LETTER);
    }

    //returns seat number in the form of a string
    public final String getId() {
        return I_D;
    }

    public final Passenger getPassenger() {
        return passenger;
    }

    public final boolean isAvailable() {
        return availabe;
    }

    //seat availability is set to false 
    public final void addPassenger(Passenger passenger) {
        this.passenger = passenger;
        availabe = false;
    }

    public final int getSeatNum() {
        return SEAT_NUM;
    }

    public final char getSeatLetter() {
        return SEAT_LETTER;
    }
    
}
