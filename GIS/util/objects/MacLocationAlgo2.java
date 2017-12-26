package objects;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class implements @see {@link MacLocation}.
 * This class represents a object MacLocation for the data of the algorithm 2.
 * @author Orel and Samuel.
 */
public class MacLocationAlgo2 implements MacLocation {
	
	private EarthCoordinate pointLocation;
	private double pi;
	
	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param pi.
	 */
	public MacLocationAlgo2(EarthCoordinate pointLocation, double pi) {
		this.pointLocation = pointLocation;
		this.pi = pi;
	}
	
	/**
	 * @return pointLocation.
	 */
	@Override
	public EarthCoordinate getPointLocation() {
		return pointLocation;
	}

	/**
	 * @return weightPointLocation = weightSignal * pointLocation.
	 */
	@Override
	public EarthCoordinate getWeightPointLocation() {
		return new EarthCoordinate(
				this.getWeigthSignal() * pointLocation.getLatitude(),
				this.getWeigthSignal() * pointLocation.getLongitude(),
				this.getWeigthSignal() * pointLocation.getAltitude()
				);
	}
	
	/**
	 * @return pi.
	 */
	@Override
	public double getWeigthSignal() {
			return pi;
	}
	
	/**
	 * @return signal.
	 */
	@Override
	public double getSignal() {
		if (pi == 0) return -120;
		return Math.sqrt(1/pi);
	}
	
	/**
	 * @return null.
	 */
	@Override
	public Wifi getWifi() {
		return null;
	}
	
}
