package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class define an object scan.
 * @author Orel and Samuel.
 */
public class Scan implements Comparable<Scan> {
	
	private GregorianCalendar time;
	private String id;
	private EarthCoordinate pointLocation;
	private ArrayList<Wifi> arrayWifi;
	private double relevantNumber;
	
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
		sort();
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
	
	public void sort() {
		Collections.sort(arrayWifi);
	}
	
	/**
	 * @return arrayStrongerWifi.
	 */
	public ArrayList<Wifi> getArrayStrongerWifi() {
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
	public int getWifiNetworks() {
		return getArrayStrongerWifi().size();
	}
	
	/**
	 * @param input
	 */
	public void setRelevantNumber(Scan input) {
		this.relevantNumber = RelevantScan.getRelevantNumber(this, input);
	}
	
	/**
	 * @return relevanrNumber.
	 */
	public double getRelevantNumber () {
		return relevantNumber;
	}
	/**
	 * @param mac
	 * @return true if contains the same mac.
	 * @return false if not contains the same mac.
	 */
	public boolean containsSameMac(String mac) {
		for (Wifi wifi : arrayWifi) 
			if (wifi.getMac().equals(mac))
				return true;
		return false;
	}

	public ArrayList<Wifi> getArrayStrongerWifiConstantNumber(int numberConstant) {
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		for (int i = 0; i < numberConstant; i++) 
			array.add(arrayWifi.get(i));
		return array;
	}

	/**
	 * Compare the relevant number.
	 */
	public int compareTo(Scan scan) {
		return Double.compare(this.relevantNumber, scan.getRelevantNumber());
	}
}
