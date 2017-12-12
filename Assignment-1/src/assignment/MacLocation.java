package assignment;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class represents a object MacLocation.
 * @author Orel and Samuel.
 */
public class MacLocation implements Comparable<MacLocation> {

	private EarthCoordinate pointLocation;
	private double signal;

	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param signal.
	 */
	public MacLocation(EarthCoordinate pointLocation, double d) {
		this.pointLocation = pointLocation;
		this.signal = d;
	}

	/**
	 * @return pointLocation.
	 */
	public EarthCoordinate getPointLocation() {
		return pointLocation;
	}

	/**
	 * @return weightPointLocation = weightSignal * pointLocation.
	 */
	public EarthCoordinate getWeightPointLocation() {
		return new EarthCoordinate(
				this.getWeigthSignal() * pointLocation.getLongitude(),
				this.getWeigthSignal() * pointLocation.getLatitude(),
				this.getWeigthSignal() * pointLocation.getAltitude()
				);
	}
	
	/**
	 * @return signal.
	 */
	public double getSignal() {
		return signal;
	}

	/**
	 * @return weightSignal = (1 / signal * signal).
	 */
	public double getWeigthSignal() {
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
