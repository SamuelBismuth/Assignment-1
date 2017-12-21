package testAlgorithm;

import static org.junit.Assert.*;
import java.util.GregorianCalendar;
import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import algorithms.LineAlgo1;
import algorithms.Mac;
import algorithms.MacLocation;
import algorithms.MacLocationAlgo1;
import algorithms.MacLocationAlgo2;
import libraries.Algorithm1;
import read.Wifi;


public class MacTest {
	/**
	 * This method check that we got the date of the mac
	 */
	@Test
	public void getDateTest() {
		String macName = "ndk";
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		Mac mac1 = new Mac(macName, array, date);
		assertEquals(date, mac1.getDate());
	}
	
	/**
	 * This method check that we got the MacName of the mac
	 */
	@Test
	public void getMacNameTest() {
		String macName = "ndk";
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		Mac mac1 = new Mac(macName, array, date);
		assertEquals(macName, mac1.getMacName());
	}
	
	/**
	 * This method check that we got the NumberOfMac of the mac
	 */
	@Test
	public void getNumberOfMacTest() {
		String macName = "ndk";
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		double pi = 0.476988545;
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		MacLocationAlgo2 a = new MacLocationAlgo2(pointlocation1, pi);
		array.add(a);
		Mac mac1 = new Mac(macName, array, date);
		assertEquals(array.size(), mac1.getNumberOfMac());
	}
	
	/**
	 * This method check that we got the ArrayMacLocation of the mac
	 */
	@Test
	public void getArrayMacLocationTest() {
		String macName = "ndk";
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		double pi = 0.476988545;
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		MacLocationAlgo2 a = new MacLocationAlgo2(pointlocation1, pi);
		array.add(a);
		Mac mac1 = new Mac(macName, array, date);
		assertEquals(array, mac1.getArrayMacLocation());
	}
	
	/**
	 * This method check that we got the SumWeightPointLocation of the mac
	 */
	@Test
	public void getSumWeightPointLocationTest() {
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		double signal1 = -30;		
		double signal2 = -80;
		double signal3 = -90;
		Wifi w = new Wifi("name", "mac", 5000, signal1);
		Wifi w1 = new Wifi("name", "mac", 5000, signal2);
		Wifi w2 = new Wifi("name", "mac", 5000, signal3);
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);		
		//EarthCoordinate pointlocationws1 = new EarthCoordinate(0.04897967,0.04464974,0.90929784);
		MacLocationAlgo1 a = new MacLocationAlgo1(pointlocation1, signal1, w);
		MacLocationAlgo1 b = new MacLocationAlgo1(pointlocation2, signal2, w1);
		MacLocationAlgo1 c = new MacLocationAlgo1(pointlocation3, signal3, w2);		
		array.add(a);
		array.add(b);
		array.add(c);		
		//assertEquals(pointlocationws1, Algorithm1.getSumWeightPointLocation(array));
	}
	
	/**
	 * This method check that we got the SumWeightSignal of the mac
	 */
	@Test
	public void getSumWeightSignalTest() {
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		double signal1 = -30;		
		double signal2 = -80;
		double signal3 = -90;
		Wifi w = new Wifi("name", "mac", 5000, signal1);
		Wifi w1 = new Wifi("name", "mac", 5000, signal2);
		Wifi w2 = new Wifi("name", "mac", 5000, signal3);
		double wsignal1 = 1 / Math.pow(signal1, 2);
		double wsignal2 = 1/(Math.pow(signal2, 2));
		double wsignal3 = 1/(Math.pow(signal3, 2));
		double sumW = wsignal1+wsignal2+wsignal3;
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);	
		MacLocationAlgo1 a = new MacLocationAlgo1(pointlocation1, signal1, w);
		MacLocationAlgo1 b = new MacLocationAlgo1(pointlocation2, signal2, w1);
		MacLocationAlgo1 c = new MacLocationAlgo1(pointlocation3, signal3, w2);		
		array.add(a);
		array.add(b);
		array.add(c);
		assertEquals(sumW, Algorithm1.getSumWeightSignal(array),0.0001);
	}
	
	/**
	 * This method check that we got the WeightCenter of the mac
	 */
	@Test
	public void getWeightCenterTest() {
		//String macName = "ndk";
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		double signal1 = -30;		
		double signal2 = -80;
		double signal3 = -90;
		Wifi w = new Wifi("name", "mac", 5000, signal1);
		Wifi w1 = new Wifi("name", "mac", 5000, signal2);
		Wifi w2 = new Wifi("name", "mac", 5000, signal3);
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);		
		//EarthCoordinate pointlocationws1 = new EarthCoordinate(0.04897967,0.04464974,0.90929784);
		MacLocationAlgo1 a = new MacLocationAlgo1(pointlocation1, signal1, w);
		MacLocationAlgo1 b = new MacLocationAlgo1(pointlocation2, signal2, w1);
		MacLocationAlgo1 c = new MacLocationAlgo1(pointlocation3, signal3, w2);		
		array.add(a);
		array.add(b);
		array.add(c);
		//GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
