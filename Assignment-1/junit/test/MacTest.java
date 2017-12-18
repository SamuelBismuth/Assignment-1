package test;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.ArrayList;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;
import assignment.Mac;
import assignment.MacLocation;

public class MacTest {

	@Test
	public void test() {
		ArrayList<MacLocation> array = new ArrayList<MacLocation>();
		String macname1 = "orel";
		EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
		EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
		EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);
		EarthCoordinate pointlocationwc = new EarthCoordinate(35.21645076,32.10322469,653.7864078);
		EarthCoordinate pointlocationws = new EarthCoordinate(0.04897967,0.04464974,0.90929784);

		int signal1 = -30;		
		int signal2 = -80;
		int signal3 = -90;
		double wsignal1 = 1 / Math.pow(signal1, 2);
		double wsignal2 = 1/(Math.pow(signal2, 2));
		double wsignal3 = 1/(Math.pow(signal3, 2));
		double sumW = wsignal1+wsignal2+wsignal3;
		MacLocation a = new MacLocation(pointlocation1, signal1);
		MacLocation b = new MacLocation(pointlocation2, signal2);
		MacLocation c = new MacLocation(pointlocation3, signal3);
		array.add(a);
		array.add(b);
		array.add(c);
		
		Mac mac1 = new Mac(macname1, array);
		assertEquals(array, mac1.getArrayMacLocation());
		assertEquals(macname1, mac1.getMacName());
		assertEquals(3, mac1.getNumberOfMac());
		assertEquals(0.0018595679012,sumW, mac1.getSumWeightSignal());
		//assertEquals(signal1, mac1.getStrongerSignal());
		//assertEquals(pointlocationwc, mac1.getWeightCenter());
		//assertEquals(pointlocationws, mac1.getSumWeightPointLocation());

		
	}	
	
}
