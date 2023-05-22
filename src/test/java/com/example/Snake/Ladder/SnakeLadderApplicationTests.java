package com.example.Snake.Ladder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SnakeLadderApplicationTests {


	private SnakeNLadder game;

	@BeforeEach
	public void setUp() {
		game = new SnakeNLadder();
	}

	@Test
	public void testRollDice() {
		int diceValue = game.rollDice();
		assertTrue(diceValue >= 1 && diceValue <= 6, "Dice value should be between 1 and 6");
	}


	@Test
	void testCalculatePlayerValue() {
		int playerId = 0;
		int playerPosition = 0;
		int diceValue = 5;

		// Test when the player does not encounter a snake or ladder
		int newPosition = game.calculatePlayerValue(playerId, playerPosition, diceValue);
		assertEquals(playerPosition + diceValue, newPosition, "Player should move forward by dice value");

		// Test when the player encounters a snake
		game.snake.put(playerPosition + diceValue, playerPosition + diceValue - 4);
		newPosition = game.calculatePlayerValue(playerId, playerPosition, diceValue);
		assertEquals(playerPosition + diceValue - 4, newPosition, "Player should move backward due to snake");


		// Test when the player's new position exceeds the win point
		playerPosition = 95;
		diceValue = 6;
		newPosition = game.calculatePlayerValue(playerId, playerPosition, diceValue);
		assertEquals(playerPosition, newPosition, "Player should not move beyond the win point");
	}

	@Test
	void testIsWin() {
		// Test when playerPosition is equal to WINPOINT
		int playerPosition = 100;
		assertTrue(game.isWin(playerPosition), "Player should win when playerPosition is equal to WINPOINT");

		// Test when playerPosition is less than WINPOINT
		playerPosition = 50;
		assertFalse(game.isWin(playerPosition), "Player should not win when playerPosition is less than WINPOINT");

		// Test when playerPosition is greater than WINPOINT
		playerPosition = 120;
		assertFalse(game.isWin(playerPosition), "Player should not win when playerPosition is greater than WINPOINT");
	}


}

