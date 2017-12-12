package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class define an object scan.
 * @author Orel and Samuel.
 */
public class Scan {
	
	private GregorianCalendar time;
	private String id;
	private EarthCoordinate pointLocation;
	private ArrayList<Wifi> arrayWifi;
	
	/**
	 * Constructor.
	 * @param time.
	 * @param id.
	 * @param pointLocation.
	 * @param wifi.
	 */
	protected Scan(GregorianCalendar time, String id, EarthCoordinate pointLocation, ArrayList<Wifi> arrayWifi) {
		this.time = time;
		this.id = id;
		this.pointLocation = pointLocation;
		this.arrayWifi = arrayWifi;
		sort();
	}

	/**
	 * @return time.
	 */
	protected GregorianCalendar getTime() {
		return time;
	}

	/**
	 * @return id.
	 */
	protected String getId() {
		return id;
	}
	
	/**
	 * @return pointLocation.
	 */
	protected EarthCoordinate getPointLocation() {
		return pointLocation;
	}
	
	/**
	 * @return arrayWifi.
	 */
	protected ArrayList<Wifi> getArrayWifi() {
		return arrayWifi;
	}
	
	protected void sort() {
		Collections.sort(arrayWifi);
	}
	
	/**
	 * @return arrayStrongerWifi.
	 */
	protected ArrayList<Wifi> getArrayStrongerWifi() {
		if (arrayWifi.size() <= 10) return arrayWifi;
		else {
			ArrayList<Wifi> arrayStrongerWifi = new ArrayList<Wifi>();
			for (int i = 0; i < 10; i++)  arrayStrongerWifi.add(arrayWifi.get(i));
			return arrayStrongerWifi;
		}
	}
	
	/**
	 * @return wifiNetworks.
	 */
	protected int getWifiNetworks() {
		return getArrayStrongerWifi().size();
	}
	
	/**
	 * @param mac
	 * @return true if contains the same mac.
	 * @return false if not contains the same mac.
	 */
	protected boolean containsSameMac(String mac) {
		for (Wifi wifi : arrayWifi) 
			if (wifi.getName().equals(mac))
				return true;
		return false;
	}
}
