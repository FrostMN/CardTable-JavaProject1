package com.aaron;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand_cards;

    Hand() {
        this.hand_cards = new ArrayList();
    }

    public int getCount() {
        return this.hand_cards.size();
    }

    public void add(Card card) {
        this.hand_cards.add(card);
    }

    public void add(ArrayList<Card> cards) {
        this.hand_cards.addAll(cards);
    }

    public ArrayList<Card> getHand() {
        return hand_cards;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "hand_cards=" + hand_cards +
                '}';
    }

    public Card playCard(int index) {
//        System.out.println("in hand play card");
//        System.out.println(this.hand_cards.get(index));
//
        return this.hand_cards.remove(index);
    }
}
