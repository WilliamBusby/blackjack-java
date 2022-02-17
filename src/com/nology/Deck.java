package com.nology;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.ArrayList;

public class Deck {

    public String[] suits = {"H", "C", "D", "S"};
    public String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    static List<String> addCardsToDeck(String[] suits, String[] values) {

        List<String> deckOfCards = new ArrayList<>();

        for(int i=0; i<52;i++) {
            int suitVal = i % 4;
            String suit = suits[suitVal];
            int valueVal = i / 4;
            String value = values[valueVal];
            deckOfCards.add(suit+value);
        }
        return deckOfCards;
    }

    static int getValueOfCard(String card) {
        char value = card.charAt(1);

        if(value=='A') {
            return 1;
        } else if(value == 'J' || value == 'Q' || value == 'K' || card.contains("10")) {
            return 10;
        } else {
            return value - '0';
        }
    }

    static List<String> shuffleDeck(List<String> deck) {

        Random r = new Random();

        for (int i = deck.size() - 1; i >= 1; i--)
        {
            int j = r.nextInt(i + 1);
            String obj = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, obj);
        }
        return deck;
    }
}
