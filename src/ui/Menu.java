package ui;
import java.io.IOException;
import java.util.Scanner;

import exceptions.InvalidOptionException;
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
		
		principalMenu();
		
		try {
			dManager.saveData();
		} catch (IOException ioException) {
			System.err.println("-Couldnt save data.");
		}
		
	}
	
	private void showMenu() {
		System.out.println("**********************");
		System.out.println("****PRINCIPAL MENU****");
		System.out.println("**********************");
		System.out.println("1.Play");
		System.out.println("3.Exit");
	}
	
	private void principalMenu() {
		showMenu();
		int option = 0;
		boolean exit = false;
		
		try {
			option  = Integer.parseInt( in.nextLine() );
			exit = runOptions(option);
			if (option < 1 || option > 3) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException numberFormatException) {
			System.err.println("the entered option, is not a valid selection.\n Please only enter the number next to the option." );
			pressAnyKeyToContinue();
			principalMenu();
		}
		
		if (!exit) {
			principalMenu();
		}
	}
	
	private boolean runOptions(int option) {
		boolean exit = false;
		switch (option) {
		case 1:
			runOptionOne();
			break;

		case 3:
			exit = true;
			break;
		}
		
		
		return exit;
	}
	
	private void runOptionOne() {
		Game game = null;
		try {
			game = startGame();
		} catch (NumberFormatException numberFormatException) {
			System.err.println("the entered information is invalid, please check the format: name n m k \n"
					+ "the allowed size is nx26, and the number of mirros from one to n*k");
			pressAnyKeyToContinue();
			runOptionOne();
		}catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			System.err.println("the entered information is invalid, please check the format: name n m k \n"
					+ "the allowed size is nx26, and the number of mirros from one to n*k");
			pressAnyKeyToContinue();
			runOptionOne();
		}
		game.getBoard().generateBoxes();
		game(game);
	}
	
	private Game startGame() throws NumberFormatException, ArrayIndexOutOfBoundsException{
		System.out.println("**********************");
		System.out.println("-Enter the name, dimensions of the board and mirror number separated by space:");
		System.out.println("example: name 2 2 1 --> to create a gamer for the user 'name' and a board of 2x2  [rows]x[columns] and 1 mirror.");
		System.out.println("the maximum number of columns is 26 - from A-Z");
		String[] gameConditions = in.nextLine().split("\\s+"); 
		
		String name = gameConditions[0];
		int rows = Integer.parseInt( gameConditions[1] );
		int columns = Integer.parseInt( gameConditions[2] );
		if (columns > 26) {
			throw new NumberFormatException();
		}
		int mirrorNumber = Integer.parseInt( gameConditions[3]);
		if (mirrorNumber > (rows*columns)) {
			throw new NumberFormatException();
		}
		Game game = new Game(name, rows, columns, mirrorNumber);
		
		return game;
		
	} 
	
	private void game(Game game) {
		System.out.println("Input options:");
		System.out.println("to shoot the lazer bean: ex. 1B. --> from a corner 1AV or 1AH: vertical or horizontal.");
		System.out.println("To locate a mirror: L follow by the position and the direction of the mirror: ex. L1AR or L1AL");
		System.out.println("to go back to the main menu: 'menu'");
		System.out.println("**********************");
		System.out.println(game.drawBoard());
		String input = "";
		try {
			 input = in.nextLine(); 
			 boolean valid = validateInput(input);
			 if(!valid) {
				 throw new InvalidOptionException(input);
			 }
		} catch (InvalidOptionException invalidOptionException) {
			System.err.println(invalidOptionException.getMessage());
			game(game);
		}
		
		if (!input.equals("menu")) {
			
		}
		
	}
	
	private boolean validateInput(String input) {
		boolean valid = false;
		return valid;
	}
	
}
