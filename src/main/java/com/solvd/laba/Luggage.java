package com.solvd.laba;

import com.solvd.laba.myinterfaces.UniqueIdInterface;

public class Luggage implements UniqueIdInterface {

    private int weight, iD = 0;
    private static int numLuggage;
    private boolean isOverweight = false;

    static 
    {
        numLuggage = 0;
    }

    //LuggageBase constructor
    //if new luggage object is over 50lbs then isOverweight is true
    public Luggage(int weight) {
        this.iD = ++numLuggage;
        this.weight = weight;
        this.isOverweight = weight > 50;
    }

    public final void setWeight(int weight) {
        this.weight = weight;
    }

    public final int getWeight() {
        return weight;
    }

    public final boolean isOverweight() {
        return isOverweight;
    }

    //returns unique LuggageBase objects id number
    @Override
    public final int getId() {
        return iD;
    }

    //returns a String of a LuggageBase object as the object's id number
    @Override
    public final String toString() {
        String myString = String.format("Bag ID#: %d", iD);
        return myString;
    }

    //compares 2 LuggageBase objects by comparing their object Strings
    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Luggage) {
            Luggage cast = (Luggage)obj;
            return this.getId() == cast.getId();
        }
        return false;
    }

    //LuggageBase objects hash code is set to its unique id#
    @Override
    public final int hashCode() {
        return this.getId();
    }
    
}
