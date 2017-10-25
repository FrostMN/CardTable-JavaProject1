package com.aaron;

public class Card {
    private Integer value;
    private Integer suit;
    private Integer played_by;
    private boolean face_up;

    Card (Integer value, Integer suit) {
        this.value = value;
        this.suit = suit;
        this.played_by = 0;
        this.face_up = true;
    }

    Card (Integer value, Integer suit, boolean face_up) {
        this.value = value;
        this.suit = suit;
        this.played_by = 0;
        this.face_up = face_up;
    }


    public Integer getValue() {
        return this.value;
    }

    public String getFaceValue() {
        switch (this.value) {
            case 0:
                return "Joker";
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return Integer.toString(this.value);
        }
    }

    public String getSuit() {
        switch (this.suit) {
            case 0:
                return Suit.SPADES.toString();
            case 1:
                return Suit.HEARTS.toString();
            case 2:
                return Suit.DIAMONDS.toString();
            default:
                return Suit.CLUBS.toString();
        }
    }

    public boolean isFaceUp() {
        return face_up;
    }

    public void Flip(boolean flip) {
        if (isFaceUp()) {
            this.face_up = false;
        } else {
            this.face_up = true;
        }
    }

    public Integer getPlayedBy() {
        return played_by;
    }

    public void setPlayedBy(Integer played_by) {
        this.played_by = played_by;
    }

    @Override
    public String toString() {
        return getFaceValue() + " of " + getSuit();
//        return "Card{" +
//                "value=" + getValue() +
//                ", suit=" + getSuit() +
//                ", face_up=" + face_up +
//                '}';
    }
}
