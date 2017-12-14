package algorithm1;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import read.Wifi;

/**
 * This class represents a object MacLocation.
 * @author Orel and Samuel.
 */
public class MacLocation implements Comparable<MacLocation> {

	private EarthCoordinate pointLocation;
	private double signal;
	private Wifi wifi;

	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param signal.
	 */
	public MacLocation(EarthCoordinate pointLocation, double signal, Wifi wifi) {
		this.pointLocation = pointLocation;
		this.signal = signal;
		this.wifi = wifi;
	}

	/**
	 * @return pointLocation.
	 */
	protected EarthCoordinate getPointLocation() {
		return pointLocation;
	}

	/**
	 * @return weightPointLocation = weightSignal * pointLocation.
	 */
	protected EarthCoordinate getWeightPointLocation() {
		return new EarthCoordinate(
				this.getWeigthSignal() * pointLocation.getLongitude(),
				this.getWeigthSignal() * pointLocation.getLatitude(),
				this.getWeigthSignal() * pointLocation.getAltitude()
				);
	}
	
	/**
	 * @return wifi.
	 */
	public Wifi getWifi() {
		return wifi;
	}
	
	/**
	 * @return signal.
	 */
	protected double getSignal() {
		return signal;
	}

	/**
	 * @return weightSignal = (1 / signal * signal).
	 */
	protected double getWeigthSignal() {
		return (1 / Math.pow(this.signal, 2));
	}

	/**
	 * This method compare the signal.
	 * @param macLocationInformation.
	 */
	public int compareTo(MacLocation macLocationInformation) {
		return Double.compare(macLocationInformation.getSignal(), this.signal);
	}

	/**
	 * toString.
	 */
	public String toString() {
		return "Signal =" + getSignal() + ", Weight signal=" + getWeigthSignal() +
			   "Point location =" + getPointLocation().toString() + "Weight point location =" + getWeightPointLocation().toString();
	}

}
