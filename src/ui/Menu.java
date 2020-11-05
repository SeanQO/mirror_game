package ui;
import java.io.IOException;

import java.util.Scanner;
import exceptions.InvalidOptionException;
import model.DataManager;

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
		System.out.println("2.Player list");
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
			try {
				dManager.saveData();
			} catch (IOException ioException) {
				System.err.println("-Couldnt save data.");
			}
			break;
			
		case 2:
			runOptionTwo();
			break;

		case 3:
			exit = true;
			break;
		}


		return exit;
	}

	private void runOptionOne() {
		try {
			startGame();
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

	}

	private void startGame() throws NumberFormatException, ArrayIndexOutOfBoundsException{
		System.out.println("**********************");
		System.out.println("-Enter the name, dimensions of the board and mirror number separated by space:");
		System.out.println("example: name 2 2 1 --> to create a gamer for the user 'name' and a board of 2x2  [rows]x[columns] and 1 mirror.");
		System.out.println("the maximum number of columns is 26 - from A-Z");
		String input = in.nextLine();
		if (!input.equals("menu")) {
			
			String[] gameConditions = input.split("\\s+"); 
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
			
			dManager.newGame(name, rows, columns, mirrorNumber);
			runGame("","","");
			System.out.println("**********************");
			System.out.println("Player " + dManager.getGame().getPlayerName() + " score: " + dManager.getGame().getScore() + " failed attempts: " + dManager.getGame().getFailedAttempts());

		}
		
		
	} 

	private void runGame(String sBox, String eBox, String located) {
		if(dManager.getGame().getRemainingMirrors() > 0) {
			System.out.println("**********************");
			System.out.println("Input options:");
			System.out.println("to shoot the lazer bean: ex. 1B. --> from a corner 1AV or 1AH: vertical or horizontal.");
			System.out.println("To locate a mirror: L follow by the position and the direction of the mirror: ex. L1AR or L1AL --> R for / and L for \\");
			System.out.println("to go back to the main menu: 'menu'");
			System.out.println("-All input characters must be on uppercase, cant shoot from a box that not on the border of the matrix.");
			System.out.println("**********************");
			System.out.println("- S --> where the laser entered, E --> where the laser came out, M --> if the laser entered and came out in the same box.");
			System.out.println("- X --> the box sought doesnt have a mirror or the mirror is not in the correct direction");
			System.out.println(dManager.getGame().drawBoard(sBox, eBox, located));
			String input = in.nextLine();

			try {
				if (input.equals("cheat")) {
					dManager.getGame().setCheat(!dManager.getGame().isCheat());
					runGame("","","");

				}else {
					if (!input.equals("menu")) {
						boolean isShootFromCorner = isShootFromCorner(input);

						if (isShootFromCorner) {
							sBox = input.substring(0,input.length()-1);
							eBox = dManager.getGame().shootFromCorner(input);
							runGame(sBox,eBox,"");

						}else {
							boolean isShoot = isShoot(input);
							if (isShoot) {
								sBox = input;
								eBox = dManager.getGame().shoot(input);
								runGame(sBox,eBox,"");

							}else {
								boolean isLocate = isLocate(input);
								if (isLocate) {
									located = dManager.getGame().locate(input);
									runGame("","",located);

								}else {
									throw new InvalidOptionException(input);

								}

							}

						}

					}else {
						String pName = dManager.getGame().getPlayerName();
						int fScore = dManager.getGame().getScore();
						int n  = dManager.getGame().getBoard().getRows();
						int m = dManager.getGame().getBoard().getColumns();
						int k = dManager.getGame().getBoard().getMirrorNumber();
						dManager.addPlayer(pName,fScore,n,m,k);
					}

				}


			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				pressAnyKeyToContinue();
				runGame("","","");
			}catch (IndexOutOfBoundsException indexOutOfBoundsException) {
				System.err.println("The input option: " + input + " does not match any known option:");
				pressAnyKeyToContinue();
				runGame("","","");
			}
		}else {
			String pName = dManager.getGame().getPlayerName();
			int fScore = dManager.getGame().getScore();
			int n  = dManager.getGame().getBoard().getRows();
			int m = dManager.getGame().getBoard().getColumns();
			int k = dManager.getGame().getBoard().getMirrorNumber();
			dManager.addPlayer(pName,fScore,n,m,k);
			System.out.println("**********************");
			System.out.println("Winner, you have found all the mirrors!.");
			System.out.println("**********************");
		}

	}
	
	private boolean validId(String input) {
		boolean valid = false;

		try {
			int column = (int) input.charAt(input.length()-1);
			int rows = Integer.parseInt( input.substring(0,input.length()-1) );

			if (column > dManager.getGame().getBoard().getColumns()) {
				throw new NumberFormatException();
			}else if (rows > dManager.getGame().getBoard().getRows()) {
				throw new NumberFormatException();
			}
			valid = true;
		} catch (NumberFormatException numberFormatException) {

		}

		return valid;
	}

	private boolean isShootFromCorner(String input) {
		boolean isShoot = false;
		if (input.charAt(input.length()-1) == 'V' || input.charAt(input.length()-1) == 'H') {
			if (validId(input.substring(0,input.length()-1) ) ) {
				isShoot = true;
			}
		}
		return isShoot;
	}

	private boolean isShoot(String input) {
		boolean isShoot = false;
		isShoot = validId(input);
		if (isShoot) {
			int column = (int) input.charAt(input.length()-1);
			int rows = Integer.parseInt( input.substring(0,input.length()-1) );
			if (rows == 1 && column == 65) {
				isShoot = false;
			}else if (rows == dManager.getGame().getBoard().getRows() && column == 65) {
				isShoot = false;
			}else if (rows == 1 && column == dManager.getGame().getBoard().getColumns()) {
				isShoot = false;
			}else if (rows == dManager.getGame().getBoard().getRows() && column == dManager.getGame().getBoard().getColumns() ) {
				isShoot = false;
			}else if (rows > 1 && rows < dManager.getGame().getBoard().getRows() && column > 65 && column < dManager.getGame().getBoard().getColumns() ) {
				isShoot = false;
			}

		}

		return isShoot;
	}

	private boolean isLocate(String input) {
		boolean isLocate = false;
		if(input.charAt(input.length()-1) == 'R' || input.charAt(input.length()-1) == 'L') {
			if (input.charAt(0) == 'L') {
				isLocate = validId(input.substring(1,input.length()-1));
			}
		}
		return isLocate;
	}

	
	private void runOptionTwo() {
		System.out.println("************************");
		System.out.println("*Player list and scores*");
		System.out.println("************************");
		System.out.println(dManager.toString());
		
	}
	
}
