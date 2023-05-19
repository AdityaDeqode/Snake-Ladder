package com.example.Snake.Ladder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnakeLadderApplication {

	public static void main(String[] args) {

		SpringApplication.run(SnakeLadderApplication.class, args);
		SnakeNLadder snakeLadder = new SnakeNLadder();
		snakeLadder.startGame();
	}

}
