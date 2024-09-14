package com.solvd.laba.myinterfaces;
import com.solvd.laba.Passenger;
import com.solvd.laba.customexceptions.EmptyPassengerException;

public interface SeatInterface {

    Passenger getPassenger() throws EmptyPassengerException;
    boolean addPassenger(Passenger person);
    boolean isAvailable();
    int getSeatNum();
    char getSeatLetter();

}
