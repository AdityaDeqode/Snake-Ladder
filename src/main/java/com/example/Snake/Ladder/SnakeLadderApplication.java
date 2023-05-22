package com.example.Snake.Ladder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnakeLadderApplication {

	public static void main(String[] args) {

		SpringApplication.run(SnakeLadderApplication.class, args);
		System.out.println("Welcome to snake ladder game ");
		SnakeNLadder snakeLadder = new SnakeNLadder();
		snakeLadder.startGame();
	}

}
