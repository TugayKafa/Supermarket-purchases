package com.endava.Supermarket.exception;

public class InvalidPurchaseMethod extends CustomRuntimeException{
    public InvalidPurchaseMethod() {
        super("You have to provide cash amount if the payment type is CASH!");
    }
}
