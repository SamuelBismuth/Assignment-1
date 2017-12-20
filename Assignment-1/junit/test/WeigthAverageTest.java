package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import algorithms.WeigthAverage;
import read.SampleScan;
import read.Wifi;

public class WeigthAverageTest {

	/**
	 * This method check if we got the SampleScan of WeightAverage
	 */
	@Test
	public void getSampleScanTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		WeigthAverage wa = new WeigthAverage(scan1);
		assertEquals(scan1, wa.getSampleScan());
	}
	
	/**
	 * This method check if we got the RelevantNumber of WeightAverage
	 */
	@Test
	public void getRelevantNumberTest() {
		double rn = 0.76;
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		WeigthAverage wa = new WeigthAverage(scan1);
		wa.setRelevantNumber(rn);
		assertEquals(rn, wa.getRelevantNumber(), 0.001);
	}
	
	/**
	 * This method check if we got the Pi of WeightAverage
	 */
	@Test
	public void getPiTest() {
		double pi = 0.457765;
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		WeigthAverage wa = new WeigthAverage(scan1);
		wa.setPi(pi);
		assertEquals(pi, wa.getPi(), 0.001);
	}
	
	/**
	 * This method check if we got the ArrayWifi of WeightAverage
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
		WeigthAverage wa = new WeigthAverage(scan1);
		wa.addWifi(wifi);
		wa.addWifi(wifi1);
		assertEquals(arrayWifi, wa.getArrayWifi());
	}
	
	/**
	 * This method check the compare of the relevant number of WeightAverage
	 */
	@Test
	public void compareToTest() {
		double rn = 0.76;
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		WeigthAverage wa = new WeigthAverage(scan1);
		wa.setRelevantNumber(rn);
		assertEquals(0, wa.compareTo(wa));
	}
}
