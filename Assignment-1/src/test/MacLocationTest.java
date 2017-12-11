package test;

import static org.junit.Assert.*;
import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment.MacLocation;

public class MacLocationTest {

	@Test
	public void test() {
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);
		EarthCoordinate wpointlocation1 = new EarthCoordinate(0.03912,0.03567,0.722222222);
		int signal1 = -30;		
		int signal2 = -80;
		int signal3 = -90;
		double wsignal1 = 1 / Math.pow(signal1, 2);
		double wsignal2 = 1/(Math.pow(signal2, 2));
		double wsignal3 = 1/(Math.pow(signal3, 2));
		//String s = "Weight point location = [longitude: 0.03912, latitude: 0.03567, altitude: 0.722222222], weight signal= 0.001111111";
		
		MacLocation a = new MacLocation(pointlocation1, signal1);
		MacLocation b = new MacLocation(pointlocation2, signal2);
		MacLocation c = new MacLocation(pointlocation3, signal3);
		
		assertEquals(pointlocation1, a.getPointLocation());
		assertEquals(pointlocation2, b.getPointLocation());
		assertEquals(pointlocation3, c.getPointLocation());
		assertEquals(signal1, a.getSignal());
		assertEquals(signal2, b.getSignal());
		assertEquals(signal3, c.getSignal());
		assertEquals(0.001111111,wsignal1,a.getWeigthSignal());
		assertEquals(0.00015625,wsignal2, b.getWeigthSignal());
		assertEquals(0.000123457,wsignal3, c.getWeigthSignal());
		assertEquals(0, a.compareTo(a));
		assertEquals(0, b.compareTo(b));
		assertEquals(0, c.compareTo(c));
		//assertEquals(wpointlocation1, a.getWeightPointLocation());
		//assertEquals(s, a.toString());
	}

}
