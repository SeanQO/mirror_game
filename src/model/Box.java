package model;

public class Box {
	private Box upperBox;
	private Box bottomBox;
	private Box rightBox;
	private Box leftBox;
	private int row;
	private int column;
	private Mirror mirror;
	
	public Box(int row, int column) {
		this.row = row;
		this.column = column;
		upperBox = null;
		bottomBox = null;
		rightBox = null;
		leftBox = null;
		mirror = Mirror.EMPTY;
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

	public Mirror getMirror() {
		return mirror;
	}
	
	public void setMirror(Mirror mirror) {
		this.mirror = mirror;
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
