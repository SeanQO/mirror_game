package exceptions;

public class InvalidOptionException extends Exception{
	private static final long serialVersionUID = 1;
	
	public InvalidOptionException(String input) {
		super("The input option: " + input + "does not match any known option:");
	}
	
}
