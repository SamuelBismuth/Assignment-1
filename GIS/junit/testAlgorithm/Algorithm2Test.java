package testAlgorithm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import libraries.Algorithm2;
import objects.WeigthAverage;
import objects.Wifi;
import read.SampleScan;

public class Algorithm2Test {
	
	/**
	 * This method check that the calculation of diff is working
	 */
	@Test
	public void getDiffTest() {
		Wifi wifi8 = new Wifi("data", "mac2", 5000, -90);
		Wifi wifi9 = new Wifi("input", "mac2", 5000, -50);
		Wifi wifi10 = new Wifi("data", "mac2", 5000, -120);
	
		assertEquals(100.0, Algorithm2.getDiff(wifi9, wifi10),0.001);
		assertEquals(40.0, Algorithm2.getDiff(wifi9, wifi8),0.001);
	}
	
	/**
	 * This method check that the calculation of weight is working
	 */
	@Test
	public void getWeightTest() {
		Wifi wifi8 = new Wifi("data", "mac2", 5000, -90);
		Wifi wifi9 = new Wifi("input", "mac2", 5000, -50);
		
		assertEquals(0.9146101039, Algorithm2.getWeight(wifi9, wifi8),0.001);
	}
	
	/**
	 * This method check that the calculation of pi is working and check that it put it in the weightAverage
	 */
	@Test
	public void inputPiTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi8 = new Wifi("data", "mac2", 5000, -90);
		Wifi wifi9 = new Wifi("input", "mac2", 5000, -50);
		Wifi wifi10 = new Wifi("data", "mac2", 5000, -120);
		arrayWifi.add(wifi8);
		arrayWifi.add(wifi9);
		arrayWifi.add(wifi10);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		WeigthAverage wa = new WeigthAverage(scan1);
		wa.addWifi(wifi8);
		wa.addWifi(wifi9);
		wa.addWifi(wifi10);
		double i = Algorithm2.getWeight(wifi9, wifi8);
		double j = Algorithm2.getWeight(wifi9, wifi9);
		double k = Algorithm2.getWeight(wifi9, wifi10);
		double p = i*j*k;
		
		Algorithm2.inputPi(scan1, wa);
		assertEquals(p, wa.getPi(), 0.001);
	}
	
	/**
	 * This method check if the setting of pi is working by checking if we the get it
	 */
	@Test
	public void getPiTest() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		Wifi wifi8 = new Wifi("data", "mac2", 5000, -90);
		Wifi wifi9 = new Wifi("input", "mac2", 5000, -50);
		Wifi wifi10 = new Wifi("data", "mac2", 5000, -120);
		arrayWifi.add(wifi8);
		arrayWifi.add(wifi9);
		arrayWifi.add(wifi10);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		WeigthAverage wa = new WeigthAverage(scan1);
		wa.addWifi(wifi8);
		wa.addWifi(wifi9);
		wa.addWifi(wifi10);
		ArrayList<WeigthAverage> ar = new ArrayList<WeigthAverage>();
		ar.add(wa);
		double i = Algorithm2.getWeight(wifi9, wifi8);
		double j = Algorithm2.getWeight(wifi9, wifi9);
		double k = Algorithm2.getWeight(wifi9, wifi10);
		double p = i*j*k;
		
		Algorithm2.setArrayPi(scan1, ar);
		assertEquals(p, ar.get(0).getPi(),0.001);
	}

	/**
	 * This method check that the calculation of the relevantNumber is working 
	 */
	@Test
	public void relevantNumberTest(){
		Wifi wifi8 = new Wifi("data", "mac2", 5000, -90);
		Wifi wifi9 = new Wifi("input", "mac2", 5000, -50);
		
		assertEquals(0.1, Algorithm2.revelantNumber(wifi9, null),0.01);
		double rn = ((Math.abs(wifi9.getSignal()) - Math.abs(wifi8.getSignal() - wifi9.getSignal())) / Math.abs(wifi9.getSignal()));
		assertEquals(rn , Algorithm2.revelantNumber(wifi9, wifi8),0.0001);
	}
}
