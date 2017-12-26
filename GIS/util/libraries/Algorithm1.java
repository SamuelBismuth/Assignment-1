package libraries;

import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import objects.MacLocation;

/**
 * This class includes all the static function which calculate all the data by the algorithm 1 formulas.
 * Here all the function are static.
 * The explnation we could give (as students) is the next one :
 * Here all the methods are used to be a mathematical reflexion. There is no object into those functions.
 * For exemple f(x) = 2x + 4 changes the x into 2x + 4, here the methods do the same.
 * That's why we choosed the do a large class which play the role of library for all the formulas of the algorithm 1.
 * @author Orel and Samuel
 *
 */
public class Algorithm1 {
	
	/**
	 * This method return the weight center.
	 * @return weightCenter.
	 */
	public static EarthCoordinate getWeightCenter(ArrayList<MacLocation> arrayMacLocation) {
		return new EarthCoordinate(
				getSumWeightPointLocation(arrayMacLocation).getLatitude() / getSumWeightSignal(arrayMacLocation),
				getSumWeightPointLocation(arrayMacLocation).getLongitude() / getSumWeightSignal(arrayMacLocation),
				getSumWeightPointLocation(arrayMacLocation).getAltitude() / getSumWeightSignal(arrayMacLocation)
				);
	}
	
	/**
	 * This method calculates the sum between all pointLocation of the weights.
	 * @return SumWeightPointLocation.
	 */
	private static EarthCoordinate getSumWeightPointLocation(ArrayList<MacLocation> arrayMacLocation) {
		double sumWeigthLatitude = 0;
		double sumWeigthLongitude = 0;
		double sumWeigthAltitude = 0;
		for(MacLocation macLocation : arrayMacLocation) {
			sumWeigthLatitude += macLocation.getWeightPointLocation().getLatitude();
			sumWeigthLongitude += macLocation.getWeightPointLocation().getLongitude();
			sumWeigthAltitude += macLocation.getWeightPointLocation().getAltitude();
		}
		return new EarthCoordinate(
				sumWeigthLatitude,
				sumWeigthLongitude,
				sumWeigthAltitude
				);
	}
	
	/**
	 * This method calculates the sum between all the signal of the weights.
	 * @return sumWeigthSignal.
	 */
	public static double getSumWeightSignal(ArrayList<MacLocation> arrayMacLocation) {
		double sumWeigthSignal = 0;
		for(MacLocation macLocation : arrayMacLocation) sumWeigthSignal += macLocation.getWeigthSignal();
		return sumWeigthSignal;
	}
	
}
