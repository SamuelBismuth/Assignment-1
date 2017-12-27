package testWrite;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.boehn.kmlframework.kml.Document;
import org.junit.Test;

import objects.SampleScan;
import objects.Wifi;
import write.WriteKmlWithoutFilter;

/**
 * @author Orel and Samuel
 */
public class WriteKmlWithoutFilterTest {


	/**
	 * Test method for {@link write.WriteKmlWithoutFilter#receiveData(java.util.ArrayList)}.
	 */
	@Test
	public void testReceiveData() {
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
		SampleScan scan = new SampleScan (date, "id", earth, arrayWifi);
		SampleScan scan2 = new SampleScan(date2, "id", earth, arrayWifi);
		SampleScan scan3 = new SampleScan(date2, "id", earth, arrayWifi);
		arraySampleScan.add(scan);
		arraySampleScan.add(scan2);
		arraySampleScan.add(scan3);
		Document document = new Document();
		String fileName = "Map";
		WriteKmlWithoutFilter write = new WriteKmlWithoutFilter(fileName, document);
		write.receiveData(arraySampleScan); // Must print everything i.e : 9 placemarks.
	}

}
