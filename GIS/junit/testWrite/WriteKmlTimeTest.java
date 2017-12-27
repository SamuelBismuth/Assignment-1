package testWrite;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.boehn.kmlframework.kml.Document;
import org.junit.Test;

import objects.SampleScan;
import objects.Wifi;
import write.WriteKmlTime;

/**
 * @author Samuel
 *
 */
public class WriteKmlTimeTest {

	/**
	 * Test method for {@link write.WriteKmlTime#receiveData(java.util.ArrayList)}.
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
		GregorianCalendar date3 = new GregorianCalendar(2015, 8, 9, 2, 9, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		SampleScan scan = new SampleScan (date, "id1", earth, arrayWifi);
		SampleScan scan2 = new SampleScan(date2, "id2", earth, arrayWifi);
		SampleScan scan3 = new SampleScan(date3, "id3", earth, arrayWifi);
		arraySampleScan.add(scan);
		arraySampleScan.add(scan2);
		arraySampleScan.add(scan3);
		Document document = new Document();
		String fileName = "Map";
		String fileName2 = "Map2";
		WriteKmlTime write = new WriteKmlTime(fileName, document, date3, date);
		write.receiveData(arraySampleScan); // Must print 3 placemarks.
		document = new Document();
		write = new WriteKmlTime(fileName2, document, date2, date);
		write.receiveData(arraySampleScan); // Must not print placemarks.
	}

}
