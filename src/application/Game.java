package application;

import java.io.Serializable;

import design.Player;
import screen.GameScreenManager;

public class Game implements Serializable {
	
	private static final long serialVersionUID = 8915093762091507279L;
	private Player player;
	private String name;
	private int score;
	private int hiScore;
	private GameScreenManager gameManager;
	
	public Game(Player player) {
		this.player = player;
		this.name = this.player.getName();
		this.score = this.player.getScore();
		gameManager = new GameScreenManager(player, this);
		gameManager.startNewGame();
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return player.getScore();
	}
	
	public int getHiScore() {
		return player.getHiScore();
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setHiScore(int score) {
		this.hiScore = score;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public GameScreenManager getManager() {
		return gameManager;
	}
}
