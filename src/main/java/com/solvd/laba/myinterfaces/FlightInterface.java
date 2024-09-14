package com.solvd.laba.myinterfaces;

import com.solvd.laba.Passenger;
import com.solvd.laba.customexceptions.DuplicateBookingException;
import com.solvd.laba.customexceptions.EmptyPassengerException;
import com.solvd.laba.myenums.SeatType;

public interface FlightInterface {

    boolean getSeatsAvailable();
    boolean getSeatsAvailable(SeatType seatType);
    boolean bookSeat(Passenger person, SeatType seatType) throws DuplicateBookingException;
    int getNumPassengers();
    Passenger[] getPassengers() throws EmptyPassengerException;
    String flightInfo();

}
