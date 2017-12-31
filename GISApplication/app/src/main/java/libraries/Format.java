package libraries;

import org.apache.commons.csv.CSVRecord;

import read.HeaderCombo;

public class Format {

	/**
	 * The method translate the channel to frequency.
	 * @param channel.
	 * @return String frequency.
	 */
	public static int channelToFrequency(int channel) {
		if (channel >= 1 && channel <= 14) return 2400;
		else return 5000;
	}

	/**
	 * This method check if the localisation is known.
	 * @param str.
	 * @return str or "0".
	 */
	public static String containsInterogation(String str) {
		if (str.equals("?")) return "0";
		return str;
	}

	/**
	 * This function check the latitude.
	 * @param latitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	public static boolean checkLatitude(double latitude) {//done
		if (latitude < -90 || latitude > 90) return false;
		return true;

	}

	/**
	 * This function check the longitude.
	 * @param longitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	public static boolean checkLongitude(double longitude) {//done
		if (longitude < 0 || longitude > 360) return false;
		return true;
	}
	
	/**
	 * This method check if the cordinate are known.
	 * @param coordinate.
	 * @return coordinate.
	 */
	public static Double checkCoordinate(String coordinate) {
		if (coordinate.equals("?") || coordinate.equals("null")) return 0.0;
		return Double.parseDouble(coordinate);
	}

	/**
	 * This method check if the line is good.
	 * @param record.
	 * @return true if the line is good.
	 * @return false if not.
	 */
	public static boolean goodLine(CSVRecord record) {
		if (!record.get(0).equals("") && !record.get(HeaderCombo.wifiNetworks).contains("#Wifi networks")) return true;
		return false;
	}
	
	/**
	 * The method checks the first line, by asking if contains the header "WigleWifi".
	 * @param firstLine.
	 * @return true if the file is a WigleWifi file.
	 * @return false if it's not.
	 */
	public static boolean checkTheFile(String firstLine) {
		if (firstLine.contains("WigleWifi") && firstLine.contains("display")) return true;
		return false;
	}

	public static boolean noGps(String record) {
		if (record.contains("?"))
			return true;
		return false;
	}
}