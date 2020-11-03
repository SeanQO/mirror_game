package model;
public class Board {
	private Box firstBox;
	private int rows;
	private int columns;
	//private int mirrorNumber;

	public Board(int rows, int columns, int mirrorNumber) {
		this.rows = rows;
		this.columns = columns + 64;
		//this.mirrorNumber = mirrorNumber;
	}
	
	
	public void fillBoard() {
		firstBox = new Box(1,65);
		if (rows != 1 && columns != 1) {
			Box lastAddedBox = firstBox;
			generateBoxes(lastAddedBox,1,65, null);
		}
	}

	
	public void generateBoxes(Box lastAddedBox, int currentRow, int currentColumn, Box upperBox) {
		if (currentColumn + 1 <= columns) {
			Box newBox = new Box(currentRow,currentColumn+1);
			lastAddedBox.setRightBox(newBox);
			newBox.setLeftBox(lastAddedBox);
			if (upperBox == null) {
				generateBoxes(newBox, currentRow, currentColumn +1, null);
			}else {
				upperBox.setBottomBox(newBox);
				newBox.setUpperBox(upperBox);
				generateBoxes(newBox, currentRow, currentColumn + 1, upperBox.getRightBox());
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
	
	public Box getFirstColumnBox(int row) {
		Box newBox = firstBox.getBottomBox();
		if (firstBox.getBottomBox().getRow() != row) {
			newBox = getFirstColumnBox(firstBox.getBottomBox().getBottomBox(),row);
		}
		
		return newBox;
	}

	private Box getFirstColumnBox(Box currentBox, int row) {
		Box newBox = currentBox;
		if (currentBox.getRow() != row) {
			newBox = getFirstColumnBox(firstBox.getBottomBox().getBottomBox(),row);
		}
		return newBox;
	}


	public String drawBoard() {
		String board = "["+ firstBox.getId() +"]";
		if (firstBox.getRightBox() != null) {
			board += drawBoard(firstBox, firstBox.getRightBox());
		}else if (firstBox.getBottomBox() != null) {
			board += drawBoard(firstBox.getBottomBox(),null);
		}


		return board;
	}

	
	private String drawBoard(Box firstRowBox, Box nextInRow) {
		String board = "";
		if (nextInRow == null) {
			board += "\n["+ firstRowBox.getBottomBox().getId() +"]" ;
			board += drawBoard(firstRowBox.getBottomBox(),null);
		}else {
			board += "["+ nextInRow.getId() +"]" ;
			if (nextInRow.getRightBox() != null) {
				board += drawBoard(firstRowBox,nextInRow.getRightBox());
			}else if (firstRowBox.getBottomBox() != null) {
				board += "\n["+ firstRowBox.getBottomBox().getId() +"]" ;
				board += drawBoard(firstRowBox.getBottomBox(),firstRowBox.getBottomBox().getRightBox());
			}
		}
		return board;
	}


}

























