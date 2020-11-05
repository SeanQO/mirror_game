package model;

import java.io.Serializable;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private int score;
	private Player father;
	private Player left;
	private Player rigth;
	
	public Player(String name,int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}

	public Player getFather() {
		return father;
	}

	public void setFather(Player father) {
		this.father = father;
	}

	public Player getLeft() {
		return left;
	}

	public void setLeft(Player left) {
		this.left = left;
	}

	public Player getRigth() {
		return rigth;
	}

	public void setRigth(Player rigth) {
		this.rigth = rigth;
	}

	
	
}
