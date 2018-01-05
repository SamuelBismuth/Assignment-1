package libraries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * This class convert a String with a data to a object {@link GregorianCalendar}.
 * @author Orel and Samuel.
 */
public class ParseDate {

	/**
	 * Set the string time of the csv into the object {@link GregorianCalendar}.
	 * Here the time appears like this : yyyy/mm/dd hh/mm/ss ex : 2017-10-27 16:13:51.
	 * @param time.
	 * @return {@link GregorianCalendar}.
	 * @exception NumberFormatException | IndexOutOfBoundsException : Error in the input.
	 */
	static public GregorianCalendar stringToDate(String dateString) throws InputException {
		try {
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(parser.parse(dateString));
			return calendar;
		}
		catch (ParseException ex) {
			return stringToDateSephie(dateString);
		}
	}

	/**
	 * Set the string time of the csv into the object {@link GregorianCalendar}.
	 * here the time appears like this : dd/mm/yyyy hh/mm ex : 28/10/2017 20:10.
	 * @param time.
	 * @return {@link GregorianCalendar}.
	 * @exception NumberFormatException | IndexOutOfBoundsException : Error in the input.
	 */
	static public GregorianCalendar stringToDateSephie(String dateString) throws InputException {
		try {
			SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(parser.parse(dateString));
			return calendar;
		}
		catch (ParseException ex) {
			return stringToDateBoaz(dateString);
		}
	}

	/**
	 * Set the string time of the csv into the object {@link GregorianCalendar}.
	 * here the time appears like this : dd/mm/yy hh/mm AM ex : 12/05/17 11:48 AM.
	 * @param time.
	 * @return {@link GregorianCalendar}.
	 * @exception NumberFormatException | IndexOutOfBoundsException : Error in the input.
	 */
	static public GregorianCalendar stringToDateBoaz(String dateString) throws InputException {
		try {
			SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yy HH:mm aa", Locale.US);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(parser.parse(dateString));
			return calendar;
		}
		catch (ParseException e) {
			return stringToDateDataBase(dateString);
		}
	}	

	/**
	 * Set the string time of the csv into the object {@link GregorianCalendar}.
	 * here the time appears like this : DDD MMM dd hh:mm:ss IST yyyy ex : Fri Dec 01 15:37:01 IST 2017.
	 * @param time.
	 * @return {@link GregorianCalendar}.
	 * @exception NumberFormatException | IndexOutOfBoundsException : Error in the input.
	 */
	static public GregorianCalendar stringToDateDataBase(String dateString) throws InputException {
		try {
			SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(parser.parse(dateString));
			return calendar;
		}
		catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
