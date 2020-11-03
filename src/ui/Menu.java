package ui;
import java.util.Scanner;

import model.Board;
public class Menu {
	private Scanner in;
	
	public Menu() {
		in = new Scanner(System.in);
	}
	
	private void pressAnyKeyToContinue() {
		System.out.println("************************");
		System.out.println("Press any key to continue");
		in.nextLine();
	}
	
	public void startProgram() {
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
	}
	
	private boolean runOptions(int option) {
		boolean exit = false;
		switch (option) {
		case 1:
			do {
				try {
					runOptionOne();
				} catch (NumberFormatException numberFormatException) {
					numberFormatException.printStackTrace();
					
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
	
	private void runOptionOne() throws NumberFormatException{
		System.out.println("**********************");
		System.out.println("-Enter the name, dimensions of the board and mirror number separated by space:");
		System.out.println("example: name 2 2 1 --> to create a gamer for the user 'name' and a board of 2x2  [rows]x[columns] and 1 mirror.");
		String[] gameConditions = in.nextLine().split("\\s+"); 
		String name = gameConditions[0];
		int rows = Integer.parseInt( gameConditions[1] );
		int columns = Integer.parseInt( gameConditions[2] );
		
		Board board = new Board(rows, columns, 0);
		board.fillBoard();		
		System.out.println(board.drawBoard());
		
		
	}
	
	
	
}
