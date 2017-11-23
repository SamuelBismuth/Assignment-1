package assignment1;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This class filtering the data by the time : only the wifi on the period of time that the user choosed will appear in the kml place.
 * This class implement @see {@link Filtering}, extends @see {@link Verification} to the use of some methods.
 * @author Orel and Samuel.
 */
public class FilteringTime extends Verification implements Filtering {

	/**
	 * Empty constructor.
	 */
	public FilteringTime(){}

	/**
	 * Constructor.
	 * @param array.
	 * @exception {@link InputException}  : Wrong input on the time.
	 */
	protected FilteringTime(ArrayList<Wifi> array) {
		try {
			filteringBy(array);
		} 
		catch (InputException ex) {
			System.out.println(ex);
			new FilteringTime(array);
		}
	}

	/**
	 * This method ask the user to input the time.
	 * @param array.
	 */
	@SuppressWarnings("resource")
	public void filteringBy(ArrayList<Wifi> array) throws InputException{
		System.out.println("Input the beginning of the period of time please : yyyy-mm-dd hh:mm:ss :");
		String time_begining = new Scanner(System.in).nextLine();
		System.out.println("Input the end of the period of time please : yyyy-mm-dd hh:mm:ss :");
		String time_end = new Scanner(System.in).nextLine();
		if (time_begining.length() == 19 && time_end.length() == 19) {
			GregorianCalendar dateBegining = stringToDate(time_begining);
			GregorianCalendar dateEnd = stringToDate(time_end);
			if (dateBegining.before(dateEnd)) {
				new WriteKmlTime(array, dateBegining, dateEnd);
			}
			else throw new InputException("Wrong input on the time, the begining time is after the end time.");
		}
		else throw new InputException("Wrong input on the time.");
	}

}
