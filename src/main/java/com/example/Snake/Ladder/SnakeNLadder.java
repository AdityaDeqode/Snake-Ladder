package com.example.Snake.Ladder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakeNLadder {

    Scanner scan= new Scanner(System.in);
    final static int WINPOINT = 100;
     Map<Integer, Integer> snake = new HashMap<>();
     Map<Integer, Integer> ladder = new HashMap<>();

    public int rollDice() {
        int n = 0;
        Random r = new Random();
        n = r.nextInt(7);
        return (n == 0 ? 1 : n);
    }


    public int calculatePlayerValue(int playerId, int playerPosition, int diceValue) {
        int playerNewPosition=playerPosition+diceValue;

        if (playerNewPosition > WINPOINT)
            return playerPosition;

        if (null !=snake.get(playerNewPosition)) {
            System.out.println("Oops.. Player " + (playerId+1) + " swallowed by the snake..");
            playerNewPosition=snake.get(playerNewPosition);
        }

        if (null !=ladder.get(playerNewPosition)) {
            System.out.println("YAY!  Player " + (playerId+1) + " climbing the ladder..");
            playerNewPosition=ladder.get(playerNewPosition);
        }

        return playerNewPosition;
    }

    public boolean isWin(int playerPosition) {
        return WINPOINT==playerPosition;
    }

    public void startGame() {


        System.out.println("Enter key-value pairs (key=value) for Adding Snakes in the board, or 'exit' to finish):");
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("exit")) {
            String[] parts = input.split("=");
            if (parts.length == 2) {
                int key = Integer.parseInt(parts[0].trim());
                int value = Integer.parseInt(parts[1].trim());
                snake.put(key, value);
            }

            input = scan.nextLine();
        }


        System.out.println("Enter key-value pairs (key=value) for Adding Ladder in the board, or 'exit' to finish):");
        String input2 = scan.nextLine();

        while (!input2.equalsIgnoreCase("exit")) {
            String[] parts = input2.split("=");
            if (parts.length == 2) {
                int key = Integer.parseInt(parts[0].trim());
                int value = Integer.parseInt(parts[1].trim());
                ladder.put(key, value);
            }

            input2 = scan.nextLine();
        }

        int number;
        System.out.println("Please choose number of player by entering the number value  ");
        number= Integer.parseInt(scan.next());
        int[] numberOfPlayer = new int[number];


        Scanner scan= new Scanner(System.in);
        String rPressed;
        int diceValue = 0;
        do {
            System.out.println("Press 'r' to roll Dice");
            rPressed=scan.next();

            for(int i =0;i <numberOfPlayer.length; i++){
                diceValue=rollDice();
                numberOfPlayer[i] = calculatePlayerValue(i, numberOfPlayer[i], diceValue);
                System.out.println("Player Number " +(i+1)  +  " Position:" + numberOfPlayer[i]);
                System.out.println("-------------------------");
                if (isWin(numberOfPlayer[i])) {
                    System.out.println("Congratulations! Player " + (i+1) + " Won the Game ");
                    return;
                }

            }

        } while ("r".equals(rPressed));
    }
}
