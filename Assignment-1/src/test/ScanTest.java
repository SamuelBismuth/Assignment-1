package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment1.Scan;
import assignment1.Wifi;

public class ScanTest {

	@Test
	public void testScan() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi2 = new Wifi("wifi2", "mac2", 5000, -90);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi2);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Scan scan = new Scan(date, "id", earth, arrayWifi);
		assertEquals(-90, scan.getArrayWifi().get(0).getSignal());
	}

}
