package com.solvd.laba.customexceptions;

public class EmptyPassengerException extends Exception {

    public EmptyPassengerException() {}

    public EmptyPassengerException(String message) {
        super(message);
    }

}
