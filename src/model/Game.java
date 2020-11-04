package model;

public class Game {
	private String playerName;
	private Board board;
	private int score;
	public Game(String playerName, int rows, int columns, int mirrorNumber) {
		this.playerName = playerName;
		board = new Board(rows, columns, mirrorNumber);
		board.generateBoxes();
		board.addMirrors(mirrorNumber);
		score = 0;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public String drawBoard() {
		String playerNameAndscore = playerName + ": " + score;
		return playerNameAndscore + "\n" +board.toString();
	}
	
	public void shoot(String id) {
		
	}
	
	public void shootFromCorner(String id) {
		
	}
	
	public void locate(String location) {
		
	}
	
}

