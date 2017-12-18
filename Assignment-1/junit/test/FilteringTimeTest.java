package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment.FilteringKmlTime;
import assignment.InputException;
import assignment.Mac;
import assignment.MacLocation;
import assignment.Scan;
import assignment.Wifi;

public class FilteringTimeTest {

	/**
	 * There is no assert equals because everything depends of the user input.
	 * If the user input a good data so, the programe keep runing, else, we created an exception.
	 */
	@Test
	public void testFilteringBy() {
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi("easyName", "easyMac", 5000, -90);
		ArrayList<Wifi> wifis = new ArrayList<Wifi>();
		wifis.add(wifi);
		Scan scan = new Scan(date, "easyId", earth, wifis);
		ArrayList<Scan> array = new ArrayList<Scan>();
		array.add(scan);
		EarthCoordinate tlv = new EarthCoordinate(34.78176760979483,32.08529989645831, 0.0);//TLV
		String macname = "easyMac";
		int signal = -90;
		MacLocation ml1 = new MacLocation(tlv, signal);
		ArrayList<MacLocation> array2 = new ArrayList<MacLocation>();
		array2.add(ml1);
		Mac m = new Mac(macname, array2);
		ArrayList<Mac> macs = new ArrayList<Mac>();
		macs.add(m);
		
		FilteringKmlTime filter = new FilteringKmlTime();
		try {
			filter.filteringBy(array,macs);
		} 
		catch (InputException ex) {
			ex.printStackTrace();
		}
	}
	
}
