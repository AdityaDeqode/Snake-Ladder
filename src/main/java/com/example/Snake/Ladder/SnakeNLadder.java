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
    {
        snake.put(99, 54);
        snake.put(70, 55);
        snake.put(52, 42);
        snake.put(25, 2);
        snake.put(95, 72);

        ladder.put(6, 25);
        ladder.put(11, 40);
        ladder.put(60, 85);
        ladder.put(46, 90);
        ladder.put(17, 69);
    }

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

//            if (currentPlayer==-1) {
//                player1Position=calculatePlayerValue("First Player", player1Position, diceValue);
//                System.out.println("First Player Position:"+player1Position);
//                System.out.println("Second Player Position:"+player2Position);
//                System.out.println("-------------------------");
//                if (isWin(player1Position)) {
//                    System.out.println("Congratulations! First player won");
//                    return;
//                }
//            } else {
//                player2Position = calculatePlayerValue("Second Player", player2Position, diceValue);
//                System.out.println("First Player Position:"+player1Position);
//                System.out.println("Second Player Position:"+player2Position);
//                System.out.println("-------------------------");
//                if (isWin(player2Position)) {
//                    System.out.println("Congratulations! Second player won");
//                    return;
//                }
//            }
//            currentPlayer = -currentPlayer;
        } while ("r".equals(rPressed));
    }
}
