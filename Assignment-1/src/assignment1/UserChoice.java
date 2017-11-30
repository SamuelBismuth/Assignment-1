package assignment1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class ask the user how he wants to organize the kml file.
 * By the time, the place or the id...
 * @author Orel and Samuel.
 */
public class UserChoice {

	/**
	 * This methode use a @see {@link Scanner} to recuperate the input of the user.
	 * You need to put 1 if you want to filtering the csv by the time, 2 by the Id, 3 by the place.
	 * Otherwise, all the csv file will be translate into a kml file.
	 * @exception InputMismatchException : Error on the input.
	 */
	@SuppressWarnings("resource")
	protected void userChoice(ArrayList<Wifi> array) {
		try {
			System.out.println("How to filtering ? input 1, 2, 3. \n"
					+ "1 : time, 2 : Id, 3 : place, otherwise no filter.");
			int choice = new Scanner(System.in).nextInt();
			switch(choice) {
			case 1 : 
				new FilteringTime(array);
				break;
			case 2 : 
				new FilteringId(array);
				break;
			case 3 : 
				new FilteringPlace(array);
				break;
			default : 
				new WriteKmlWithoutFilter(array);
				break;
			}
		}
		catch (InputMismatchException ex) {
			System.out.println("Error on the input. " + ex);
			userChoice(array);
		}
	}

}
