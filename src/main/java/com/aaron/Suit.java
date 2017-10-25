package com.aaron;

public enum Suit {
    SPADES ("Spades"),
    HEARTS ("Hearts"),
    DIAMONDS ("Diamonds"),
    CLUBS ("Clubs");

    private final String name;
    Suit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
