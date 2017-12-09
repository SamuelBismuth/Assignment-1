package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment1.FilteringKmlId;
import assignment1.InputException;
import assignment1.Scan;
import assignment1.Wifi;

public class FilteringIdTest {

	/**
	 * There is no assert equals because everything depends of the user input.
	 * If the user input EasyId so, the programe keep runing, else, we created an exception.
	 */
	@Test (expected = InputException.class)
	public void testFilteringBy() {
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi("easyName", "easyMac", 5000, -90);
		Scan scan = new Scan(date, "easyId", earth, wifi);
		ArrayList<Scan> array = new ArrayList<Scan>();
		array.add(scan);
		FilteringKmlId filter = new FilteringKmlId();
		try {
			filter.filteringBy(array);
		} 
		catch (InputException ex) {
			ex.printStackTrace();
		}
	}
	
}
