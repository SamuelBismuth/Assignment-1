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
	public MacLocation(EarthCoordinate pointLocation, int signal) {
		this.pointLocation = pointLocation;
		this.signal = signal;
	}

	/**
	 * @return pointLocation.
	 */
	public EarthCoordinate getPointLocation() {//p
		return pointLocation;
	}

	/**
	 * @return weightPointLocation = weightSignal * pointLocation.
	 */
	public EarthCoordinate getWeightPointLocation() {//p
		return new EarthCoordinate(
				this.getWeigthSignal() * pointLocation.getLongitude(),
				this.getWeigthSignal() * pointLocation.getLatitude(),
				this.getWeigthSignal() * pointLocation.getAltitude()
				);
	}
	
	/**
	 * @return signal.
	 */
	public int getSignal() {//p
		return signal;
	}

	/**
	 * @return weightSignal = (1 / signal * signal).
	 */
	public double getWeigthSignal() {//p
		return (1 / Math.pow(this.signal, 2));
	}

	/**
	 * This method compare the signal.
	 * @param macLocationInformation.
	 */
	public int compareTo(MacLocation macLocationInformation) {//p
		return Integer.compare(this.signal, macLocationInformation.getSignal());
	}

	/**
	 * toString.
	 */
	public String toString() {
		return "Signal =" + getSignal() + ", Weight signal=" + getWeigthSignal() +
			   "Point location =" + getPointLocation().toString() + "Weight point location =" + getWeightPointLocation().toString();
	}

}
