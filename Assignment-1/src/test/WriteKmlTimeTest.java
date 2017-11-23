package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment1.Wifi;
import assignment1.WriteKmlTime;

public class WriteKmlTimeTest {
	
	@Test 
	public void testCheckData() {
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		GregorianCalendar date = new GregorianCalendar(2016, 8, 12, 22, 8, 10);
		GregorianCalendar date2 = new GregorianCalendar(2017, 8, 9, 2, 9, 10);
		GregorianCalendar date3 = new GregorianCalendar(2018, 8, 9, 2, 9, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi(date, "easyId", earth, "easyName", "easyMac", 5000, -70);
		Wifi wifi2 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -90);
		Wifi wifi3 = new Wifi(date2, "easyId", earth, "easyName", "easyMac", 5000, -80);
		array.add(wifi);
		array.add(wifi2);
		array.add(wifi3);
		WriteKmlTime wrt = new WriteKmlTime(date, date2);
		WriteKmlTime wrt2 = new WriteKmlTime(date, date3);
		wrt2.checkData(array); /// The kml file must have 2 placemark.
		wrt.checkData(array); //InputException : there is no placemark on the document.
	}
	
}
