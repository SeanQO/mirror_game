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

	public String shoot(String input) {
		Box startBox = board.getBox(input);
		Box finalBox = null;
		if (startBox.getColumn() == board.getColumns()) {
			finalBox = shootLeft(startBox);
		}else if(startBox.getColumn() == 65) {
			finalBox = shootRight(startBox);
		}else if (startBox.getRow() == 1) {
			finalBox = shootDownward(startBox);
		}else if (startBox.getRow() == board.getRows()) {
			finalBox = shootUpward(startBox);
		}

		return finalBox.getId();
	}

	public void shootFromCorner(String input) {

	}

	private Box shootLeft(Box currentBox) {
		Box finalBox = null;
		switch (currentBox.getMirror()) {
		case RIGHT_MIRROR:
			if (currentBox.getBottomBox() != null) {
				shootDownward(currentBox.getBottomBox());
			}else {
				finalBox = currentBox;
			}
			break;

		case LEFT_MIRROR:
			if (currentBox.getUpperBox() != null) {
				shootUpward(currentBox.getUpperBox());
			}else {
				finalBox = currentBox;
			}
			break;
		case EMPTY:
			if (currentBox.getLeftBox() != null) {
				shootLeft(currentBox.getLeftBox());
			}else {
				finalBox = currentBox;
			}
			break;
		}
		
		
		return finalBox;
	}


	private Box shootRight(Box currentBox) {
		Box finalBox = null;
		return finalBox;
	}


	private Box shootUpward(Box currentBox) {
		Box finalBox = null;
		return finalBox;
	}

	private Box shootDownward(Box currentBox) {
		Box finalBox = null;
		return finalBox;
	}

	public void locate(String input) {

	}

}

