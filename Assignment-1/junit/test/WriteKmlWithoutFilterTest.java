package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment.Mac;
import assignment.MacLocation;
import assignment.Scan;
import assignment.Wifi;
import assignment.WriteKmlWithoutFilter;

public class WriteKmlWithoutFilterTest {

	@Test 
	public void testCheckData() {
		ArrayList<Scan> array = new ArrayList<Scan>();
		ArrayList<Wifi> wifis = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("easyName", "easyMac", 5000, -70);
		Wifi wifi2 = new Wifi("easyName", "easyMac", 5000, -90);
		Wifi wifi3 = new Wifi("easyName", "easyMac", 5000, -80);
		wifis.add(wifi);
		wifis.add(wifi2);
		wifis.add(wifi3);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Scan scan = new Scan (date, "easyId", earth, wifis);
		GregorianCalendar date2 = new GregorianCalendar(2016, 8, 9, 2, 9, 10);
		Scan scan2 = new Scan(date2, "easyId", earth, wifis);
		Scan scan3 = new Scan(date2, "easyId", earth, wifis);
		array.add(scan);
		array.add(scan2);
		array.add(scan3);
		String macname = "easyMac";
		int signal = -90;
		MacLocation ml1 = new MacLocation(earth, signal);
		ArrayList<MacLocation> array2 = new ArrayList<MacLocation>();
		array2.add(ml1);
		Mac m = new Mac(macname, array2);
		ArrayList<Mac> macs = new ArrayList<Mac>();
		macs.add(m);
		WriteKmlWithoutFilter wrt = new WriteKmlWithoutFilter(macs);
		wrt.checkData(array, "123"); // Must print everything i.e : 3 placemarks.
	}

}
