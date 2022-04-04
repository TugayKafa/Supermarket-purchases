package com.endava.Supermarket.exception;

public class InvalidTypeOfItemException extends CustomRuntimeException{
    public InvalidTypeOfItemException() {
        super("Invalid type of item!");
    }
}
