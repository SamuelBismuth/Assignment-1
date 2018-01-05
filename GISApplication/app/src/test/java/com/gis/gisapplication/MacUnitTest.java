package com.gis.gisapplication;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import libraries.Algorithm1;
import objects.LineAlgo1;
import objects.Mac;
import objects.MacInformation;
import objects.MacInformationAlgo1;
import objects.MacInformationAlgo2;
import objects.Wifi;

import static junit.framework.Assert.assertEquals;

public class MacUnitTest {
    /**
     * This method check that we got the date of the mac
     */
    @Test
    public void getDateTest() {
        String macName = "ndk";
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
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
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
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
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
        GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
        double pi = 0.476988545;
        EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
        MacInformationAlgo2 a = new MacInformationAlgo2(pointlocation1,null , pi);
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
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
        GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
        double pi = 0.476988545;
        EarthCoordinate pointlocation1 = new EarthCoordinate(35.208,32.103,650.0);
        MacInformationAlgo2 a = new MacInformationAlgo2(pointlocation1,null,  pi);
        array.add(a);
        Mac mac1 = new Mac(macName, array, date);
        assertEquals(array, mac1.getArrayMacLocation());
    }

    /**
     * This method check that we got the SumWeightPointLocation of the mac
     */
    @Test
    public void getSumWeightPointLocationTest() {
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
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
        MacInformationAlgo1 a = new MacInformationAlgo1(pointlocation1, w, signal1);
        MacInformationAlgo1 b = new MacInformationAlgo1(pointlocation2, w1, signal2);
        MacInformationAlgo1 c = new MacInformationAlgo1(pointlocation3, w2, signal3);
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
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
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
        MacInformationAlgo1 a = new MacInformationAlgo1(pointlocation1, w, signal1);
        MacInformationAlgo1 b = new MacInformationAlgo1(pointlocation2, w1, signal2);
        MacInformationAlgo1 c = new MacInformationAlgo1(pointlocation3, w2, signal3);
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
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
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
        MacInformationAlgo1 a = new MacInformationAlgo1(pointlocation1, w, signal1);
        MacInformationAlgo1 b = new MacInformationAlgo1(pointlocation2, w1, signal2);
        MacInformationAlgo1 c = new MacInformationAlgo1(pointlocation3, w2, signal3);
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
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
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
        MacInformationAlgo1 a = new MacInformationAlgo1(pointlocation1, w, signal1);
        MacInformationAlgo1 b = new MacInformationAlgo1(pointlocation2, w1, signal2);
        MacInformationAlgo1 c = new MacInformationAlgo1(pointlocation3, w2, signal3);
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
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
        GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
        Mac mac1 = new Mac(macName, array, date);
    }

    /**
     * This method check that we got the LineAlgo1 of the mac
     */
    @Test
    public void getLineAlgo1Test() {
        String macName = "ndk";
        ArrayList<MacInformation> array = new ArrayList<MacInformation>();
        GregorianCalendar date = new GregorianCalendar(2016,23,45,12,45,01);
        Mac mac1 = new Mac(macName, array, date);
        int index = 87;
        String ssid = "kjh";
        int frequency = 36485;
        double signal = 987.0;
        EarthCoordinate localisation = new EarthCoordinate(34.7,32.6,9.0);
    }

    /**
     * This method check that we got the ArrayListWithFinalNumber of the mac
     */
    @Test
    public void defineArrayListWithFinalNumberTest() {
        ArrayList<MacInformation> array2 = new ArrayList<MacInformation>();
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
        MacInformationAlgo1 a = new MacInformationAlgo1(pointlocation1, w, signal1);
        MacInformationAlgo1 b = new MacInformationAlgo1(pointlocation2, w1, signal2);
        MacInformationAlgo1 c = new MacInformationAlgo1(pointlocation3, w2, signal3);
        MacInformationAlgo2 d = new MacInformationAlgo2(pointlocation1,null,  pi);
        MacInformationAlgo2 e = new MacInformationAlgo2(pointlocation2,null,  pi1);
        array2.add(a);
        array2.add(b);
        array2.add(c);
        array2.add(d);
        array2.add(e);
        Mac mac = new Mac(array2);
        ArrayList<MacInformation> array3 = mac.defineArrayListWithFinalNumber(array2);
        array2.remove(4);
        assertEquals(array2, array3);
    }

}