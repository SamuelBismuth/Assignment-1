package test;

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
	
	@Test
	public void test() {
		boolean bool = true;
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		ArrayList<MacLocation> array1 = new ArrayList<MacLocation>();
		ArrayList<MacLocation> array2 = new ArrayList<MacLocation>();

		int index = 87;
		String macName = "ndk";
		String ssid = "kjh";
		int frequency = 36485;
		double signal = 987.0;
		EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
		GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
		LineAlgo1 sa = new LineAlgo1(index, macName, ssid, frequency, signal, localisation, date);
		
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);
		EarthCoordinate wpointlocation1 = new EarthCoordinate(0.03912,0.03567,0.722222222);
		EarthCoordinate wpointlocation2 = new EarthCoordinate(16.79381271,15.31276327,310.0425545);
		EarthCoordinate pointlocationws1 = new EarthCoordinate(0.04897967,0.04464974,0.90929784);
		EarthCoordinate pointlocationws2 = new EarthCoordinate(28.50479959,25.9774824,532.4570976);
		EarthCoordinate pointlocationwc1 = new EarthCoordinate(35.21645076,32.10322469,653.7864078);
		EarthCoordinate pointlocationwc2 = new EarthCoordinate(35.22673264,32.1034296,658.0198453);

		double pi = 0.476988545;
		double pi1 = 0.17381326;
		double pi2 = 0.158379105;
		double signal1 = -30;		
		double signal2 = -80;
		double signal3 = -90;
		double wsignal1 = 1 / Math.pow(signal1, 2);
		double wsignal2 = 1/(Math.pow(signal2, 2));
		double wsignal3 = 1/(Math.pow(signal3, 2));
		double sumW = wsignal1+wsignal2+wsignal3;
		double sumP = pi+pi1+pi2;
		Wifi w = new Wifi("name", "mac", 5000, signal1);
		Wifi w1 = new Wifi("name", "mac", 5000, signal2);
		Wifi w2 = new Wifi("name", "mac", 5000, signal3);
		
		MacLocationAlgo1 a = new MacLocationAlgo1(pointlocation1, signal1, w);
		MacLocationAlgo1 b = new MacLocationAlgo1(pointlocation2, signal2, w1);
		MacLocationAlgo1 c = new MacLocationAlgo1(pointlocation3, signal3, w2);
		MacLocationAlgo2 d = new MacLocationAlgo2(pointlocation1, pi);
		MacLocationAlgo2 e = new MacLocationAlgo2(pointlocation2, pi1);
		MacLocationAlgo2 f = new MacLocationAlgo2(pointlocation3, pi2);
		array.add(a);
		array.add(b);
		array.add(c);
		array1.add(d);
		array1.add(e);
		array1.add(f);
		array2.add(a);
		array2.add(b);
		array2.add(c);
		array2.add(d);
		array2.add(e);
		
		Mac mac1 = new Mac(macName, array, date);
		Mac mac2 = new Mac(array1);
		Mac mac3 = new Mac(array2);

		assertEquals(date, mac1.getDate());
		assertEquals(macName, mac1.getMacName());
		assertEquals(array.size(), mac1.getNumberOfMac());
		assertEquals(array1.size(), mac2.getNumberOfMac());
		assertEquals(array, mac1.getArrayMacLocation());
		assertEquals(array1, mac2.getArrayMacLocation());
		//assertEquals(pointlocationws1, mac1.getSumWeightPointLocation()); w
		//assertEquals(pointlocationws2, mac2.getSumWeightPointLocation()); w
		assertEquals(sumW, Algorithm1.getSumWeightSignal(array),0.0001);
		assertEquals(sumP, Algorithm1.getSumWeightSignal(array1),0.0001);
		//assertEquals(pointlocationwc1, mac1.getWeightCenter()); w
		//assertEquals(pointlocationwc2, mac2.getWeightCenter()); w
		//assertEquals(signal1, mac1.getStrongerSignal(),0.01); // we need to change the signal definition but w
		assertEquals(false, mac1.getUsed());
		assertEquals(false, mac2.getUsed());
		mac1.setUsed(bool);
		assertEquals(true, mac1.getUsed());
		mac1.setLineAlgo1(sa);
		assertEquals(sa, mac1.getLineAlgo1());
		ArrayList<MacLocation> array3 = mac3.defineArrayListWithFinalNumber(array2);
		array2.remove(4);
		assertEquals(array2, array3);
	}	
	
}
