package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment1.Wifi;
import assignment1.WriteKmlWithoutFilter;

public class WriteKmlWithoutFilterTest {

	@Test 
	public void testCheckData() {
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		GregorianCalendar date2 = new GregorianCalendar(2016, 8, 9, 2, 9, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi(date, "easyId", earth, "easyName", "easyMac", 5000, -70);
		Wifi wifi2 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -90);
		Wifi wifi3 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -80);
		array.add(wifi);
		array.add(wifi2);
		array.add(wifi3);
		WriteKmlWithoutFilter wrt = new WriteKmlWithoutFilter();
		wrt.checkData(array); // Must print everything i.e : 3 placemarks.
	}

}
