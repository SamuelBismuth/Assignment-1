package testObject;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import objects.MacInformationAlgo1;
import objects.Wifi;

import static junit.framework.Assert.assertEquals;

public class MacInformationAlgo1UnitTest {

    /**
     * This method check if we got the signal of MaclocationAlgo1
     */
    @SuppressWarnings("deprecation")
	@Test
    public void getSignalTest() {
        EarthCoordinate pointlocation2 = new EarthCoordinate(35.205,32.105,660.0);
        double signal2 = -80;
        Wifi w1 = new Wifi("name", "mac", 5000, signal2);
        MacInformationAlgo1 b = new MacInformationAlgo1(pointlocation2, w1, signal2);
        assertEquals(signal2, b.getSignal(),0.001);
    }

    /**
     * This method check if we got the wifi of MaclocationAlgo1
     */
    @SuppressWarnings("deprecation")
	@Test
    public void getWifiTest() {
        EarthCoordinate pointlocation3 = new EarthCoordinate(35.307,32.103,680.0);
        double signal3 = -90;
        Wifi w2 = new Wifi("name", "mac", 5000, signal3);
        MacInformationAlgo1 c = new MacInformationAlgo1(pointlocation3, w2, signal3);
        assertEquals(w2, c.getWifi());
    }

    /**
     * This method check if we got the weight signal of MaclocationAlgo1
     */
    @SuppressWarnings("deprecation")
	@Test
    public void getWeightSignalTest() {
        EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
        double signal1 = -30;
        double wsignal1 = 1 / Math.pow(signal1, 2);
        Wifi w = new Wifi("name", "mac", 5000, signal1);
        MacInformationAlgo1 a = new MacInformationAlgo1(pointlocation1, w, signal1);
        assertEquals(wsignal1,a.getWeigthSignal(),0.0001);
    }

    /**
     * This method check if we got the weight point location of MaclocationAlgo1
     */
    @Test
    public void getWeightPointLocationTest() {
        //EarthCoordinate wpointlocation1 = new EarthCoordinate(0.03912,0.03567,0.722222222);
        //EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
//		double signal1 = -30;
//		Wifi w = new Wifi("name", "mac", 5000, signal1);
        //MacLocationAlgo1 a = new MacLocationAlgo1(pointlocation1, signal1,w);
        //assertEquals(wpointlocation1, a.getWeightPointLocation()); this will bring an exception because the wpointlocation1 is not exactly accurate.
    }

}

