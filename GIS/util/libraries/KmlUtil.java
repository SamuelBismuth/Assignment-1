package libraries;

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

import objects.Wifi;
import read.SampleScan;

/**
 * This class contains statics functions which may help the construction of the kml file.
 * Here all the function are static.
 * The explnation we could give (as students) is the next one :
 * The creation of the kml file should not depends of object.
 * Then, it's getting closer of methematical function like f(x) = 2x + 4 like we saw in {@link Algorithm2}.
 * Here the difference is that the x should be a string, and so the method does not return a number but a string.
 * (return or side effect in the document for exemple).
 * @author Orel and Samuel
 */
public class KmlUtil {

	/**
	 * This method constructs the placemark.
	 * @param scan.
	 * @param document.
	 */
	public static void addPlacemark(SampleScan scan, Document document) {
		for (Wifi wifi : scan.getArrayStrongerWifi()) {
			if (wifi.getName().contains("&")) wifi.setName(wifi.getName().replaceAll("&", "and"));
			Placemark placemark = new Placemark(wifi.getName());
			TimeStamp time = new TimeStamp(timeInput(scan.getTime()));
			placemark.setTimePrimitive(time);
			placemark.setExtendedData(extendedData(scan, wifi));
			placemark.setLocation(scan.getPointLocation().getLatitude(), scan.getPointLocation().getLongitude());
			placemark.setStyleUrl(color(wifi.getSignal()));
			document.addFeature(placemark);
		}
	}

	/**
	 * This method generates the data to input a new icon.
	 * @param color.
	 * @param document.
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
	 * This method generate the extended data. (it's display like a table in the kml file).
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
	 * This method constructs the simple data.
	 * @param name.
	 * @param value.
	 * @return simple data.
	 */
	public static SimpleData simpleData(String name, String value) {//can't check
		SimpleData data = new SimpleData();
		data.setName(name);
		data.setValue(value);
		return data;
	}

	/**
	 * The method is defines which color for which signal.
	 * if signal > - 70 red icon, if signal > -90 yellow icon, else green icon.
	 * @param signal.
	 * @return the color.
	 */
	public static String color(double signal) {//done
		if (signal > - 70) return "#grn";
		else if (signal > -90) return "#ylw";
		else return "#red";
	}

	/**
	 * This method return the time like asked by google map to dipslay the timeline.
	 * @param time.
	 * @return yyyy-mm-ddThh:mm:ssZ.
	 */
	public static String timeInput(GregorianCalendar time) {//done
		return Integer.toString(time.get(Calendar.YEAR)) + "-" + dataChange((time.get(Calendar.MONTH))) + "-" + 
				dataChange((time.get(Calendar.DATE))) + "T" + dataChange((time.get(Calendar.HOUR_OF_DAY))) 
				+ ":" + dataChange((time.get(Calendar.MINUTE))) + ":" + dataChange((time.get(Calendar.SECOND))) + "Z";
	}

	/**
	 * This function make 1 min to 01 min, like google map ask for.
	 * @param data.
	 * @return data if the data is already with two digits.
	 * @return 0 + data if the data got only one digit.
	 */
	public static String dataChange(int data) {//done
		if (data / 10 >= 1) return Integer.toString(data);
		else return "0" + Integer.toString(data);
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
	
}
