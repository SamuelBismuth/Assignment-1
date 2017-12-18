package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import read.SampleScan;
import read.Wifi;

public class SampleScanTest {

	@Test
	public void testScan() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		ArrayList<Wifi> arrayWifi1 = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi2 = new Wifi("wifi2", "mac3", 5000, -90);
		Wifi wifi3 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi4 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi5 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi6 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi7 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi8 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi9 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi10 = new Wifi("wifi2", "mac2", 5000, -90);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		arrayWifi.add(wifi2);
		arrayWifi.add(wifi3);
		arrayWifi.add(wifi4);
		arrayWifi.add(wifi5);
		arrayWifi.add(wifi6);
		arrayWifi.add(wifi7);
		arrayWifi.add(wifi8);
		arrayWifi.add(wifi9);
		arrayWifi.add(wifi10);
		arrayWifi1.add(wifi10);
		arrayWifi1.add(wifi6);
		arrayWifi1.add(wifi4);
		arrayWifi1.add(wifi3);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		SampleScan scan2 = new SampleScan(date, id, earth, arrayWifi1);
		assertEquals(date, scan1.getTime());
		assertEquals(earth, scan1.getPointLocation());
		assertEquals(id, scan1.getId());
		assertEquals(arrayWifi, scan1.getArrayWifi());
		arrayWifi.remove(10);
		assertEquals(arrayWifi, scan1.getArrayStrongerWifi());
		assertEquals(arrayWifi1, scan2.getArrayStrongerWifi());
		assertEquals(10, scan1.getWifiNetworks());
		assertEquals(wifi2, scan1.containsSameMac("mac3"));
		assertEquals(null, scan2.containsSameMac("mac4"));
	}

}
