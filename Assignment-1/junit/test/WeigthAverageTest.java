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

	@Test
	public void test() {
		
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
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

		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		double rn = 0.76;
		double pi = 0.457765;
		WeigthAverage wa = new WeigthAverage(scan1);
		assertEquals(scan1, wa.getSampleScan());
		wa.setRelevantNumber(rn);
		assertEquals(rn, wa.getRelevantNumber(), 0.001);
		wa.setPi(pi);
		assertEquals(pi, wa.getPi(), 0.001);
		wa.addWifi(wifi);
		wa.addWifi(wifi1);
		wa.addWifi(wifi2);
		wa.addWifi(wifi3);
		wa.addWifi(wifi4);
		wa.addWifi(wifi5);
		wa.addWifi(wifi6);
		wa.addWifi(wifi7);
		wa.addWifi(wifi8);
		wa.addWifi(wifi9);
		wa.addWifi(wifi10);
		assertEquals(arrayWifi, wa.getArrayWifi());
		assertEquals(0, wa.compareTo(wa));

	}

}
