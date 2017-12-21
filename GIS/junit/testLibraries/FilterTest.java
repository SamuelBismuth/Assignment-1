package testLibraries;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.junit.Test;

import libraries.Filter;
import read.SampleScan;
import read.Wifi;

public class FilterTest {

	@Test
	public void test() {
		ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
		ArrayList<Wifi> arrayWifi1 = new ArrayList<Wifi>();

		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi2 = new Wifi("wifi2", "mac3", 5000, -10);
		Wifi wifi3 = new Wifi("wifi2", "mac3", 5000, -90);

		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		arrayWifi.add(wifi2);

		arrayWifi1.add(wifi3);
		
	
		
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		SampleScan scan2 = new SampleScan(date, id, earth, arrayWifi1);
		
		ArrayList<SampleScan> sc = new ArrayList<SampleScan>();
		sc.add(scan1);
		sc.add(scan2);
		Filter.removeDuplicateMac(sc);
		
		assertEquals(0, sc.get(1).getArrayStrongerWifi().size());
		}

//	@Test
//	public void testFromScanToCsv() {
//		String mac = "dfg";
//		String ssid = "ert"; 
//		String authMode = "dfg";
//		String firstseen = "er";
//		String channel = "hg";
//		String rssi = "456";
//		String currentLatitude = "09";
//		String currentLongitude = "65";
//		String altitudeMeters = "rte";
//		String accuracyMeters = "908";
//		String type = "987";
//		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id); 
//		ArrayList<WigleWifiLine> lines = new ArrayList<WigleWifiLine>();
//		lines.add(ln);
//		CsvFile csv = new CsvFile(id, lines);
//		ArrayList<CsvFile> cf = new ArrayList<CsvFile>();
//		cf.add(csv);
//	}
}
