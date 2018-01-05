package objects;

import java.util.Comparator;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This interface defines only five methods : getCoordinates, getWeightCoordinates, getWeigthSignal, getSignal and getWifi.
 * Then, two classes implements this interface :
 * @see MacLocationAlgo1, and @see {@link MacInformationAlgo2}.
 * 
 * The main goal of this class is the defines the gps of the mac adresse.
 * 
 * @author Orel and Samuel.
 */
public abstract class MacInformation {
	
	private EarthCoordinate coordinates;
	private Wifi wifi;
	
	public MacInformation(EarthCoordinate coordinates, Wifi wifi) {
		this.coordinates = coordinates;
		this.wifi = wifi;
	}
	
	/**
	 * Abstract method, we define it in anothers classes.
	 * @return the weight signal.
	 */
	public abstract double getWeigthSignal();
	
	/**
	 * Abstract method, we define it in anothers classes.
	 * @return signal.
	 */
	public abstract double getSignal();

	/**
	 * @return coordinates.
	 */
	public EarthCoordinate getCoordinates() {
		return coordinates;
	}
	
	/**
	 * @return weightPointLocation = weightSignal * coordinates.
	 */
	public EarthCoordinate getWeightCoordinates() {
		return new EarthCoordinate(
				this.getWeigthSignal() * coordinates.getLatitude(),
				this.getWeigthSignal() * coordinates.getLongitude(),
				this.getWeigthSignal() * coordinates.getAltitude()
				);
	}
	
	/**
	 * @return wifi.
	 */
	public Wifi getWifi() {
		return wifi;
	}
	
	public static class Comparators {
	    public static final Comparator<MacInformation> SIGNAL = (MacInformation o1, MacInformation o2) -> Double.compare(o1.getSignal(), o2.getSignal());
	}

}
