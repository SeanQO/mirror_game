package model;

public class Game {
	private Player player;
	private Board board;
	private int score;
	public Game(String playerName, int rows, int columns, int mirrorNumber) {
		player = new Player(playerName);
		board = new Board(rows, columns, mirrorNumber);
		board.generateBoxes();
		score = 0;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public String drawBoard() {
		return board.toString();
	}
	
}
