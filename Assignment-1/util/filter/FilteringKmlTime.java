package filter;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.boehn.kmlframework.kml.Document;

import library.ParseDate;
import read.SampleScan;
import library.InputException;
import write.WriteFile;
import write.WriteKmlTime;

/**
 * This class extends @see {@link FilteringKml} and implements @see {@link Filtering}.
 * This class filtering the data by the time : only the scan on the period of time that the user choosed will appear in the kml place.
 * @author Orel and Samuel.
 */
public class FilteringKmlTime extends FilteringKml implements Filtering<SampleScan, SampleScan> {

	/**
	 * This method ask the user to input the time.
	 * @param array.
	 * @return {@link WriteKmlTime}.
	 */
	@SuppressWarnings("resource")
	@Override
	public WriteFile<SampleScan> filteringBy(ArrayList<SampleScan> array) throws InputException {
		System.out.println("Input the beginning of the period of time please : yyyy-mm-dd hh:mm:ss :");
		String time_begining = new Scanner(System.in).nextLine();
		System.out.println("Input the end of the period of time please : yyyy-mm-dd hh:mm:ss :");
		String time_end = new Scanner(System.in).nextLine();
		if (time_begining.length() == 19 && time_end.length() == 19) {
			GregorianCalendar dateBegining = ParseDate.stringToDate(time_begining);
			GregorianCalendar dateEnd = ParseDate.stringToDate(time_end);
			if (dateBegining.before(dateEnd)) {
				String fileName = getFileName();
				Document document = new Document();
				return new WriteKmlTime(fileName, document, dateBegining, dateEnd);
			}
			else throw new InputException("Wrong input on the time, the begining time is after the end time.");
		}
		else throw new InputException("Wrong input on the time.");
	}

}
