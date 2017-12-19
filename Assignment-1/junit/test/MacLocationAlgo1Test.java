package test;

import static org.junit.Assert.*;
import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;
import algorithms.MacLocationAlgo1;
import read.Wifi;

public class MacLocationAlgo1Test {

	/**
	 * This method for {@link }
	 */
	@Test
	public void test() {
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);
		//EarthCoordinate wpointlocation1 = new EarthCoordinate(0.03912,0.03567,0.722222222);
		double signal1 = -30;		
		double signal2 = -80;
		double signal3 = -90;
		Wifi w = new Wifi("name", "mac", 5000, signal1);
		Wifi w1 = new Wifi("name", "mac", 5000, signal2);
		Wifi w2 = new Wifi("name", "mac", 5000, signal3);
		double wsignal1 = 1 / Math.pow(signal1, 2);
		double wsignal2 = 1/(Math.pow(signal2, 2));
		double wsignal3 = 1/(Math.pow(signal3, 2));
		
		MacLocationAlgo1 a = new MacLocationAlgo1(pointlocation1, signal1,w);
		MacLocationAlgo1 b = new MacLocationAlgo1(pointlocation2, signal2,w1);
		MacLocationAlgo1 c = new MacLocationAlgo1(pointlocation3, signal3,w2);
		
		assertEquals(pointlocation1, a.getPointLocation());
		assertEquals(pointlocation2, b.getPointLocation());
		assertEquals(pointlocation3, c.getPointLocation());
		assertEquals(signal1, a.getSignal(),0.001);
		assertEquals(signal2, b.getSignal(),0.001);
		assertEquals(signal3, c.getSignal(),0.001);
		assertEquals(w, a.getWifi());
		assertEquals(w1, b.getWifi());
		assertEquals(w2, c.getWifi());
		assertEquals(wsignal1,a.getWeigthSignal(),0.0001);
		assertEquals(wsignal2, b.getWeigthSignal(),0.0001);
		assertEquals(wsignal3, c.getWeigthSignal(),0.0001);
		//assertEquals(wpointlocation1, a.getWeightPointLocation()); this will bring an exception because the wpointlocation1 is not exactly accurate. 
		}

}
