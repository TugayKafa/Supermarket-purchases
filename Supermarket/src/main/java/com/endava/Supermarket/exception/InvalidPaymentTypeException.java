package com.endava.Supermarket.exception;

public class InvalidPaymentTypeException extends CustomRuntimeException{
    public InvalidPaymentTypeException() {
        super("Invalid payment type!");
    }
}
