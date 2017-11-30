package assignment1;

import java.util.GregorianCalendar;

/**
 * In this class there is not constructor, but only methods, that we need and use in some others classes.
 * @author Orel and Samuel.
 */
public class Verification {

	/**
	 * The method cheks if the altitude is between -418 (The dead sea : Israel) to 5100 (the Rinconada : Peru).
	 * @param altitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	protected boolean checkAltitude(double altitude) {
		if (altitude <= -418 || altitude >= 5100) return false; // -418 meters : the dead sea : Israel, 5100 meters : the Rinconada : Peru.
		return true;
	}

	/**
	 * @param latitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	protected boolean checkLatitude(double latitude) {
		if (latitude < -90 || latitude > 90) return false;
		return true;

	}

	/**
	 * @param longitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	protected boolean checkLongitude(double longitude) {
		if (longitude < 0 || longitude > 360) return false;
		return true;
	}

	/**
	 * @param mac.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	protected boolean checkMac(String mac) {
		if(mac.length() != 17) return false;
		return true;
	}

	/**
	 * The methpod checks if the name is empty.
	 * @param string.
	 * @return the name of the wifi.
	 */
	protected String noName(String wifi) {
		if (wifi.equals("")) return "No name";
		return wifi;
	}

	/**
	 * The method checks the first line, by asking if contains the header "WigleWifi".
	 * @param firstLine.
	 */
	protected boolean checkTheFile(String firstLine) {
		if (firstLine.contains("WigleWifi") && firstLine.contains("display")) return true;
		return false;
	}

	/**
	 * Set the string time of the csv into the object {@link GregorianCalendar}.
	 * Here the time appears like this : yyyy/mm/dd hh/mm/ss ex : 2017-10-27 16:13:51
	 * @param time.
	 * @return {@link GregorianCalendar}.
	 * @exception NumberFormatException | IndexOutOfBoundsException : Error in the input.
	 */
	protected GregorianCalendar stringToDate1(String time) throws InputException {
		try {
			int year = Integer.parseInt(time.substring(0, 4));
			int month = Integer.parseInt(time.substring(5, 7));
			int date = Integer.parseInt(time.substring(8, 10));
			int hours = Integer.parseInt(time.substring(11, 13));
			int min = Integer.parseInt(time.substring(14, 16));
			int sec = Integer.parseInt(time.substring(17, 19));
			if (checkTime(year, 1970, 2018) && checkTime(month, 0, 12) && checkTime(date, 0, 31) && checkTime(hours, 0, 24) && checkTime(min, 0, 60) && checkTime(sec, 0, 60))
				return new GregorianCalendar(year, month, date, hours, min, sec);
			else throw new InputException("There is ne such date in the calendar.");
		}
		catch (NumberFormatException | IndexOutOfBoundsException ex) {
			return stringToDate2(time);
		}
	}
	
	/**
	 * Set the string time of the csv into the object {@link GregorianCalendar}.
	 * here the time appears like this : dd/mm/yyyy hh/mm ex : 28/10/2017 20:10
	 * @param time.
	 * @return {@link GregorianCalendar}.
	 * @exception NumberFormatException | IndexOutOfBoundsException : Error in the input.
	 */
	protected GregorianCalendar stringToDate2(String time) throws InputException {
		try {
			int year = Integer.parseInt(time.substring(6, 10));
			int month = Integer.parseInt(time.substring(3, 5));
			int date = Integer.parseInt(time.substring(0, 2));
			int hours = Integer.parseInt(time.substring(11, 13));
			int min = Integer.parseInt(time.substring(14, 16));
			int sec = 00;
			if (checkTime(year, 1970, 2018) && checkTime(month, 0, 12) && checkTime(date, 0, 31) && checkTime(hours, 0, 24) && checkTime(min, 0, 60) && checkTime(sec, 0, 60))
				return new GregorianCalendar(year, month, date, hours, min, sec);
			else throw new InputException("There is ne such date in the calendar.");
		}
		catch (NumberFormatException | IndexOutOfBoundsException ex) {
			System.out.println("Error in the input. " + ex);
			return null;
		}
	}

	/**
	 * @param time.
	 * @param begining.
	 * @param end.
	 * @return true if the date's format correct.
	 * @return false if not.
	 */
	protected boolean checkTime(int time, int begining, int end) {
		if (time >= begining && time <= end) return true;
		return false;
	}
	
}
