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

	public void generateBoxes() {
		firstBox = new Box(/*upperBox*/null, /*bottomBox*/null, /*rightBox*/null, /*leftBox*/null, "1A");
		if (rows != 1 && columns != 1) {
			Box lastAddedBox = firstBox;
			generateBoxes(lastAddedBox,rows,columns);
			
		}

	}
	//the recursive method is going to start from the last place a box can be (rows,columns), then is going to check if the previous box is added, if its added it adds the new box, if not calls itself 
	//it moves going trough the columns from left to right, then jumps to the previous row and goes the same way(left to right).
	private void generateBoxes(Box lastAddedBox, int row, int column) {
		//validate if the previous box is already added or not, in two scenarios: if the box to add its not a corner or if the box to add its a corner.
		if ( ( row == lastAddedBox.getNumberIdRow() && (column - 1) == lastAddedBox.getNumberIdColumn() ) 
				|| ( row-1 == lastAddedBox.getNumberIdRow() && columns == lastAddedBox.getNumberIdColumn()  )  ) {
				lastAddedBox = addBox(lastAddedBox);
			//if the last added is on the last position, doesn't add more boxes. 
			if (lastAddedBox.getNumberIdColumn() != columns || lastAddedBox.getNumberIdRow() != rows) {
				generateBoxes(lastAddedBox,rows,columns);
			} 
			
		}else {
			if (column - 1 >= 65) {
				generateBoxes(lastAddedBox,row,column-1);
			}else {
				if (row - 1 >=1 ) {
					generateBoxes(lastAddedBox,row-1,columns);
				}
				
			}
			
		}
	}

	//adds and return the new box 
	private Box addBox(Box lastAddedBox) {
		Box newBox = null;

		//if the last added it's not on the last column, then it must be next to the new box to add
		if (lastAddedBox.getNumberIdColumn() != columns) {
			if (lastAddedBox.getNumberIdRow() == 1) {
				newBox = new Box(/*upperBox*/null, /*bottomBox*/null, /*rightBox*/null, /*leftBox*/lastAddedBox, "1" + (char)(lastAddedBox.getNumberIdColumn() + 1) );
				lastAddedBox.setRightBox(newBox);
			}else {
				newBox = new Box(/*upperBox*/getBox((lastAddedBox.getNumberIdRow()-1)+""+(char)(lastAddedBox.getNumberIdColumn() + 1)), /*bottomBox*/null, /*rightBox*/null, /*leftBox*/lastAddedBox, 
						lastAddedBox.getNumberIdRow()+""+(char)(lastAddedBox.getNumberIdColumn() + 1) );
				lastAddedBox.setRightBox(newBox);
				Box upperBox = getBox((lastAddedBox.getNumberIdRow()-1)+""+(char)(lastAddedBox.getNumberIdColumn() + 1));
				upperBox.setBottomBox(newBox);
			}
			
		}else {
			newBox = new Box(/*upperBox*/getBox((lastAddedBox.getNumberIdRow())+"A"), /*bottomBox*/null, /*rightBox*/null, /*leftBox*/null,
					(lastAddedBox.getNumberIdRow()+1)+"A");
			Box upperBox = getBox((lastAddedBox.getNumberIdRow())+"A");
			upperBox.setBottomBox(newBox);
		}

		return newBox;
	}

	//the given id MUST be a valid id, otherwise the method WONT work
	public Box getBox(String id) {
		Box box = null;
		int rowNumber = Integer.parseInt( id.substring(0, id.length()-1 ) );
		int columnNumber = id.charAt(id.length()-1);
		
		if (!firstBox.getStringId().equals(id)) {
			if (firstBox.getNumberIdRow() != rowNumber) {
				 box = getBox(id , firstBox.getBottomBox(), rowNumber, columnNumber);
				
			}else {
				box = getBox(id , firstBox.getRightBox(), rowNumber, columnNumber);
				
			}
			
		}else {
			box = firstBox;
		}

		return box;
	}

	private Box getBox(String id,Box currentBox, int rowNumber, int columnNumber) {
		Box box = null;
		if (currentBox.getStringId().equals(id)) {
			box = currentBox;
		}else {
			if (currentBox.getNumberIdRow() != rowNumber) {
				box = getBox(id , currentBox.getBottomBox(), rowNumber, columnNumber);

			}else if(currentBox.getNumberIdColumn() != columnNumber){
				box = getBox(id , currentBox.getRightBox(), rowNumber, columnNumber);
			}
		}
		
		return box;
	}

	public String drawBoard() {
		String board = "["+ firstBox.getStringId() +"]";
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
			board += "\n["+ firstRowBox.getBottomBox().getStringId() +"]" ;
			board += drawBoard(firstRowBox.getBottomBox(),null);
		}else {
			board += "["+ nextInRow.getStringId() +"]" ;
			if (nextInRow.getRightBox() != null) {
				board += drawBoard(firstRowBox,nextInRow.getRightBox());
			}else if (firstRowBox.getBottomBox() != null) {
				board += "\n["+ firstRowBox.getBottomBox().getStringId() +"]" ;
				board += drawBoard(firstRowBox.getBottomBox(),firstRowBox.getBottomBox().getRightBox());
			}
		}
		return board;
	}


}

























