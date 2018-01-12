package testWrite;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.boehn.kmlframework.kml.Document;
import org.junit.Test;

import objects.Wifi;
import read.SampleScan;
import write.WriteKmlPlace;

/**
 * @author Orel and Samuel.
 */
public class WriteKmlPlaceTest {

	/**
	 * Test method for {@link write.WriteKmlPlace#receiveData(java.util.ArrayList)}.
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
		EarthCoordinate earth = new EarthCoordinate(34.78176760979483,32.08529989645831, 0.0);
		EarthCoordinate tlv = new EarthCoordinate(34.78176760979483,32.08529989645831, 0.0);
		EarthCoordinate jru = new EarthCoordinate(35.21371001267917,31.76831900367241, 0.0);
		SampleScan scan = new SampleScan (date, "id", earth, arrayWifi);
		SampleScan scan2 = new SampleScan(date2, "id", tlv, arrayWifi);
		SampleScan scan3 = new SampleScan(date2, "id", jru, arrayWifi);
		arraySampleScan.add(scan);
		arraySampleScan.add(scan2);
		arraySampleScan.add(scan3);
		Document document = new Document();
		String fileName = "Map";
		String fileName2 = "Map2";
		String fileName3 = "Map3";
		// The distance between Tlv and Jru is : 53904.03271502325 meter
		WriteKmlPlace write = new WriteKmlPlace(fileName, document, earth, 53905);
		write.receiveData(arraySampleScan); // Must print 9 placemarks.
		document = new Document();
		write = new WriteKmlPlace(fileName2, document, earth, 100);
		write.receiveData(arraySampleScan); // Must print 6 placemarks.	
		document = new Document();
		write = new WriteKmlPlace(fileName3, document, earth, 53904);
		write.receiveData(arraySampleScan); // Must print 6 placemarks.
	}

}
