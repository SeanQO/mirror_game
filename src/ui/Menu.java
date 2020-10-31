package ui;
import java.util.Scanner;
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

			} catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option, is not a valid selection.\n Please only enter the number next to the option." );
				pressAnyKeyToContinue();
			}
	
		} while (!exit);
	}
	
	private boolean runOptions(int option) {
		boolean exit = false;
		
		
		
		return exit;
	}
	
	private void showMenu() {
		
	}
	
}
