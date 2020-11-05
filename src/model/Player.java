package model;

import java.io.Serializable;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private int score;
	private int n;
	private int m;
	private int k;
	private Player father;
	private Player left;
	private Player rigth;
	
	public Player(String name,int score, int n, int m, int k) {
		this.name = name;
		this.score = score;
		this.n = n; 
		this.m = m;
		this.k = k;
	}
	
	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public int getK() {
		return k;
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
	

	public Player getLeft() {
		return left;
	}

	public Player getRigth() {
		return rigth;
	}
	
	public void setFather(Player father) {
		this.father = father;
	}
	
	public void setLeft(Player left) {
		this.left = left;
	}
	
	public void setRigth(Player rigth) {
		this.rigth = rigth;
	}

	
	
}
