package model;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataManager {
	private Player root;
	private Game game;
	private final String GAME_FILE_NAME = "data/game_data.mgd";
	
	public DataManager(){}
	
	public void newGame(String playerName, int rows, int columns, int mirrorNumber) {
		game = new Game(playerName, rows, columns, mirrorNumber);
	}
	
	public Game getGame() {
		return game;
	}

	public void saveData() throws IOException{
		ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(GAME_FILE_NAME));
		oStream.writeObject(root);
		oStream.close();
	}
	
	public void loadData() throws IOException, ClassNotFoundException{
		ObjectInputStream oInStream = new ObjectInputStream(new FileInputStream(GAME_FILE_NAME));
		root = (Player) oInStream.readObject();
		oInStream.close();
		
	}
	
	
	public void addPlayer(String name, int score) {
		Player newPlayer = new Player(name,score); 
		
		if (root == null) {
			root = newPlayer;
		}else {
			addPlayer(root, newPlayer);
		}
		
	}
	
	private void addPlayer(Player currentPlayer, Player newPlayer) {
		
		if (currentPlayer.getScore() > newPlayer.getScore()) {
			if (currentPlayer.getLeft() == null) {
				currentPlayer.setLeft(newPlayer);
				newPlayer.setFather(currentPlayer);
				
			}else {
				addPlayer(currentPlayer.getLeft(), newPlayer);
			}
			
		}else {
			if (currentPlayer.getRigth() == null) {
				currentPlayer.setRigth(newPlayer);
				newPlayer.setFather(currentPlayer);
			}else {
				addPlayer(currentPlayer.getRigth(), newPlayer);
			}
		}
		
	}

	@Override
	public String toString() {
		String playersList = "";
		if (root != null) {
			playersList  = inOrder(root);
		}
		
		return playersList;
	}
	
	private String inOrder(Player currentPlayer) {
		String player = "";
		
		if (currentPlayer.getLeft() != null) {
			player += inOrder(currentPlayer.getLeft());
			player += "\n-" + currentPlayer.getName() + " Score: " + currentPlayer.getScore();
			if (currentPlayer.getRigth() != null) {
				player += inOrder(currentPlayer.getRigth());
				
			}
		}else {
			player += "\n-" + currentPlayer.getName() + " Score: " + currentPlayer.getScore();
			if (currentPlayer.getRigth() != null) {
				player += inOrder(currentPlayer.getRigth());
			}
			
		}
		
		return player;
	}
	
	
	
}
