package objects;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class implements @see {@link MacInformation}.
 * This class represents a object {@link MacInformation} for the data of the algorithm 1.
 * @author Orel and Samuel.
 */
public class MacInformationAlgo1 extends MacInformation {

	private double signal;

	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param wifi.
	 * @param signal.
	 */
	public MacInformationAlgo1(EarthCoordinate coordinates, Wifi wifi,  double signal) {
		super(coordinates, wifi);
		this.signal = signal;
	}
	
	//Getters.
	
	/**
	 * @return weightSignal = (1 / signal * signal).
	 */
	@Override
	public double getWeigthSignal() {
			return (1 / Math.pow(this.signal, 2));
	}

	/**
	 * @return signal.
	 */
	@Override
	public double getSignal() {
		return signal;
	}
		
}
