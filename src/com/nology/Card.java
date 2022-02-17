package com.nology;

import java.util.*;

public class Card {

    private int value;
    private char suit;
    private char num;

    public Card(char suit, char num) {
        this.suit = suit;
        this.num = num;
    }

    public char getNum() {
        return num;
    }

    public char getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public void setNum(char num) {
        this.num = num;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
