package assignment1;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

public class MacLocation implements Comparable<MacLocation> {

	private EarthCoordinate pointLocation;
	private int signal;

	/**
	 * Constructor.
	 * @param pointLocation
	 * @param signal
	 */
	public MacLocation(EarthCoordinate pointLocation, int signal) {
		this.pointLocation = pointLocation;
		this.signal = signal;
	}

	/**
	 * @return pointLocation.
	 */
	public EarthCoordinate getPointLocation() {
		return pointLocation;
	}

	/**
	 * @return signal.
	 */
	public int getSignal() {
		return signal;
	}

	/**
	 * @return weightSignal = (1 / signal * signal).
	 */
	public double getWeigthSignal() {
		return (1 / Math.pow(this.signal, 2));
	}

	/**
	 * @return weightPointLocation = weightSignal * pointLocation.
	 */
	public EarthCoordinate getWeightPointLocation() {
		return new EarthCoordinate(
				this.getWeigthSignal() * pointLocation.getLatitude(),
				this.getWeigthSignal() * pointLocation.getLongitude(),
				this.getWeigthSignal() * pointLocation.getAltitude()
				);
	}

	/**
	 * This method compare the signal.
	 * @param macLocationInformation.
	 */
	public int compareTo(MacLocation macLocationInformation) {
		return Integer.compare(this.signal, macLocationInformation.getSignal());
	}

}
