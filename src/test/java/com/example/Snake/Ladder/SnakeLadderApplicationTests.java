package com.example.Snake.Ladder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	public void testCalculatePlayerValue() {
		int playerPosition = 0;
		int diceValue = 6;

		// Test when the player does not encounter a snake or ladder
		int newPosition = game.calculatePlayerValue("Player", playerPosition, diceValue);
		assertEquals(playerPosition + diceValue, newPosition, "Player should move forward by dice value");

		// Test when the player encounters a snake
		game.snake.put(playerPosition + diceValue, playerPosition + diceValue - 5);
		newPosition = game.calculatePlayerValue("Player", playerPosition, diceValue);
		assertEquals(playerPosition + diceValue - 5, newPosition, "Player should move backward due to snake");

		// Test when the player encounters a ladder
		game.snake.remove(playerPosition + diceValue - 5);
		game.ladder.put(playerPosition + diceValue, playerPosition + diceValue + 10);
		newPosition = game.calculatePlayerValue("Player", playerPosition, diceValue);
		assertEquals(playerPosition + diceValue + 10, newPosition, "Player should move forward due to ladder");

		// Test when the player's new position exceeds the win point
		playerPosition = 95;
		diceValue = 6;
		newPosition = game.calculatePlayerValue("Player", playerPosition, diceValue);
		assertEquals(playerPosition + diceValue, newPosition, "Player should move forward but not beyond the win point");
	}

	@Test
	public void testIsWin() {
		assertFalse(game.isWin(50), "Player should not win at position 50");
		assertFalse(game.isWin(99), "Player should not win at position 99");
		assertTrue(game.isWin(100), "Player should win at position 100");
	}

}

