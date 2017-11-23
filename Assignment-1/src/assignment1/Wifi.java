package assignment1;

import java.util.GregorianCalendar;
import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class defines a wifi network.
 * @author Orel and Samuel.
 */

public class Wifi {
	
	private GregorianCalendar time;
	private String id;
	private EarthCoordinate pointLocation;
	private String name;
	private String mac;
	private int frequency;
	private int signal;
	
	/**
	 * Constructor
	 * @param time
	 * @param id
	 * @param pointLocation
	 * @param name
	 * @param mac
	 * @param frequency
	 * @param signal
	 */
	public Wifi(GregorianCalendar time, String id, EarthCoordinate pointLocation, String name, String mac, 
			int frequency, int signal) {
		this.time = time;
		this.id = id;
		this.pointLocation = pointLocation;
		this.name = name;
		this.mac = mac;
		this.frequency = frequency;
		this.signal = signal;
	}
	
	/**
	 * @return time
	 */
	protected GregorianCalendar getTime() {
		return time;
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return pointLocation
	 */
	protected EarthCoordinate getPointLocation() {
		return pointLocation;
	}

	/**
	 * @return name
	 */
		protected String getName() {
		return name;
	}
	
	/**
	 * @return mac
	 */
	protected String getMac() {
		return mac;
	}
	
	/**
	 * @return frequence
	 */
	protected int getFrequency() {
		return frequency;
	}

	/**
	 * @return signal
	 */
	public int getSignal() {
		return signal;
	}

	/**
	 * @param name
	 */
	protected void setName(String name) {
		this.name = name;
	}
	
}
