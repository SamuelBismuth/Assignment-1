package algorithms;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import read.Wifi;

public class MacLocationAlgo2 implements MacLocation {
	
	private EarthCoordinate pointLocation;
	private double pi;
	
	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param signal.
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
				this.getWeigthSignal() * pointLocation.getLongitude(),
				this.getWeigthSignal() * pointLocation.getLatitude(),
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
	
	@Override
	public Wifi getWifi() {
		return null;
	}
}
