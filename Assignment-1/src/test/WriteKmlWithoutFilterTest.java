package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment1.Scan;
import assignment1.Wifi;
import assignment1.WriteKmlWithoutFilter;

public class WriteKmlWithoutFilterTest {

	@Test 
	public void testCheckData() {
		ArrayList<Scan> array = new ArrayList<Scan>();
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi("easyName", "easyMac", 5000, -70);
		Scan scan = new Scan (date, "easyId", earth, wifi);
		GregorianCalendar date2 = new GregorianCalendar(2016, 8, 9, 2, 9, 10);
		Wifi wifi2 = new Wifi("easyName", "easyMac", 5000, -90);
		Scan scan2 = new Scan(date2, "easyId", earth, wifi2);
		Wifi wifi3 = new Wifi("easyName", "easyMac", 5000, -80);
		Scan scan3 = new Scan(date2, "easyId", earth, wifi3);
		array.add(scan);
		array.add(scan2);
		array.add(scan3);
		WriteKmlWithoutFilter wrt = new WriteKmlWithoutFilter();
		wrt.checkData(array); // Must print everything i.e : 3 placemarks.
	}

}
