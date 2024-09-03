package com.solvd.laba.myinterfaces;
import com.solvd.laba.Passenger;
import com.solvd.laba.customexceptions.NullPassengerException;

public interface SeatInterface {

    Passenger getPassenger() throws NullPassengerException;
    boolean addPassenger(Passenger person);
    boolean isAvailable();
    int getSeatNum();
    char getSeatLetter();

}
