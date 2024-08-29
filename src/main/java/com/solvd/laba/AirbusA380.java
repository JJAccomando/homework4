package com.solvd.laba;

import com.solvd.laba.myinterfaces.A380Interface;

public final class AirbusA380 extends AirplaneBase implements A380Interface {

    private int iD = 0;
    private static int count;

    static 
    {
        count = 0;
    }
    
    //AirbusA380 object constructor
    public AirbusA380() {
        ++count;
        this.iD = super.getNumPlanes();
    }

    public static final int getCount() {
        return count;
    }

    @Override
    public final int getId() {
        return iD;
    }

    @Override
    public final String getClassification() {
        return CLASSIFICATION;
    }

    //returns a String of an AirbusA380 object as the object's "CLASSIFICATION" and id number
    @Override
    public final String toString() {
        String str1 = String.format("Plane ID#: %d", iD);
        String str2 = String.format("%1$s %2$s\n%3$s", COMPANY, CLASSIFICATION, str1);
        return str2;
    }

    //compares 2 AirbusA380 objects by comparing their object Strings
    @Override
    public final boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof AirbusA380) {
            AirbusA380 cast = (AirbusA380)obj;
            return this.getId() == cast.getId();
        }
        return false;
    }

    //AirbusA380 objects hash code is set to its unique id#
    @Override
    public final int hashCode() {
        return this.getId();
    }

    @Override
    public final int getTotalSeats() {
        return TOTAL_SEATS;
    }

    @Override
    public final int getSeatsInFirst() {
        return TOTAL_IN_FIRST;
    }

    @Override
    public final int getSeatsInBusiness() {
        return TOTAL_IN_BUSINESS;
    }

    @Override
    public final int getSeatsInEcon() {
        return TOTAL_IN_ECONOMY;
    }
    
}
