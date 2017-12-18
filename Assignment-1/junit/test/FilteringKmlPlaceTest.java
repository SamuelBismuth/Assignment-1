package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import filter.FilteringKmlPlace;
import libraries.InputException;
import read.SampleScan;
import read.Wifi;

/**
 * @author Orel and Samuel.
 *
 */
public class FilteringKmlPlaceTest {

	/**
	 * Test method for {@link filter.FilteringKmlPlace#filteringBy(java.util.ArrayList)}.
	 */
	@Test
	public void testFilteringBy() {
		ArrayList<SampleScan> arraySampleScan = new ArrayList<SampleScan>();
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("name", "mac", 5000, -70);
		Wifi wifi2 = new Wifi("name", "mac", 5000, -90);
		Wifi wifi3 = new Wifi("name", "mac", 5000, -80);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi2);
		arrayWifi.add(wifi3);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		GregorianCalendar date2 = new GregorianCalendar(2016, 8, 9, 2, 9, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		SampleScan scan = new SampleScan (date, "id1", earth, arrayWifi);
		SampleScan scan2 = new SampleScan(date2, "id2", earth, arrayWifi);
		SampleScan scan3 = new SampleScan(date2, "id3", earth, arrayWifi);
		arraySampleScan.add(scan);
		arraySampleScan.add(scan2);
		arraySampleScan.add(scan3);
		FilteringKmlPlace filter = new FilteringKmlPlace();
		try {
			filter.filteringBy(arraySampleScan);			
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
	}

}
