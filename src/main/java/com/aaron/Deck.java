package com.aaron;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards;

    Deck () {
        this.cards = generateCards();

    }

    Deck (boolean jokers) {
        this.cards = generateCards(jokers);

    }

    // Fills deck with standard cards cards
    private ArrayList<Card> generateCards() {
        ArrayList<Card> deck = new ArrayList();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                deck.add(new Card(j + 1, i));
            }
        }
        deck.add(new Card(0, 0));
        deck.add(new Card(0, 1));
        return deck;
    }

    // Fills deck with standard cards cards jokers optional
    private ArrayList<Card> generateCards(boolean jokers) {
        ArrayList<Card> deck = new ArrayList();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                deck.add(new Card(j + 1, i));
            }
        }
        if (jokers) {
            deck.add(new Card(0, 0));
            deck.add(new Card(0, 1));
        }
        return deck;
    }

    // Removes un-needed cards
    public void cullDeck(ArrayList<Card> cull) {
        ArrayList<Card> tempList = new ArrayList();
        for (int i = this.cards.size() - 1; i >= 0; i--) {
            for (int j = 0; j < cull.size(); j++) {
                if (this.cards.get(i).toString().equals(cull.get(j).toString())) {
                    tempList.add(this.cards.get(i));
                }
            }
        }
        this.cards.removeAll(tempList);
    }

    public void shuffleDeck() {
//        System.out.println("pre shuffle:");
//        System.out.println(this.cards);
        boolean less = true;
        for (int i = 0; i < 100; i++) {
            ruffleCards();
            cutCards(less);
            if (less) {
                less = false;
            } else {
                less = true;
            }
        }
//        System.out.println("post shuffle:");
//        System.out.println(this.cards);
    }

    private void ruffleCards() {
        ArrayList<Card> side_a = new ArrayList<Card>();
        ArrayList<Card> side_b = new ArrayList<Card>();
        ArrayList<Card> shuffled = new ArrayList<Card>();
        Integer card_count = this.cards.size();
        Integer half_count = card_count / 2;
        boolean a = true;
        for (int i = 0; i < half_count; i++) {
            side_a.add(this.cards.get(i));
        }
        for (int i = half_count; i < card_count; i++) {
            side_b.add(this.cards.get(i));
        }

        this.cards.removeAll(side_a);
        this.cards.removeAll(side_b);

        while (shuffled.size() < card_count) {
            if (a) {
                if (side_a.size() > 0) {
                    shuffled.add(side_a.remove(0));
                }
                if (side_b.size() > 0) {
                    shuffled.add(side_b.remove(0));
                }
                a = false;
            } else {
                if (side_b.size() > 0) {
                    shuffled.add(side_b.remove(0));
                }
                if (side_a.size() > 0) {
                    shuffled.add(side_a.remove(0));
                }
                a = true;
            }
        }
        this.cards.addAll(shuffled);
//        System.out.println(this.cards);
    }

    private void cutCards(boolean less) {
        ArrayList<Card> side_a = new ArrayList<Card>();
        ArrayList<Card> side_b = new ArrayList<Card>();
        Integer card_count = this.cards.size();
        Integer half_count = card_count / 2;
        if (less) {
            for (int i = 0; i < half_count - 2; i++) {
                side_a.add(this.cards.get(i));
            }
            for (int i = half_count - 2; i < card_count; i++) {
                side_b.add(this.cards.get(i));
            }
        } else {
            for (int i = 0; i < half_count + 2; i++) {
                side_a.add(this.cards.get(i));
            }
            for (int i = half_count + 2; i < card_count; i++) {
                side_b.add(this.cards.get(i));
            }
        }
        this.cards.removeAll(side_a);
        this.cards.removeAll(side_b);
        cards.addAll(side_b);
        cards.addAll(side_a);
    }

    // TODO needs to account for no cards left in deck
    public Card dealCards() {
//        System.out.println(this.cards);
        Card c = this.cards.get(0);
//        System.out.println(c);
//        System.out.println(this.cards);
        return this.cards.remove(0);
    }

    // TODO needs to account for less cards in deack than was asked for
    public ArrayList<Card> dealCards(Integer delt) {
//        System.out.println("in deal cards");
        ArrayList<Card> deltCards = new ArrayList();
        for (int i = 0; i < delt; i++) {
            deltCards.add(dealCards());
        }
//        System.out.println(deltCards);
        return deltCards;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
