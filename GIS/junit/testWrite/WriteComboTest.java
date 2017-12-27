package testWrite;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import objects.Wifi;
import read.SampleScan;
import write.WriteCombo;

/**
 * @author Samuel
 *
 */
public class WriteComboTest {

	/**
	 * Test method for {@link write.WriteCombo#receiveData(java.util.ArrayList)}.
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
		SampleScan scan = new SampleScan (date, "id1", earth, arrayWifi);
		SampleScan scan2 = new SampleScan(date2, "id2", earth, arrayWifi);
		SampleScan scan3 = new SampleScan(date2, "id3", earth, arrayWifi);
		arraySampleScan.add(scan);
		arraySampleScan.add(scan2);
		arraySampleScan.add(scan3);
		String fileName = "Table";
		WriteCombo write = new WriteCombo(fileName);
		write.receiveData(arraySampleScan); 
	}
}