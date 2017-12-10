package assignment;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class represents a object MacLocation.
 * @author Orel and Samuel.
 */
public class MacLocation implements Comparable<MacLocation> {

	private EarthCoordinate pointLocation;
	private int signal;

	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param signal.
	 */
	protected MacLocation(EarthCoordinate pointLocation, int signal) {
		this.pointLocation = pointLocation;
		this.signal = signal;
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
				this.getWeigthSignal() * pointLocation.getLatitude(),
				this.getWeigthSignal() * pointLocation.getLongitude(),
				this.getWeigthSignal() * pointLocation.getAltitude()
				);
	}
	
	/**
	 * @return signal.
	 */
	protected int getSignal() {
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
		return Integer.compare(this.signal, macLocationInformation.getSignal());
	}

	/**
	 * toString.
	 */
	public String toString() {
		return "Weight point location =" + getWeightPointLocation().toString() + ", weight signal=" + getWeigthSignal();
	}

}
