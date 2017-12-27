package userChoice;

import java.util.GregorianCalendar;
import java.util.Scanner;

import filter.Filtering;
import filter.FilteringKmlTime;
import libraries.InputException;
import libraries.ParseDate;
import objects.SampleScan;

/**
 * This class implements @see {@link UserChoice}.
 * This class ask to choose a time.
 * @author Orel and Samuel.
 */
public class UserChoiceKmlTime implements UserChoice {

	/**
	 * This method ask the user a place.
	 * @exception InputException : There is no such date.
	 */
	@SuppressWarnings("resource")
	@Override
	public Filtering<SampleScan> choice() throws InputException {
		System.out.println("Input the beginning of the period of time please : yyyy-mm-dd hh:mm:ss :");
		String time_begining = new Scanner(System.in).nextLine();
		System.out.println("Input the end of the period of time please : yyyy-mm-dd hh:mm:ss :");
		String time_end = new Scanner(System.in).nextLine();
		if (time_begining.length() != 19 || time_end.length() != 19) 
			throw new InputException("There is no such date.");
		GregorianCalendar dateBeginning = ParseDate.stringToDate(time_begining);
		GregorianCalendar dateEnd = ParseDate.stringToDate(time_end);
		return new FilteringKmlTime(dateBeginning, dateEnd);
		}
}
