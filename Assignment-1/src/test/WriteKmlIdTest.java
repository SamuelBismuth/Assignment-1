package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;
import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;
import assignment1.Wifi;
import assignment1.WriteKmlId;

public class WriteKmlIdTest {
	
	@Test 
	public void testCheckData() {
		WriteKmlId wrt = new WriteKmlId("notEasyId");
		WriteKmlId wrt2 = new WriteKmlId("easyId");
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi(date, "easyId", earth, "easyName", "easyMac", 5000, -70);
		GregorianCalendar date2 = new GregorianCalendar(2016, 8, 9, 2, 9, 10);
		Wifi wifi2 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -90);
		Wifi wifi3 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -80);
		array.add(wifi);
		array.add(wifi2);
		array.add(wifi3);
		wrt.checkData(array); // InputException : there is no placemark in the document
		wrt2.checkData(array); // work as we need.  
	}

	/**
	 * Here we also testing the abstract class WriteKml.
	 */
	
	@Test
 	public void testSameMac() {
		WriteKmlId wrt = new WriteKmlId("notEasyId");
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi(date, "easyId", earth, "easyName", "notEasyMac", 5000, -70);
		Wifi wifi2 = new Wifi(date, "easyIdevfe", earth, "easyNaasvdfcme", "easyMac", 5000, -90);
		Wifi wifi3 = new Wifi(date, "easyId", earth, "easyName", "easyMac", 5000, -80);
		array.add(wifi);
		array.add(wifi2);
		array.add(wifi3);
		assertEquals(false, wrt.sameMac(array, wifi3));
		assertEquals(true, wrt.sameMac(array, wifi));
 	}
	
/**
 * Here we have tested all the private method, but, we need them to be private, and so, junit test can't read private method.
 * If you need to run those tests, you need to change from private to public all the method in the Sort class.
 */
//	public void testColor() {
//		WriteKmlId wrt = new WriteKmlId("notEasyId");
//		assertEquals("#red", wrt.color(-91));
//		assertEquals("#ylw", wrt.color(-81));
//		assertEquals("#green", wrt.color(-71));
//	}
 	
}
