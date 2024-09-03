package com.solvd.laba;

import com.solvd.laba.myinterfaces.LuggageInterface;
import com.solvd.laba.myinterfaces.UniqueIdInterface;

public final class Luggage implements UniqueIdInterface, LuggageInterface {

    private final int ID;
    private int weight;
    private static int numLuggage = 0;
    private boolean isOverweight = false;

    //Luggage Object constructor
    public Luggage(int weight) {
        this.ID = ++numLuggage;
        this.weight = weight;
        this.isOverweight = weight > 50;
    }

    //returns total number of Luggage Object's instantiated
    public static final int getNumLuggage() {
        return numLuggage;
    }



    ////////////// LuggageInterface Overrides ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //resets Luggage Object's weight to new weight value
    @Override
    public final void setWeight(int weight) {
        this.weight = weight;
    }

    //returns Luggage Object's weight
    @Override
    public final int getWeight() {
        return weight;
    }

    //returns true if Luggage Object's weight is over 50lbs
    @Override
    public final boolean isOverweight() {
        return isOverweight;
    }



    ////////////// UniqueIdInterface Overrides ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns Luggage Object's ID#
    @Override
    public final int getId() {
        return ID;
    }



    ////////////// Object Class Overrides ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //returns a String of a Luggage Object as the Object's ID# and "weight"
    @Override
    public final String toString() {
        String myString = String.format("Luggage ID#: %1$d\nLuggage weight: %2$d", ID, weight);
        return myString;
    }

    //compares 2 Luggage Objects by comparing their Object's hashcodes
    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Luggage) {
            Luggage cast = (Luggage)obj;
            return this.hashCode() == cast.hashCode();
        }
        return false;
    }

    //Luggage Object's hashcode is set to its unique ID#
    @Override
    public final int hashCode() {
        return this.getId();
    }
    
}
