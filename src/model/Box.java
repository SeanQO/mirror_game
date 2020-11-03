package model;

public class Box {
	private Box upperBox;
	private Box bottomBox;
	private Box rightBox;
	private Box leftBox;
	/*
	 rows {1,n}
	 columns{65/A,90/z}
	 */
	private int row;
	private int column;
	
	public Box(int row, int column) {
		this.row = row;
		this.column = column;
		upperBox = null;
		bottomBox = null;
		rightBox = null;
		leftBox = null;
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Box getUpperBox() {
		return upperBox;
	}

	public Box getBottomBox() {
		return bottomBox;
	}

	public Box getRightBox() {
		return rightBox;
	}

	public Box getLeftBox() {
		return leftBox;
	}
	
	public String getId() {
		String id = row + "";
		
		id += (char)column;
		
		return id;
	}

	public void setUpperBox(Box upperBox) {
		this.upperBox = upperBox;
	}

	public void setBottomBox(Box bottomBox) {
		this.bottomBox = bottomBox;
	}

	public void setRightBox(Box rightBox) {
		this.rightBox = rightBox;
	}

	public void setLeftBox(Box leftBox) {
		this.leftBox = leftBox;
	}
	
}
