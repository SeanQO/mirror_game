package model;

import java.util.Random;

public class Board {
	private Box firstBox;
	private int rows;
	private int columns;
	private int mirrorNumber;

	public Board(int rows, int columns, int mirrorNumber) {
		this.rows = rows;
		this.columns = columns + 64;
		this.mirrorNumber = mirrorNumber;
	}

	public Box getFirstBox() {
		return firstBox;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getMirrorNumber() {
		return mirrorNumber;
	}

	// *********************** Create board
	public void generateBoxes() {
		firstBox = new Box(1,65);
		if (rows != 1 && columns != 1) {
			Box lastAddedBox = firstBox;
			generateBoxes(lastAddedBox,1,65, null);
		}
	}

	private void generateBoxes(Box lastAddedBox, int currentRow, int currentColumn, Box upperBox) {
		if (currentColumn + 1 <= columns) {
			Box newBox = new Box(currentRow,currentColumn+1);
			lastAddedBox.setRightBox(newBox);
			newBox.setLeftBox(lastAddedBox);
			if (upperBox == null) {
				generateBoxes(newBox, currentRow, currentColumn +1, null);
			}else {
				upperBox.setBottomBox(newBox);
				newBox.setUpperBox(upperBox);
				if (upperBox.getRightBox() == null) {
					generateBoxes(newBox,currentRow, currentColumn + 1, upperBox );
				}else {
					generateBoxes(newBox, currentRow, currentColumn + 1, upperBox.getRightBox());
				}

			}

		}else if (currentRow +1 <= rows) {
			if (upperBox == null) {
				upperBox = firstBox;
			}else {
				upperBox = getFirstColumnBox(currentRow);
			}
			Box newBox = new Box(currentRow + 1,65);
			upperBox.setBottomBox(newBox);
			newBox.setUpperBox(upperBox);
			generateBoxes(newBox, currentRow + 1, 65, upperBox.getRightBox());
		}

	}

	private Box getFirstColumnBox(int row) {
		Box newBox = firstBox.getBottomBox();
		if (firstBox.getBottomBox().getRow() != row) {
			newBox = getFirstColumnBox(firstBox.getBottomBox().getBottomBox(),row);
		}

		return newBox;
	}

	private Box getFirstColumnBox(Box currentBox, int row) {
		Box newBox = currentBox;
		if (currentBox.getRow() != row) {
			newBox = getFirstColumnBox(currentBox.getBottomBox(),row);
		}
		return newBox;
	}

	public void addMirrors(int mirrorsToAssign) {	
		Random random = new Random();
		Mirror mirror = null;
		if (random.nextInt(2) == 1) {
			mirror = Mirror.LEFT_MIRROR;
		}else {
			mirror = Mirror.RIGHT_MIRROR;
		}
		
		int randomRow = random.nextInt(rows) + 1;
		int randomColumn = random.nextInt(columns-64)+65;
		Box randomBox = getBox(randomRow + "" + (char)randomColumn);
		
		if (randomBox.getMirror().equals(Mirror.EMPTY)) {
			randomBox.setMirror(mirror);
			if (mirrorsToAssign - 1 > 0) {
				addMirrors(mirrorsToAssign - 1);
				
			}
			
		}else {
			findEmpty(mirror,randomRow,randomColumn);
			if (mirrorsToAssign - 1 > 0) {
				addMirrors(mirrorsToAssign - 1);
				
			}
		}
		
	}

	
	private void findEmpty(Mirror mirror, int row, int column) {
		Box Box = getBox(row + "" + (char)column);
		
		if (Box.getMirror().equals(Mirror.EMPTY)) {
			Box.setMirror(mirror);
			
		}else {
			if (column + 1 <= columns) {
				findEmpty(mirror, row, column+1);
			}else if (row + 1 <= rows) {
				findEmpty(mirror, row +1, 65);
			}else {
				findEmpty(mirror, 1, 65);
			}
		}
	}

	// *********************** board to string
	private String columnsLine(Box currentBox) {
		String columnsString = " " + (char)currentBox.getColumn() + " ";
		if (currentBox.getRightBox() != null) {
			columnsString += columnsLine(currentBox.getRightBox());
		}
		return columnsString;
	}


	public String toString(String startBox, String finishBox , String findedBox, boolean cheat) {
		String board = " " + columnsLine(firstBox) + "\n1";
		String inBox = getInBoxValue(firstBox, startBox, finishBox, findedBox, cheat);
		board += "[" + inBox + "]";
		
		if (firstBox.getRightBox() != null) {
			board += toString(firstBox,firstBox.getRightBox(), startBox, finishBox, findedBox, cheat);

		}else if (firstBox.getBottomBox() != null) {
			board += "\n2" + toString(firstBox.getBottomBox(),firstBox.getBottomBox(), startBox, finishBox, findedBox,cheat);

		}


		return board;
	}

	private String toString(Box firstinRow, Box currentBox, String startBox, String finishBox , String findedBox, boolean cheat) {
		String board = "";
		String inBox = getInBoxValue(currentBox, startBox, finishBox, findedBox, cheat);
		board += "[" + inBox + "]";
		
		if (currentBox.getRightBox() != null) {
			board += toString(firstinRow, currentBox.getRightBox(),  startBox, finishBox, findedBox, cheat);

		}else if (currentBox.getBottomBox() != null) {
			board += "\n"+ currentBox.getBottomBox().getRow() + toString(firstinRow.getBottomBox(), firstinRow.getBottomBox(), startBox, finishBox, findedBox,cheat);

		}

		return board;
	}

	private String getInBoxValue(Box curentBox, String startBox, String finishBox , String findedBox, boolean cheat) {
		String value = " ";

		if (cheat) {
			value = curentBox.getMirror().getString();

		}

		if (curentBox.isFounded()) {
			value = curentBox.getMirror().getString();
		}else if (curentBox.getId().equals(findedBox)) {
			value = "X";
		}
		
		if (curentBox.getId().equals(startBox) && curentBox.getId().equals(finishBox)) {
			value = "M";
		}else if (curentBox.getId().equals(startBox)) {
			value = "S";
		}else if(curentBox.getId().equals(finishBox)) {
			value = "E";
		}

		return value;
	}

	public Box getBox(String id) {
		Box box = null;
		int column = (int) id.charAt(id.length()-1);
		int row = Integer.parseInt( id.substring(0,id.length()-1) );
		
		if (firstBox.getRow() != row) {
			if (firstBox.getBottomBox() != null) {
				box = getBox(firstBox.getBottomBox(),row, column);
			}
			
		}else {
			if (firstBox.getColumn() != column) {
				if (firstBox.getRightBox() != null) {
					box = getBox(firstBox.getRightBox(),row, column);
				}
			}else {
				box = firstBox;
			}
		}
		
		return box;
	}
	
	private Box getBox(Box currentBox, int row, int column) {
		Box box = currentBox;
		if (currentBox.getRow() != row) {
			if (currentBox.getBottomBox() != null) {
				box = getBox(currentBox.getBottomBox(),row, column);
			}
			
		}else {
			if (currentBox.getColumn() != column) {
				if (currentBox.getRightBox() != null) {
					box = getBox(currentBox.getRightBox(),row, column);
				}
			}else {
				box = currentBox;
			}
		}
		
		return box;
		
	}
	
}
