package library;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.ExtendedData;
import org.boehn.kmlframework.kml.IconStyle;
import org.boehn.kmlframework.kml.Placemark;
import org.boehn.kmlframework.kml.SimpleData;
import org.boehn.kmlframework.kml.Style;
import org.boehn.kmlframework.kml.TimeStamp;

import read.SampleScan;
import read.Wifi;

/**
 * This class contains static function which may help the construction of the kml file.
 * @author Orel and Samuel
 */
public class KmlUtil {

	/**
	 * This method constructs the placemark.
	 * @param scan.
	 */
	public static void addPlacemark(SampleScan scan, Document document) {
		for (Wifi wifi : scan.getArrayStrongerWifi()) {
			if (wifi.getName().contains("&")) wifi.setName(wifi.getName().replaceAll("&", "and"));
			Placemark placemark = new Placemark(wifi.getName());
			TimeStamp time = new TimeStamp(timeInput(scan.getTime()));
			placemark.setTimePrimitive(time);
			placemark.setExtendedData(extendedData(scan, wifi));
			placemark.setLocation(scan.getPointLocation().getLongitude(), scan.getPointLocation().getLatitude());
			placemark.setStyleUrl(color(wifi.getSignal()));
			document.addFeature(placemark);
		}
	}

	/**
	 * This method generate the data to input a new icon.
	 * @param color
	 */
	public static void addIcon(String color, Document document) {
		Style style = new Style();
		style.setId(color);
		IconStyle iconStyle = new IconStyle();
		iconStyle.setColor(color);
		iconStyle.setIconHref("http://maps.google.com/mapfiles/kml/paddle/" + color + "-stars.png");
		style.setIconStyle(iconStyle);
		document.addStyleSelector(style);
	}

	/**
	 * This method generate the data.
	 * @param scan.
	 * @param wifi.
	 * @return the extended data.
	 */
	public static ExtendedData extendedData(SampleScan scan, Wifi wifi) {
		ArrayList<SimpleData> array = new ArrayList<SimpleData>();
		array.add(simpleData("Mac", wifi.getMac()));
		array.add(simpleData("Frequency", Integer.toString(wifi.getFrequency())));
		array.add(simpleData("Date", scan.getTime().getTime().toString()));
		array.add(simpleData("Signal", Double.toString(wifi.getSignal())));
		array.add(simpleData("Id", scan.getId()));
		ExtendedData extendedData = new ExtendedData();
		extendedData.setSimpleDataElements(array);
		return extendedData;
	}

	/**
	 * @param name.
	 * @param value.
	 * @return simple data.
	 */
	public static SimpleData simpleData(String name, String value) {
		SimpleData data = new SimpleData();
		data.setName(name);
		data.setValue(value);
		return data;
	}

	/**
	 * if signal > - 70 green icon, if signal > -90 yellow icon, else red icon.
	 * @param signal.
	 * @return the color.
	 */
	public static String color(double signal) {
		if (signal > - 70) return "#red";
		else if (signal > -90) return "#ylw";
		else return "#grn";
	}

	/**
	 * @param time.
	 * @return yyyy-mm-ddThh:mm:ssZ.
	 */
	public static String timeInput(GregorianCalendar time) {
		return Integer.toString(time.get(Calendar.YEAR)) + "-" + dataChange((time.get(Calendar.MONTH))) + "-" + 
				dataChange((time.get(Calendar.DATE))) + "T" + dataChange((time.get(Calendar.HOUR_OF_DAY))) 
				+ ":" + dataChange((time.get(Calendar.MINUTE))) + ":" + dataChange((time.get(Calendar.SECOND))) + "Z";
	}

	/**
	 * @param data.
	 * @return data if the data is already with two digits.
	 * @return 0 + data if the data got only one digit.
	 */
	public static String dataChange(int data) {
		if (data / 10 >= 1) return Integer.toString(data);
		else return "0" + Integer.toString(data);
	}
	
	/**
	 * @param latitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	public static boolean checkLatitude(double latitude) {
		if (latitude < -90 || latitude > 90) return false;
		return true;

	}

	/**
	 * @param longitude.
	 * @return true if the data is good.
	 * @return if the data is not good.
	 */
	public static boolean checkLongitude(double longitude) {
		if (longitude < 0 || longitude > 360) return false;
		return true;
	}
	
}
