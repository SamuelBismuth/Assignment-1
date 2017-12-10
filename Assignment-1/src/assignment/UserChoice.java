package assignment;

/**
 * This interface defines only one method : userChoice.
 * This interface is implemented by {@link UserChoiceKml}, {@link UserChoiceMac} and {@link UserChoicePlace}.
 * @author Orel and Samuel.
 */
public interface UserChoice {

	public Filtering userChoice() throws InputException;
	
}

