package com.velha.Entities;

public abstract class Serial {
    private static int serial = 0;

    public int count() {
        serial++;
        return serial;
    }
}
