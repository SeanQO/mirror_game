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
		if ( ( rows == lastAddedBox.getNumberIdRow() && (column - 1) == lastAddedBox.getNumberIdColumn() ) 
				|| ( rows-1 == lastAddedBox.getNumberIdRow() && columns == lastAddedBox.getNumberIdColumn()  )  ) {
			lastAddedBox = addBox(lastAddedBox);
			//if the last added is on the last position, doesn't add more boxes. 
			if (lastAddedBox.getNumberIdColumn() != columns && lastAddedBox.getNumberIdRow() != rows) {
				generateBoxes(lastAddedBox,rows,columns);
			} 
		}else {
			generateBoxes(lastAddedBox,row-1,column-1);
		}
	}
	
	//adds and return the new box 
	private Box addBox(Box lastAddedBox) {
		Box newBox = null;
		
		//if the last added it's not on the last column, then it must be next to the new box to add
		if (lastAddedBox.getNumberIdColumn() != columns) {
			if (lastAddedBox.getNumberIdRow() == 1) {
				newBox = new Box(/*upperBox*/null, /*bottomBox*/null, /*rightBox*/null, /*leftBox*/lastAddedBox, "1" + (lastAddedBox.getNumberIdColumn() + 1) );
				lastAddedBox.setRightBox(newBox);
			}else {
				newBox = new Box(/*upperBox*/getBox((lastAddedBox.getNumberIdRow()-1)+""+(lastAddedBox.getNumberIdColumn() + 1)), /*bottomBox*/null, /*rightBox*/null, /*leftBox*/lastAddedBox, 
						lastAddedBox.getNumberIdRow()+""+(lastAddedBox.getNumberIdColumn() + 1) );
				lastAddedBox.setRightBox(newBox);
				
			}
		}else {
			newBox = new Box(/*upperBox*/getBox((lastAddedBox.getNumberIdRow())+"A"), /*bottomBox*/null, /*rightBox*/null, /*leftBox*/null,
					(lastAddedBox.getNumberIdRow()+1)+"A");
		}
		
		return newBox;
	}
	
	//the given id MUST be a valid id, otherwise the method WONT work
	public Box getBox(String id) {
		Box box = null;
		int rowNumber = Integer.parseInt( id.substring(0, id.length()-2 ) );
		int columnNumber = id.charAt(id.length()-1);
		if (firstBox.getNumberIdRow() != rowNumber) {
			getBox(id , firstBox.getBottomBox(), rowNumber, columnNumber);
			
		}else {
			if (firstBox.getNumberIdColumn() != columnNumber) {
				getBox(id , firstBox.getRightBox(), rowNumber, columnNumber);
			}else {
				box = firstBox;
			}
		}
		return box;
	}
	
	private Box getBox(String id,Box currentBox, int rowNumber, int columnNumber) {
		Box box = null;
		if (currentBox.getNumberIdRow() != rowNumber) {
			getBox(id , currentBox.getBottomBox(), rowNumber, columnNumber);
			
		}else {
			if (currentBox.getNumberIdColumn() != columnNumber) {
				getBox(id , currentBox.getRightBox(), rowNumber, columnNumber);
			}else {
				return currentBox;
			}
		}
		return box;
	}
	
}

























