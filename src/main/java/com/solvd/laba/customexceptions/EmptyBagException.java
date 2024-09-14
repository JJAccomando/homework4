package com.solvd.laba.customexceptions;

public class EmptyBagException extends Exception {

    public EmptyBagException() {}

    public EmptyBagException(String message) {
        super(message);
    }

}
