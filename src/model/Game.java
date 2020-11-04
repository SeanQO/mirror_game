package model;

public class Game {
	private String playerName;
	private Board board;
	private int score;
	private boolean cheat;
	public Game(String playerName, int rows, int columns, int mirrorNumber) {
		this.playerName = playerName;
		board = new Board(rows, columns, mirrorNumber);
		board.generateBoxes();
		board.addMirrors(mirrorNumber);
		score = 0;
		cheat = false;
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

	public boolean isCheat() {
		return cheat;
	}

	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}

	public String drawBoard() {
		String playerNameAndscore = playerName + ": " + score;
		return playerNameAndscore + "\n" + board.toString(cheat);

	}

	public void shoot(String input) {
		Box startBox = board.getBox(input);

		if (startBox.getColumn() == board.getColumns()) {
			shootLeft();
		}else if(startBox.getColumn() == 65) {
			shootRight();
		}else if (startBox.getRow() == 1) {
			shootDownward();
		}else if (startBox.getRow() == board.getRows()) {
			shootUpward();
		}


	}

	public void shootFromCorner(String input) {

	}

	private void shootLeft() {

	}


	private void shootRight() {

	}


	private void shootUpward() {

	}

	private void shootDownward() {

	}

	public void locate(String input) {

	}

}

