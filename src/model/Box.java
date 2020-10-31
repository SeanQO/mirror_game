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
	private String id;
	
	public Box(Box upperBox, Box bottomBox, Box rightBox, Box leftBox, String id) {
		this.upperBox = upperBox;
		this.bottomBox = bottomBox;
		this.rightBox = rightBox;
		this.leftBox = leftBox;
		this.id = id;
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
	
	public String getStringId() {
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

	public int getNumberIdRow() {
		//id String - last letter (column)
		int rowNumber = Integer.parseInt( id.substring(0, id.length()-2 ) );
		return rowNumber;
	}
	
	public int getNumberIdColumn() {
		//id string last char, column number
		int columnNumber = id.charAt(id.length()-1);
		return columnNumber;
	}
	
}
