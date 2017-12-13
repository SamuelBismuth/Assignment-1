package assignment;

import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

/**
 * This class write the kml file with the place filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel.
 * TODO : check the radius with JUNIT.
 */
public class WriteKmlPlace extends WriteKml implements WriteFile<Scan> {

	private EarthCoordinate pointLocation;
	private double radius;
	
	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param radius.
	 */
	public WriteKmlPlace(EarthCoordinate pointLocation, double radius, ArrayList<Mac> array) {
		super(array);
		this.pointLocation = pointLocation;
		this.radius = radius;
	}
	
	/**
	 * The method check the data, by the time.
	 * @see {@link EarthCoordinate}.
	 * @exception InputException : printStackTrace.
	 */
	public void checkData(ArrayList<Scan> array, String fileNameExport) {
		initialize();
		for (Scan scan : array) 
			if(scan.getPointLocation().distanceTo(pointLocation) <= radius) 
				addNetwork(scan);
		try {
			createFile(fileNameExport);
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}
	
}
