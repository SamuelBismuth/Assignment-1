package libraries;

import java.util.InputMismatchException;
import java.util.Scanner;

import filter.Filtering;
import filter.FilteringKmlEmpty;
import filter.FilteringKmlId;
import filter.FilteringKmlPlace;
import filter.FilteringKmlTime;
import read.SampleScan;

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
	public static Filtering<SampleScan> userChoice() {
		try {
			System.out.println("How to filtering ? input 1, 2, 3. \n"
					+ "1 : time, 2 : Id, 3 : place, otherwise no filter.");
			int choice = new Scanner(System.in).nextInt();
			switch (choice) {
			case 1 : 
				return new FilteringKmlTime();
			case 2 : 
				return new FilteringKmlId();
			case 3 : 
				return new FilteringKmlPlace();
			default :
				return new FilteringKmlEmpty();
			}
		}
		catch (InputMismatchException ex) {
			System.out.println("Error on the input. " + ex);
			return userChoice();
		}
	}
	
	/**
	 * This method make the user choice a file name for the file.
	 * @param file.
	 * @return the fileName choosed.
	 */
	@SuppressWarnings("resource")
	public static String getFileName(String file) {
		System.out.println("Input a name for the " + file + " file you want to create : ");
		return new Scanner(System.in).nextLine();
	}

}
