package testFiltering;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import filter.FilteringKmlId;
import libraries.InputException;
import objects.Wifi;
import read.SampleScan;

/**
 * @author Orel and Samuel.
 *
 */
public class FilteringKmlIdTest {

	/**
	 * Test method for {@link filter.FilteringKmlId#filteringBy(java.util.ArrayList)}.
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
		assertEquals(arraySampleScan.size(), 3);
		FilteringKmlId filter = new FilteringKmlId("id2");
		arraySampleScan = filter.filteringBy(arraySampleScan);		
		assertEquals(arraySampleScan.size(), 1);
	}

}
