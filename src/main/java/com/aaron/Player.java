package com.aaron;

import java.util.ArrayList;

public class Player {
    static int player_counter = 1;
    private int player_number;
    private Hand cards;
    private String name;
    private boolean is_dealer;
    private ArrayList<ArrayList<Card>> won_tricks;
    private boolean lastWinner;

    Player(String name) {
        this.name = name;
        this.cards = new Hand();
        this.player_number = Player.player_counter;
        this.is_dealer = false;
        this.won_tricks = new ArrayList<ArrayList<Card>>();
        this.lastWinner = false;

        Player.player_counter++;
    }

    public int getPlayerNumber() {
        return player_number;
    }

    public void addCards(Card card) {
        this.cards.add(card);
    }

    public void addCards(ArrayList<Card> cards) {
//        System.out.println("in add cards");
        this.cards.add(cards);
//        System.out.println(this.cards);
    }

    public void showCards() {
        String cardString = "";
        int counter = 1;
        for (Card c: this.cards.getHand()) {
            cardString += counter + ") - " + c.toString() + "\n";
            counter ++;
        }
        System.out.println(cardString);
    }

    public String getName() {
        return name;
    }

    public boolean isDealer() {
        return is_dealer;
    }

    public void setDealer(boolean is_dealer) {
        this.is_dealer = is_dealer;
    }

    public int getCardCount() {
        return this.cards.getCount();
    }

    public Card playCard(int index) {

        Card c = this.cards.playCard(index - 1);

        c.setPlayedBy(this.player_number);

        System.out.println(c);

        return c;
    }

    public void addWonTrick(ArrayList<Card> trick) {
        this.won_tricks.add(trick);
    }

    public void getWonTrick() {
        System.out.println(this.won_tricks);
    }

    public boolean isLastWinner() {
        return lastWinner;
    }

    public void setLastWinner(boolean lastWinner) {
        this.lastWinner = lastWinner;
    }

    @Override
    public String toString() {
        return "Player{" +
                "player_number=" + player_number +
                ", cards=" + cards +
                ", name='" + name + '\'' +
                ", is_dealer=" + is_dealer +
                '}';
    }
}
