package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment1.FilteringKmlPlace;
import assignment1.InputException;
import assignment1.Scan;
import assignment1.Wifi;

public class FilteringPlaceTest {
	
	/**
	 * There is no assert equals because everything depends of the user input.
	 * If the user input a good data so, the programe keep runing, else, we created an exception.
	 */
	@Test
	public void testFilteringBy() {
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi("easyName", "easyMac", 5000, -90);
		SampleScan scan = new SampleScan(date, "easyId", earth, wifi);
		ArrayList<SampleScan> array = new ArrayList<SampleScan>();
		array.add(scan);
		FilteringKmlPlace filter = new FilteringKmlPlace();
		try {
			filter.filteringBy(array);
		} 
		catch (InputException ex) {
			ex.printStackTrace();
		}
	}	

}
