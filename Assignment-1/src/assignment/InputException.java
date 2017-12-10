package assignment;

/**
 * This class print the exception message. 
 * This class extends @see {@link Exception}.
 * @author Orel and Samuel.
 */
@SuppressWarnings("serial")
public class InputException extends Exception {

	/**
	 * Constructor.
	 * @param message.
	 */
	public InputException(String message) {
        super(message);
    }
	
}
