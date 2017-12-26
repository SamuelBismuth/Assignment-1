package userChoice;

import java.util.Scanner;

import filter.Filtering;
import filter.FilteringKmlId;
import libraries.DataBase;
import libraries.InputException;
import read.SampleScan;

/**
 * This class implements @see {@link UserChoice}.
 * This class ask to choose an id.
 * @author Orel and Samuel.
 */
public class UserChoiceKmlId implements UserChoice {

	/**
	 * This method ask the user an id.
	 * @exception InputException : Couldn't find the id.
	 */
	@SuppressWarnings("resource")
	@Override
	public Filtering<SampleScan> choice() throws InputException {
		System.out.println("Input an Id please :");
		String id = new Scanner(System.in).nextLine();
		if (DataBase.containsTheId(id)) 
			return new FilteringKmlId(id);
		else throw new InputException("Couldn't find the id :" + id);	
	}

}
