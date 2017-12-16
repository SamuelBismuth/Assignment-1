package algorithms;

import java.util.Comparator;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import read.Wifi;

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
