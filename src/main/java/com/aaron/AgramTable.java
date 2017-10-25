package com.aaron;

import java.util.ArrayList;
import static input.InputUtils.*;
import java.util.Random;


public class AgramTable {
    private Random rng;
    private Deck agramDeck;
    private ArrayList<Player> players;
    private ArrayList<Card> playPile;
    private Integer player_count;

    AgramTable() {
        this.agramDeck = new Deck(false);
        rng = new Random();

        ArrayList<Card> cull_cards = new ArrayList();
        cull_cards.add(new Card(1, 0));
        for (int suit = 0; suit < 4; suit++) {
            cull_cards.add(new Card(2, suit));
            for (int faceValue = 10; faceValue < 13; faceValue++) {
                cull_cards.add(new Card(faceValue + 1, suit));
            }
        }
        agramDeck.cullDeck(cull_cards);

        this.playPile = new ArrayList<Card>();
        this.players = AgramTable.getPlayers();
        this.player_count = Player.player_counter - 1;

        firstDealer(this.players);
    }

    static private ArrayList<Player> getPlayers () {
        ArrayList<Player> players = new ArrayList();
        int number_of_players = intInput("How many Players?");
        for (int i = 0; i < number_of_players; i++) {
            String name = stringInput(String.format("What is the name of Player %d?", i + 1));
            players.add(new Player(name));
        }
        return players;
    }

    private void firstDealer(ArrayList<Player> players) {
        players.get(this.rng.nextInt(players.size())).setDealer(true);
    }

    public Integer getPlayerCount() {
        return player_count;
    }

    public String ShowPlayers() {
        return "AgramTable{" +
                "players=" + players +
                '}';
    }

    public void playTrick() {
        agramDeck.shuffleDeck();
        dealTrick();
        while (countPlayerCards() > 0) {

            sortPlayers();
            for (Player p : this.players) {
                playPile.add(playCard(p));
                p.showCards();
            }
            testTrick();
            System.out.println("");
        }

        for (Player p: this.players) {
            if (p.isLastWinner()) {
                System.out.println( "Player " + p.getName() + " wins!");
            }
        }

    }

    private int countPlayerCards() {
        int count = 0;
        for (Player p: this.players) {
            count += p.getCardCount();
        }
        return count;
    }

    private void dealTrick() {
//        System.out.println("in deal trick");
//        System.out.println(this.agramDeck.cards);
        while (countPlayerCards() < this.players.size() * 6) {
            for (Player p: this.players) {
//                System.out.println(p.getName());
                p.addCards(this.agramDeck.dealCards(3));
            }
        }
//        System.out.println(this.agramDeck.cards);
        for (Player p: players) {
//            System.out.println(p.getName());
//            System.out.println(p.getCardCount());
//            System.out.println(p.showCards());
//            System.out.println(p.isDealer());
//            System.out.println(p.getPlayerNumber());
//            System.out.println(p.toString());
//            System.out.println();

        }
//        System.out.println(this.agramDeck.cards);
    }

    private void sortPlayers() {
        ArrayList<Player> side_a = new ArrayList<Player>();
        ArrayList<Player> side_b = new ArrayList<Player>();
        ArrayList<Player> sorted = new ArrayList<Player>();
        int temp = 0;
        for (Player p: this.players) {
            if (p.isDealer()) {
                temp = p.getPlayerNumber();
            }
        }
//        temp = sorted.get(0).getPlayerNumberr();
//        System.out.println(temp);
//        System.out.println(sorted.get(0).getPlayerNumber());

//        sorted.remove(0);

//        System.out.println("temp");
//        System.out.println(temp);

        for (int i = temp - 1; i < this.players.size(); i++) {
            side_a.add(this.players.get(i));
        }

//        System.out.println("side A");
        for (Player p: side_a) {
//            System.out.println(p);
            sorted.add(p);
        }

        for (int i = 0; i < temp - 1; i++) {
            side_b.add(this.players.get(i));
        }

//        System.out.println("side B");
        for (Player p: side_b) {
            sorted.add(p);
//            System.out.println(p);
        }

//        System.out.println("sorted");
        for (Player p: sorted) {
//            System.out.println(p);
        }

        this.players.removeAll(sorted);

        for (Player p: sorted) {
            this.players.add(p);
        }

        System.out.println(sorted);
        System.out.println(this.players);

    }

    private Card playCard(Player p) {
        System.out.println("Hello " + p.getName());
        System.out.println("Cards in play:");
        if (this.playPile.size() == 0) {
            System.out.println("[ none ]");
        } else {
            System.out.println(this.playPile);
        }
        p.showCards();
        return p.playCard(intInput("What card would you like to play?"));
    }

    private void testTrick() {
        String trickSuite = this.playPile.get(0).getSuit();
        Card bestCard = this.playPile.get(0);
        for (Card c: this.playPile) {
            if ( c.getSuit().equals(trickSuite) && c.getValue() > bestCard.getValue() ) {
                bestCard = c;
            }
        }

        for (Player p: this.players) {
//            System.out.println(p.getPlayerNumber());
//            System.out.println(bestCard.getPlayedBy());

            p.setDealer(false);
            p.setLastWinner(false);

            if (p.getPlayerNumber() == bestCard.getPlayedBy()) {
                p.addWonTrick(this.playPile);
//                System.out.println("player " + p.getPlayerNumber() + " tricks won");
                p.setDealer(true);
                p.setLastWinner(true);
                System.out.println(p.getName() + " won the trick!");
//                p.getWonTrick();
            }
        }

        this.playPile.clear();
//        System.out.println("bestCard:");
//        System.out.println(bestCard);
//        System.out.println("played by:");
//        System.out.println(bestCard.getPlayedBy());
    }
}
