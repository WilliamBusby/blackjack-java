package com.nology;

import java.util.*;

public class Main {

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    public static void main(String[] args) {

        String[] suits = {"H", "C", "D", "S"};
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        boolean gameStarted = false;

        Scanner userInput = new Scanner(System.in);
        while(!gameStarted) {
            System.out.println("Are you ready to play? Y/N");
            String input = userInput.next();
            if(input.contains("Y")) {
                gameStarted = true;
            }
        }

        List<String> deck = Deck.addCardsToDeck(suits,values);
        setTimeout(() -> System.out.println("Shuffling deck..."), 100);
        List<String> shuffledDeck = Deck.shuffleDeck(deck);

        int currentCard = 4;

        setTimeout(() -> System.out.println("You have been dealt " + shuffledDeck.get(0) + " and " + shuffledDeck.get(2)), 2000);
        setTimeout(() -> System.out.println("House has been dealt " + shuffledDeck.get(1) + " and a face-down card"), 2500);

        List<String> playerHand = new ArrayList<>();
        playerHand.add(shuffledDeck.get(0));
        playerHand.add(shuffledDeck.get(2));

        List<String> houseHand = new ArrayList<>();
        houseHand.add(shuffledDeck.get(1));
        houseHand.add(shuffledDeck.get(3));

        int playerTotal = Deck.getValueOfCard(playerHand.get(0)) + Deck.getValueOfCard(playerHand.get(1));
        int houseTotal = Deck.getValueOfCard(houseHand.get(0)) + Deck.getValueOfCard(houseHand.get(1));

        int playerSplitValue1 = Deck.getValueOfCard(playerHand.get(0));
        int playerSplitValue2 = Deck.getValueOfCard(playerHand.get(0));

        boolean playerSplit = false;
        boolean playerHandActive1 = false;
        boolean playerHandActive2 = false;
        boolean playerHandBust1 = false;
        boolean playerHandBust2 = false;
        boolean gameActive = true;

        boolean playerBust = false;
        boolean houseBust = false;

        String playerTurn = "";

        while(gameActive && !playerBust || (playerHandBust1 && playerHandBust2)) {
            if(playerTotal > 21 && !playerSplit ) {
                setTimeout(() -> System.out.println("You went bust!"), 1000);
                playerBust = true;
                break;
            } else if(playerSplit) {
                if(playerSplitValue1 > 21) {
                    playerHandBust1 = true;
                } else if(playerSplitValue2 > 21) {
                    playerHandBust2 = true;
                }
            }
            setTimeout(() -> System.out.println("Please choose hit (Hi), split (Sp), or stand (St)."), 3500);
            playerTurn = userInput.next();
            if(playerTurn.equals("Hi")) {
                System.out.println("You drew " + shuffledDeck.get(currentCard));
                playerTotal+= Deck.getValueOfCard(shuffledDeck.get(currentCard));
                playerHand.add(shuffledDeck.get(currentCard));
                currentCard++;
            } else if(playerTurn.equals("Sp") && !playerSplit && playerHand.get(0).charAt(0) == playerHand.get(1).charAt(0)) {
                playerSplit = true;
            } else if(playerTurn.equals("Sp") && playerSplit) {
                System.out.println("You cannot split more than once.");
            } else if(playerTurn.equals("Sp") && playerHand.get(0).charAt(0) != playerHand.get(1).charAt(0)) {
                System.out.println("Your cards have to be the same value to split.");
            } else if(playerTurn.equals("St")) {
                gameActive = false;
                break;
            }
        }

        if(!playerBust) {
            System.out.println("House turn.");
            System.out.println("House hand is " + houseHand.get(0) + " and " + houseHand.get(1));
            while(houseTotal <= 17) {
                System.out.println("House drew " + shuffledDeck.get(currentCard));
                houseTotal += Deck.getValueOfCard(shuffledDeck.get(currentCard));
                houseHand.add(shuffledDeck.get(currentCard));
                currentCard++;
                if(houseTotal > 21) {
                    System.out.println("House bust, you win!");
                    houseBust = true;
                }
            }

            if(!houseBust && playerTotal > houseTotal) {
                System.out.println("You win!");
            } else if(!houseBust && (houseTotal > playerTotal || houseTotal == playerTotal)) {
                System.out.println("You lose!");
            }
        }

    }
}
