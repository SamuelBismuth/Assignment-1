package algorithms;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import read.Wifi;

/**
 * This class implements @see {@link MacLocation}.
 * This class represents a object MacLocation for the data of the algorithm 1.
 * @author Orel and Samuel.
 */
public class MacLocationAlgo1 implements MacLocation {

	private EarthCoordinate pointLocation;
	private double signal;
	private Wifi wifi;

	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param signal.
	 * @param wifi.
	 */
	public MacLocationAlgo1(EarthCoordinate pointLocation, double signal, Wifi wifi) {
		this.pointLocation = pointLocation;
		this.signal = signal;
		this.wifi = wifi;
	}
	
	//Getters.

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
	 * @return wifi.
	 */
	@Override
	public Wifi getWifi() {
		return wifi;
	}
	
	/**
	 * @return signal.
	 */
	@Override
	public double getSignal() {
		return signal;
	}

	/**
	 * @return weightSignal = (1 / signal * signal).
	 */
	@Override
	public double getWeigthSignal() {
			return (1 / Math.pow(this.signal, 2));
	}
		
}