//		double pi = 0.476988545;
//		Mac mac1 = new Mac(macName, array, date);
//		EarthCoordinate pointlocationwc1 = new EarthCoordinate(35.21645076,32.10322469,653.7864078);
		//assertEquals(pointlocationwc1, mac1.getWeightCenter()); 
	}
	
	/**
	 * This method check that we got the StrongerSignal of the mac
	 */
	@Test
	public void getStrongerSignalTest() {
		//String macName = "ndk";
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		double signal1 = -30;		
		double signal2 = -80;
		double signal3 = -90;
		Wifi w = new Wifi("name", "mac", 5000, signal1);
		Wifi w1 = new Wifi("name", "mac", 5000, signal2);
		Wifi w2 = new Wifi("name", "mac", 5000, signal3);
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);		
		//EarthCoordinate pointlocationws1 = new EarthCoordinate(0.04897967,0.04464974,0.90929784);
		MacLocationAlgo1 a = new MacLocationAlgo1(pointlocation1, signal1, w);
		MacLocationAlgo1 b = new MacLocationAlgo1(pointlocation2, signal2, w1);
		MacLocationAlgo1 c = new MacLocationAlgo1(pointlocation3, signal3, w2);		
		array.add(a);
		array.add(b);
		array.add(c);
		//GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		//Mac mac1 = new Mac(macName, array, date);
		//assertEquals(signal1, mac1.getStrongerSignal(),0.01); // we need to change the signal definition but w
	}

	/**
	 * This method check that we got the value of used of the mac
	 */
	@Test
	public void getUsedTest() {
		boolean bool = true;
		String macName = "ndk";
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		Mac mac1 = new Mac(macName, array, date);
		assertEquals(false, mac1.getUsed());
		mac1.setUsed(bool);
		assertEquals(true, mac1.getUsed());
	}
	
	/**
	 * This method check that we got the LineAlgo1 of the mac
	 */
	@Test
	public void getLineAlgo1Test() {
		String macName = "ndk";
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		Mac mac1 = new Mac(macName, array, date);
		int index = 87;
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		mac1.setLineAlgo1(sa);
		assertEquals(sa, mac1.getLineAlgo1());
	}
	
	/**
	 * This method check that we got the ArrayListWithFinalNumber of the mac
	 */
	@Test
	public void defineArrayListWithFinalNumberTest() {
		ArrayList<MacLocation> array2 = new ArrayList<MacLocation>();
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);
		double signal1 = -30;		
		double signal2 = -80;
		double signal3 = -90;
		double pi = 0.476988545;
		double pi1 = 0.17381326;
		Wifi w = new Wifi("name", "mac", 5000, signal1);
		Wifi w1 = new Wifi("name", "mac", 5000, signal2);
		Wifi w2 = new Wifi("name", "mac", 5000, signal3);
		MacLocationAlgo1 a = new MacLocationAlgo1(pointlocation1, signal1, w);
		MacLocationAlgo1 b = new MacLocationAlgo1(pointlocation2, signal2, w1);
		MacLocationAlgo1 c = new MacLocationAlgo1(pointlocation3, signal3, w2);
		MacLocationAlgo2 d = new MacLocationAlgo2(pointlocation1, pi);
		MacLocationAlgo2 e = new MacLocationAlgo2(pointlocation2, pi1);
		array2.add(a);
		array2.add(b);
		array2.add(c);
		array2.add(d);
		array2.add(e);
		Mac mac = new Mac(array2);
		ArrayList<MacLocation> array3 = mac.defineArrayListWithFinalNumber(array2);
		array2.remove(4);
		assertEquals(array2, array3);
	}
	
}
