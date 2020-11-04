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
		
		if (mirrorsToAssign > 0) {
			int row = random.nextInt(rows) + 1;
			int column = random.nextInt(columns-64)+65;
			addMirrors(firstBox,row, column);
			if (mirrorsToAssign - 1 > 0) {
				addMirrors(mirrorsToAssign - 1);
			}
			
		}
	
	}
	
	private void addMirrors(Box currentBox,int row, int column) {
		if (currentBox.getRow() == row) {
			if (currentBox.getColumn() == column) {
				Random random = new Random();
				Mirror mirror = null;
				if (random.nextInt(1) == 1) {
					mirror = Mirror.LEFT_MIRROR;
				}else {
					mirror = Mirror.RIGHT_MIRROR;
				}
				currentBox.setMirror(mirror);
			}else {
				addMirrors(currentBox.getRightBox(),row,column);
			}
			
		}else {
			addMirrors(currentBox.getBottomBox(), row, column);
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
	
	@Override
	public String toString() {
		String board = " " + columnsLine(firstBox) + "\n1[ ]";
		if (firstBox.getRightBox() != null) {
			board += toString(firstBox, firstBox.getRightBox());
		}else if (firstBox.getBottomBox() != null) {
			board += toString(firstBox.getBottomBox(),null);
		}


		return board;
	}

	
	private String toString(Box firstRowBox, Box nextInRow) {
		String board = "";
		if (nextInRow == null) {
			board += "\n" + firstRowBox.getRow() +"[ ]" ;
			board += toString(firstRowBox.getBottomBox(),null);
		}else {
			board += "[ ]" ;
			if (nextInRow.getRightBox() != null) {
				board += toString(firstRowBox,nextInRow.getRightBox());
			}else if (firstRowBox.getBottomBox() != null) {
				board += "\n" + firstRowBox.getBottomBox().getRow() +"[ ]" ;
				board += toString(firstRowBox.getBottomBox(),firstRowBox.getBottomBox().getRightBox());
			}
		}
		return board;
	}
	

}

























