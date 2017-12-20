package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import read.SampleScan;
import read.Wifi;

public class SampleScanTest {

	/**
	 * This method check that we got the time of the SampleScan
	 */
	@Test
	public void getTimeTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		assertEquals(date, scan1.getTime());
	}
	
	/**
	 * This method check that we got the PointLocation of the SampleScan
	 */
	@Test
	public void getPointLocationTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		assertEquals(earth, scan1.getPointLocation());
	}
	
	/**
	 * This method check that we got the id of the SampleScan
	 */
	@Test
	public void getIdTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		assertEquals(id, scan1.getId());
	}

	/**
	 * This method check that we got the array of wifis of the SampleScan
	 */
	@Test
	public void getArrayWifiTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		assertEquals(arrayWifi, scan1.getArrayWifi());
	}
	
	/**
	 * This method check that we got the 10 strongest signals if the is more 10 wifis 
	 */
	@Test
	public void getArrayStrongerWifiTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi2 = new Wifi("wifi2", "mac2", 5000, -90);
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
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		arrayWifi.remove(10);
		assertEquals(arrayWifi, scan1.getArrayStrongerWifi());
	}
		
	/**
	 * This method check that we got the size of wifi networks of the SampleScan
	 */
	@Test
	public void getWifiNetworksTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		assertEquals(2, scan1.getWifiNetworks());
	}

	/**
	 * This method check that we got the wifi who got the same mac that we put
	 */
	@Test
	public void containsSameMacTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi2 = new Wifi("wifi2", "mac2", 5000, -90);
		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		arrayWifi.add(wifi2);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		assertEquals(wifi1, scan1.containsSameMac("mac2"));
		assertEquals(null, scan1.containsSameMac("mac4"));
	}

}
