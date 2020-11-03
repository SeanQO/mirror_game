package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataManager {
	private Player root;
	private final String GAME_FILE_NAME = "data/game_data.mgd";
	
	public DataManager(){}
	
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
	
	
}
