package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import assignment.Mac;
import assignment.MacLocation;
import assignment.Scan;
import assignment.Wifi;
import write.WriteKmlTime;

public class WriteKmlTimeTest {
	
	@Test 
	public void testCheckData() {
		GregorianCalendar date = new GregorianCalendar(2016, 8, 12, 22, 8, 10);
		GregorianCalendar date2 = new GregorianCalendar(2017, 8, 9, 2, 9, 10);
		GregorianCalendar date3 = new GregorianCalendar(2018, 8, 9, 2, 9, 10);
		ArrayList<Scan> array = new ArrayList<Scan>();
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		Wifi wifi = new Wifi("easyName", "easyMac", 5000, -70);
		Wifi wifi3 = new Wifi("easyName", "easyMac", 5000, -80);
		Wifi wifi2 = new Wifi("easyName", "easyMac", 5000, -90);
		ArrayList<Wifi> wifis = new ArrayList<Wifi>();
		wifis.add(wifi);
		wifis.add(wifi2);
		wifis.add(wifi3);
		Scan scan = new Scan (date, "easyId", earth, wifis);
		Scan scan2 = new Scan(date2, "easyId", earth, wifis);
		Scan scan3 = new Scan(date3, "easyId", earth, wifis);
		array.add(scan);
		array.add(scan2);
		array.add(scan3);
		EarthCoordinate tlv = new EarthCoordinate(34.78176760979483,32.08529989645831, 0.0);//TLV
		EarthCoordinate jru = new EarthCoordinate(35.21371001267917,31.76831900367241, 0.0);//JRU
		String macname = "easyMac";
		int signal = -90;
		MacLocation ml1 = new MacLocation(tlv, signal);
//		MacLocation ml2 = new MacLocation(jru, signal);
		ArrayList<MacLocation> array2 = new ArrayList<MacLocation>();
		array2.add(ml1);
//		array.add(ml2);
		Mac m = new Mac(macname, array2);
		ArrayList<Mac> macs = new ArrayList<Mac>();
		macs.add(m);
		WriteKmlTime wrt = new WriteKmlTime(date, date2,macs);
		WriteKmlTime wrt2 = new WriteKmlTime(date, date3,macs);
		wrt2.checkData(array,"123"); /// The kml file must have 2 placemark.
		wrt.checkData(array,"456"); //InputException : there is no placemark on the document.
	}
	
}
