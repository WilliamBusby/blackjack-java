package com.nology;

import java.util.*;

import static com.nology.Deck.getValueOfCard;

public class Player {

    private List<String> hand = new ArrayList<>();
    private int total;

    public Player(String cardOne, String cardTwo) {
        Collections.addAll(hand, cardOne, cardTwo);
        total = getValueOfCard(cardOne) + getValueOfCard(cardTwo);
    }

    public int getTotal() { return total; }

    public List<String> getHand() { return hand; }

    public String getCard(int index) { return hand.get(index); }

    public boolean isBust() { return (total > 21); }

    public void addCardToHand(String card) {
        hand.add(card);
        total += getValueOfCard(card);
    }
}
