package assignment1;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This class filtering the data by the time : only the scan on the period of time that the user choosed will appear in the kml place.
 * This class implement @see {@link FilteringKml}, extends @see {@link Verification} to the use of some methods.
 * @author Orel and Samuel.
 */
public class FilteringKmlTime extends FilteringKml implements Filtering {

	/**
	 * This method ask the user to input the time.
	 * @param array.
	 * @return {@link WriteKmlTime}.
	 */
	@SuppressWarnings("resource")
	public WriteFile filteringBy(ArrayList<?> arrayObject) throws InputException {
		ArrayList<Scan> array = (ArrayList<Scan>) arrayObject;
		System.out.println("Input the beginning of the period of time please : yyyy-mm-dd hh:mm:ss :");
		String time_begining = new Scanner(System.in).nextLine();
		System.out.println("Input the end of the period of time please : yyyy-mm-dd hh:mm:ss :");
		String time_end = new Scanner(System.in).nextLine();
		if (time_begining.length() == 19 && time_end.length() == 19) {
			GregorianCalendar dateBegining = stringToDate1(time_begining);
			GregorianCalendar dateEnd = stringToDate1(time_end);
			if (dateBegining.before(dateEnd)) {
				return new WriteKmlTime(dateBegining, dateEnd);
			}
			else throw new InputException("Wrong input on the time, the begining time is after the end time.");
		}
		else throw new InputException("Wrong input on the time.");
	}

}
