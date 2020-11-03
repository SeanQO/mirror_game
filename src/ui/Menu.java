package ui;
import java.io.IOException;
import java.util.Scanner;

import model.DataManager;
import model.Game;

public class Menu {
	private Scanner in;
	private DataManager dManager;
	public Menu() {
		in = new Scanner(System.in);
		dManager = new DataManager();
	}
	
	private void pressAnyKeyToContinue() {
		System.out.println("************************");
		System.out.println("Press any key to continue");
		in.nextLine();
	}
	
	public void startProgram() {
		try {
			dManager.loadData();
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("-Couldnt find any previous saved data.");
		}catch (IOException ioException) {
			System.err.println("-Couldnt find any previous saved data.");
		}
		int option = 0;
		boolean exit = false;
		do {
			try {
				showMenu();
				option  = Integer.parseInt( in.nextLine() );
				exit = runOptions(option);
				if (option < 1 || option > 3) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option, is not a valid selection.\n Please only enter the number next to the option." );
				pressAnyKeyToContinue();
			}
	
		} while (!exit);
		
		try {
			dManager.saveData();
		} catch (IOException ioException) {
			System.err.println("-Couldnt save data.");
		}
		
	}
	
	private boolean runOptions(int option) {
		boolean exit = false;
		switch (option) {
		case 1:
			do {
				try {
					runOptionOne();
				} catch (NumberFormatException numberFormatException) {
					
					
				}
				
			} while (exit);
			
			break;

		case 3:
			exit = true;
			break;
		}
		
		
		return exit;
	}
	
	private void showMenu() {
		System.out.println("**********************");
		System.out.println("****PRINCIPAL MENU****");
		System.out.println("**********************");
		System.out.println("1.Play");
		System.out.println("3.Exit");
	}
	
	private void runOptionOne() {
		Game game = null;
		boolean error = false;
		do {
			error = false;
			try {
				game = startGame();
			} catch (Exception e) {
				error = true;
				System.err.println("the entered information is invalid, please check the format: name n m k");
			}
		} while (error);
		
		game.getBoard().generateBoxes();
		System.out.println(game.drawBoard());
		
		
	}
	
	private Game startGame() throws NumberFormatException{
		System.out.println("**********************");
		System.out.println("-Enter the name, dimensions of the board and mirror number separated by space:");
		System.out.println("example: name 2 2 1 --> to create a gamer for the user 'name' and a board of 2x2  [rows]x[columns] and 1 mirror.");
		System.out.println("the maximum number of columns is 26 - from A-Z");
		String[] gameConditions = in.nextLine().split("\\s+"); 
		
		String name = gameConditions[0];
		int rows = Integer.parseInt( gameConditions[1] );
		int columns = Integer.parseInt( gameConditions[2] );
		int mirrorNumber = Integer.parseInt( gameConditions[3]);
		
		Game game = new Game(name, rows, columns, mirrorNumber);
		
		return game;
		
	} 
	
	
	
}
