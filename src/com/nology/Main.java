package com.nology;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        boolean gameStarted = false;

        Scanner userInput = new Scanner(System.in);

        while(!gameStarted) {
            System.out.println("Are you ready to play? Y/N");
            String input = userInput.next();
            if(input.contains("Y")) {
                gameStarted = true;
            }
        }

        System.out.println("\nShuffling deck...");
        List<String> shuffledDeck = Deck.createNewShuffledDeck();

        int currentCard = 4;

        Player player = new Player(shuffledDeck.get(0),shuffledDeck.get(2));
        Player house = new Player(shuffledDeck.get(1),shuffledDeck.get(3));
        Thread.sleep(1000);

        System.out.println("You have been dealt " + player.getCard(0) + " and " + player.getCard(1));
        Thread.sleep(500);
        System.out.println("House has been dealt " + house.getCard(0) + " and a face-down card");

        boolean gameActive = true;

        String playerTurn = "";

        while(gameActive) {
            if(player.isBust()) {
                Thread.sleep(500);
                System.out.println("\nYou went bust!");
                gameActive=false;
                break;
            }
            Thread.sleep(750);
            System.out.println("Please choose hit (Hi) or stand (St). \n");
            playerTurn = userInput.next();
            if(playerTurn.equals("Hi")) {
                System.out.println("\nYou drew " + shuffledDeck.get(currentCard));
                player.addCardToHand(shuffledDeck.get(currentCard));
                Thread.sleep(100);
                System.out.println("Current hand " + player.getHand() + ". Current total: " + player.getTotal());
                currentCard++;
            } else if(playerTurn.equals("St")) {
                gameActive=false;
                break;
            }

            if(player.getTotal() == 21) {
                Thread.sleep(200);
                System.out.println("\nYou have 21 in hand, automatically going to house turn.");
                break;
            }
        }

        if(!player.isBust()) {
            System.out.println("\nHouse turn.\n");
            Thread.sleep(500);
            System.out.println("House hand is " + house.getHand().get(0) + " and " + house.getHand().get(1));
            while(house.getTotal() < 17) {
                Thread.sleep(1000);
                System.out.println("House drew " + shuffledDeck.get(currentCard));
                house.addCardToHand(shuffledDeck.get(currentCard));
                currentCard++;
                if(house.isBust()) {
                    System.out.println("\nHouse bust, you win!");
                    break;
                }
            }

            if(!house.isBust() && player.getTotal() > house.getTotal()) {
                System.out.println("\nYou win!");
            } else if(!house.isBust()){
                System.out.println("\nYou lose!");
            }
        }
    }
}
