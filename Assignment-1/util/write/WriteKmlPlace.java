package write;

import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.boehn.kmlframework.kml.Document;

import libraries.Filter;
import libraries.InputException;
import libraries.KmlUtil;
import read.SampleScan;

/**
 * This class write the kml file with the place filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel.
 * TODO : check the radius with JUNIT.
 */
public class WriteKmlPlace extends WriteKml implements WriteFile<SampleScan> {

	private Document document;
	private EarthCoordinate pointLocation;
	private double radius;

	/**
	 * Constructor.
	 * @param pointLocation.
	 * @param radius.
	 */
	public WriteKmlPlace(String fileName, Document document, EarthCoordinate pointLocation, double radius) {
		super(fileName, document);
		this.document = document;
		this.pointLocation = pointLocation;
		this.radius = radius;
	}

	/**
	 * The method check the data, by the time.
	 * @see {@link EarthCoordinate}.
	 * @exception InputException : printStackTrace.
	 */
	@Override
	public void receiveData(ArrayList<SampleScan> array) {
		Filter.removeDuplicateMac(array);
		writeHeader();
		for (SampleScan scan : array) 
			if(scan.getPointLocation().distanceTo(pointLocation) <= radius) 
				KmlUtil.addPlacemark(scan, document);
		try {
			writeFile();
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}

}
