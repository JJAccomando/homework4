package com.solvd.laba.myinterfaces;

import com.solvd.laba.Passenger;
import com.solvd.laba.customexceptions.DoubleBookException;
import com.solvd.laba.customexceptions.NullPassengerException;
import com.solvd.laba.myenums.SeatType;

public interface FlightInterface {

    boolean getSeatsAvailable();
    boolean getSeatsAvailable(SeatType seatType);
    boolean bookSeat(Passenger person, SeatType seatType) throws DoubleBookException;
    int getNumPassengers();
    Passenger[] getPassengers() throws NullPassengerException;
    String flightInfo();

}
