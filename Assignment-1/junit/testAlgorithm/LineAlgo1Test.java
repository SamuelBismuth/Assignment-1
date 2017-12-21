package testAlgorithm;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

import org.junit.Test;

import algorithms.LineAlgo1;

public class LineAlgo1Test {

	/**
	 * This method check if we got the index of LineAlgo1
	 */
	@Test
	public void getIndexTest() {
		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		assertEquals(index, sa.getIndex());
	}
	
	/**
	 * This method check if we got the mac name of LineAlgo1
	 */
	@Test
	public void getMacNameTest() {
		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		assertEquals(macName, sa.getMacName());
	}
	
	/**
	 * This method check if we got the ssid of LineAlgo1
	 */
	@Test
	public void getSsidTest() {
		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		assertEquals(ssid, sa.getSsid());
	}
	
	/**
	 * This method check if we got the frequency of LineAlgo1
	 */
	@Test
	public void getFrequencyTest() {
		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		assertEquals(frequency, sa.getFrequency());
	}
	
	/**
	 * This method check if we got the signal of LineAlgo1
	 */
	@Test
	public void getSignalTest() {
		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		assertEquals(signal, sa.getSignal(),0.0001);
	}
	
	/**
	 * This method check if we got the localisation of LineAlgo1
	 */
	@Test
	public void getLocalisationTest() {
		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		assertEquals(localisation, sa.getLocalisation());
	}
		
	/**
	 * This method check if we got the date of LineAlgo1
	 */
	@Test
	public void getDateTest() {
		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		assertEquals(date, sa.getDate());
	}

}
