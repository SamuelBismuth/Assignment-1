package assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class define a scan.
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
	public Scan(GregorianCalendar time, String id, EarthCoordinate pointLocation, ArrayList<Wifi> arrayWifi) {
		this.time = time;
		this.id = id;
		this.pointLocation = pointLocation;
		this.arrayWifi = arrayWifi;
		Collections.sort(arrayWifi);
	}

	/**
	 * @return time.
	 */
	public GregorianCalendar getTime() {
		return time;
	}

	/**
	 * @return id.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return pointLocation.
	 */
	public EarthCoordinate getPointLocation() {
		return pointLocation;
	}
	
	/**
	 * @return arrayWifi.
	 */
	public ArrayList<Wifi> getArrayWifi() {
		return arrayWifi;
	}
	
	/**
	 * @return wifiNetworks.
	 */
	public int getWifiNetworks() {
		return arrayWifi.size();
	}
	
	public void sort() {
		Collections.sort(arrayWifi);
	}
	
	/**
	 * @return arrayStrongerWifi.
	 */
	public ArrayList<Wifi> getArrayStrongerWifi() {
		if (getWifiNetworks() <= 10) return arrayWifi;
		else {
			ArrayList<Wifi> arrayStrongerWifi = new ArrayList<Wifi>();
			for (int i = 0; i < 10; i++)  arrayStrongerWifi.add(arrayWifi.get(i));
			return arrayStrongerWifi;
		}
	}
	
	/**
	 * @param mac
	 * @return true if contains the same mac.
	 * @return false if not contains the same mac.
	 */
	public boolean containsSameMac(String mac) {
		for (Wifi wifi : arrayWifi) 
			if (wifi.getName().equals(mac)) return true;
		return false;
	}
}
