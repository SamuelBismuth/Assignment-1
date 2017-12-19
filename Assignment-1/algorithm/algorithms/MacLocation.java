package algorithms;

import java.util.Comparator;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import read.Wifi;

/**
 * This interface defines only five methods : getPointLocation, getWeightPointLocation, getWeigthSignal, getSignal and getWifi.
 * Then, two classes implements this interface :
 * @see MacLocationAlgo1, and @see {@link MacLocationAlgo2}.
 * 
 * The main goal of this class is the defines the gps of the mac adresse.
 * 
 * @author Orel and Samuel.
 */
public interface MacLocation {
	
	public EarthCoordinate getPointLocation();
	public EarthCoordinate getWeightPointLocation();
	public double getWeigthSignal();
	public double getSignal();
	public Wifi getWifi();
	
	public static class Comparators {
	    public static final Comparator<MacLocation> SIGNAL = (MacLocation o1, MacLocation o2) -> Double.compare(o1.getSignal(), o2.getSignal());
	}


}
