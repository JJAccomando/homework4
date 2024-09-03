package com.solvd.laba.myinterfaces;

import com.solvd.laba.Luggage;
import com.solvd.laba.Seat;
import com.solvd.laba.customexceptions.*;

public interface PassengerInterface {

    int MAX_LUGGAGE = 10;

    String getFirstName();
    String getLastName();
    boolean addBags(Luggage bag) throws OversizeBagException, OverLimitException;
    int getNumBags();
    void setSeatNum(Seat seat) throws DoubleBookException;
    Seat getSeat() throws NullSeatException;
    Luggage[] getLuggage() throws NullBagException;
    
}
