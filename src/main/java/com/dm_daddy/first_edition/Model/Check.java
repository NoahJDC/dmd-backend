package com.dm_daddy.first_edition.Model;

import java.security.InvalidParameterException;

public class Check {

    private double subtotal;

    private int partySize;

    public Check() {}

    public Check(int partySize, double subTotal) {
        this.setPartySize(partySize);
        this.setSubtotal(subTotal);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        if(partySize <= 0) {
            throw new InvalidParameterException(partySize + " is not a valid party size.");
        } else {
            this.partySize = partySize;
        }
    }

    public double calculateTotal() {
        if(this.partySize >= 6) {
            return this.subtotal + calculateEighteenPercentTip();
        } else {
            return this.subtotal;
        }
    }


    public double calculateFifteenPercentTip() {
        return calculateTip(15.0);
    }

    public double calculateEighteenPercentTip() {
        return calculateTip(18.0);
    }

    public double calculateTwentyPercentTip() {
        return calculateTip(20.0);
    }

    private double calculateTip(double percent) {
        return Math.round(this.subtotal * percent) / 100.0;
    }
}
