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

	public String drawBoard(String startBox, String finishBox , String findedBox) {
		String playerNameAndscore = playerName + ": " + score;
		return playerNameAndscore + "\n" + board.toString(startBox, finishBox, findedBox, cheat);

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
		System.out.println(finalBox.getId());
		return finalBox.getId();
	}

	public String shootFromCorner(String input) {
		String id = input.substring(0, input.length()-1);
		Box startBox = board.getBox(id);
		Box finalBox = null;
		
		if (startBox.getRow() == 1 && startBox.getColumn() == 65) {
			if (input.charAt(input.length()-1) == 'V') {
				finalBox = shootDownward(startBox);
			}else {
				finalBox = shootRight(startBox);
			}
			
		}else if (startBox.getRow() == board.getRows() && startBox.getColumn() == 65) {
			if (input.charAt(input.length()-1) == 'V') {
				finalBox = shootUpward(startBox);
			}else {
				finalBox = shootRight(startBox);
			}
			
		}else if (startBox.getRow() == 1 && startBox.getColumn() == board.getColumns()) {
			if (input.charAt(input.length()-1) == 'V') {
				finalBox = shootDownward(startBox);
			}else {
				finalBox = shootLeft(startBox);
			}
			
		}else if (startBox.getRow() == board.getRows() && startBox.getColumn() == board.getColumns() ) {
			if (input.charAt(input.length()-1) == 'V') {
				finalBox = shootUpward(startBox);
			}else {
				finalBox = shootLeft(startBox);
			}
			
		}
		
		return finalBox.getId();
	}

	private Box shootLeft(Box currentBox) {
		Box finalBox = currentBox;
		switch (currentBox.getMirror()) {
		case RIGHT_MIRROR:
			if (currentBox.getBottomBox() != null) {
				finalBox = shootDownward(currentBox.getBottomBox());
			}
			break;

		case LEFT_MIRROR:
			if (currentBox.getUpperBox() != null) {
				finalBox = shootUpward(currentBox.getUpperBox());
			}
			break;
		case EMPTY:
			if (currentBox.getLeftBox() != null) {
				finalBox = shootLeft(currentBox.getLeftBox());
			}
			break;
		}
		return finalBox;
	}


	private Box shootRight(Box currentBox) {
		Box finalBox = currentBox;
		switch (currentBox.getMirror()) {
		case RIGHT_MIRROR:
			if (currentBox.getUpperBox() != null) {
				finalBox = shootUpward(currentBox.getUpperBox());
			}
			break;

		case LEFT_MIRROR:
			if (currentBox.getBottomBox() != null) {
				finalBox = shootDownward(currentBox.getBottomBox());
			}
			break;
		case EMPTY:
			if (currentBox.getRightBox() != null) {
				finalBox = shootRight(currentBox.getRightBox());
			}
			break;
		}
		return finalBox;
	}


	private Box shootUpward(Box currentBox) {
		Box finalBox = currentBox;
		switch (currentBox.getMirror()) {
		case RIGHT_MIRROR:
			if (currentBox.getRightBox() != null) {
				finalBox = shootRight(currentBox.getRightBox());
			}
			break;

		case LEFT_MIRROR:
			if (currentBox.getLeftBox() != null) {
				finalBox = shootLeft(currentBox.getLeftBox());
			}
			break;
		case EMPTY:
			if (currentBox.getUpperBox() != null) {
				finalBox = shootUpward(currentBox.getUpperBox());
			}
			break;
		}
		return finalBox;
	}

	private Box shootDownward(Box currentBox) {
		Box finalBox = currentBox;
		switch (currentBox.getMirror()) {
		case RIGHT_MIRROR:
			if (currentBox.getLeftBox() != null) {
				finalBox = shootLeft(currentBox.getLeftBox());
			}
			break;

		case LEFT_MIRROR:
			if (currentBox.getRightBox() != null) {
				finalBox = shootRight(currentBox.getRightBox());
			}
			break;
		case EMPTY:
			if (currentBox.getBottomBox() != null) {
				finalBox = shootDownward(currentBox.getBottomBox());
			}
			break;
		}
		return finalBox;
	}

	public String locate(String input) {
		Box locateBox = board.getBox(input.substring(1,input.length()-1));
		String mirrorDirection = input.charAt(input.length()-1) + "";
		System.out.println("LocateB: " + locateBox.getId() + " " + locateBox.getMirror() + "Mdir: " + mirrorDirection);
		System.out.println(locateBox.getMirror().equals(Mirror.RIGHT_MIRROR) + " L: " + locateBox.getMirror().equals(Mirror.LEFT_MIRROR));
		if (locateBox.getMirror() != Mirror.EMPTY) {
			if (locateBox.getMirror().equals(Mirror.RIGHT_MIRROR) && mirrorDirection.equals("R")) {
				System.out.println("Located");
				locateBox.setFounded(true);
				
			}else if (locateBox.getMirror().equals(Mirror.LEFT_MIRROR) && mirrorDirection.equals("L")) {
				System.out.println("Located");
				locateBox.setFounded(true);
				
			}
		}
		
		return locateBox.getId();
	}

}

