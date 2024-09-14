package com.solvd.laba.customexceptions;

public class EmptySeatException extends Exception {
    
    public EmptySeatException() {}

    public EmptySeatException(String message) {
        super(message);
    }
    
}
