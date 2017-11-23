package assignment1;

import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class write the kml file with the place filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel
 */
public class WriteKmlPlace extends WriteKml implements WriteFile {

	private EarthCoordinate pointLocation;
	private double radius;
	
	/**
	 * Test constructor.
	 * @param pointLocation
	 * @param radius
	 */
	public WriteKmlPlace(EarthCoordinate pointLocation, double radius) {
		this.pointLocation = pointLocation;
		this.radius = radius;
	}
	/**
	 * Constructor.
	 * @param array
	 * @param pointLocation
	 * @param radius
	 */
	protected WriteKmlPlace(ArrayList<Wifi> array, EarthCoordinate pointLocation, double radius) {
		this.pointLocation = pointLocation;
		this.radius = radius;
		initialize();
		checkData(array);
	}
	
	/**
	 * The method check the data, by the time.
	 * @see {@link EarthCoordinate}.
	 * @exception InputException : printStackTrace.
	 */
	public void checkData(ArrayList<Wifi> array) {
		for (Wifi wifi : array)
			if(sameMac(array, wifi) && wifi.getPointLocation().distanceTo(pointLocation) <= radius) 
				addNetwork(wifi);
		try {
			createFile();
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}
	
}
