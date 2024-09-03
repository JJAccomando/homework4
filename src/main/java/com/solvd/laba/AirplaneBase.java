package com.solvd.laba;

import com.solvd.laba.myinterfaces.UniqueIdInterface;
import com.solvd.laba.myenums.*;

public abstract class AirplaneBase implements UniqueIdInterface {

    private static int numPlanes;

    {
        //incraments total number of airplanes each time a new Object that extends AirplaneBase is created
        ++numPlanes;
    }

    abstract public PlaneType getPlaneType();

    abstract public boolean assignSeat(Passenger person, int index, SeatType seat);

    //returns current number of AirplaneBase Objects instantiated
    public static int getTotalPlanes() {
        return numPlanes;
    }
    
}
