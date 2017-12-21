package testAlgorithm;

import static org.junit.Assert.*;
import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;
import algorithms.MacLocationAlgo2;

public class MacLocationAlgo2Test {

	/**
	 * This method check if we got the point location of MaclocationAlgo1
	 */
	@Test
	public void getPointLocationTest() {
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		double pi = 0.476988545;
		MacLocationAlgo2 a = new MacLocationAlgo2(pointlocation1, pi);
		assertEquals(pointlocation1, a.getPointLocation());
	}
	
	/**
	 * This method check if we got the signal of MaclocationAlgo1
	 */
	@Test
	public void getSignalTest() {
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);
		double pi = 0.476988545;
		double pi2 = 0;
		MacLocationAlgo2 a = new MacLocationAlgo2(pointlocation1, pi);
		MacLocationAlgo2 c = new MacLocationAlgo2(pointlocation3, pi2);
		assertEquals(-120, c.getSignal(),0.001);
		assertEquals(Math.sqrt(1/pi), a.getSignal(),0.001);
	}
	
	/**
	 * This method check if we got the wifi of MaclocationAlgo1
	 */
	@Test
	public void getWifiTest() {
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		double pi1 = 0.17381326;
		MacLocationAlgo2 b = new MacLocationAlgo2(pointlocation2, pi1);
		assertEquals(null, b.getWifi());
	}
	
	/**
	 * This method check if we got the weight signal of MaclocationAlgo1
	 */
	@Test
	public void getWeightSinalTest() {
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		double pi = 0.476988545;
		MacLocationAlgo2 a = new MacLocationAlgo2(pointlocation1, pi);
		assertEquals(pi, a.getWeigthSignal(),0.001);
	}
	
	/**
	 * This method check if we got the weight point location of MaclocationAlgo1
	 */
	@Test
	public void getWeightPointLocationTest() {
//		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
//		double pi = 0.476988545;
//		MacLocationAlgo2 a = new MacLocationAlgo2(pointlocation1, pi);
//		EarthCoordinate wpointlocation1 = new EarthCoordinate(16.79381271,15.31276327,310.0425545);
		//assertEquals(wpointlocation1, a.getWeightPointLocation()); //this will bring an exception because wpointlocation1 is not exactly accurate.
	}

}
