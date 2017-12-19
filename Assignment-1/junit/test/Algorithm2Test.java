package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import algorithms.WeigthAverage;
import libraries.Algorithm2;
import read.SampleScan;
import read.Wifi;

public class Algorithm2Test {

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
		Wifi wifi7 = new Wifi("data", "mac2", 5000, -90);
		Wifi wifi8 = new Wifi("data", "mac2", 5000, -90);
		Wifi wifi9 = new Wifi("input", "mac2", 5000, -50);
		Wifi wifi10 = new Wifi("data", "mac2", 5000, -120);
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
		WeigthAverage wa = new WeigthAverage(scan1);
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
		
		assertEquals(100.0, Algorithm2.getDiff(wifi9, wifi10),0.001);
		assertEquals(40.0, Algorithm2.getDiff(wifi9, wifi8),0.001);
		assertEquals(0.9146101039, Algorithm2.getWeight(wifi9, wifi8),0.001);
		double a = Algorithm2.getWeight(wifi9, wifi);
		double b = Algorithm2.getWeight(wifi9, wifi1);
		double c = Algorithm2.getWeight(wifi9, wifi2);
		double d = Algorithm2.getWeight(wifi9, wifi3);
		double e = Algorithm2.getWeight(wifi9, wifi4);
		double f = Algorithm2.getWeight(wifi9, wifi5);
		double g = Algorithm2.getWeight(wifi9, wifi6);
		double h = Algorithm2.getWeight(wifi9, wifi7);
		double i = Algorithm2.getWeight(wifi9, wifi8);
		double j = Algorithm2.getWeight(wifi9, wifi9);
		double k = Algorithm2.getWeight(wifi9, wifi10);
		double p = a*b*c*d*e*f*g*h*i*j*k;
//		Algorithm2.inputPi(scan1, wa);
//		assertEquals(p, wa.getPi(), 0.001);
		ArrayList<WeigthAverage> ar = new ArrayList<WeigthAverage>();
		ar.add(wa);
		Algorithm2.setArrayPi(scan1, ar);
		assertEquals(p, ar.get(0).getPi(),0.001);
		assertEquals(0.1, Algorithm2.revelantNumber(wifi9, null),0.01);
		double rn = ((Math.abs(wifi9.getSignal()) - Math.abs(wifi8.getSignal() - wifi9.getSignal())) / Math.abs(wifi9.getSignal()));
		assertEquals(rn , Algorithm2.revelantNumber(wifi9, wifi8),0.0001);		
	}

}
