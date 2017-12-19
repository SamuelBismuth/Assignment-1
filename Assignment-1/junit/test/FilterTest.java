package test;

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
		ArrayList<Wifi> arrayWifi2 = new ArrayList<Wifi>();
		Wifi wifi = new Wifi("wifi", "mac1", 5000, -80);
		Wifi wifi1 = new Wifi("wifi2", "mac2", 5000, -90);
		Wifi wifi2 = new Wifi("wifi2", "mac3", 5000, -10);
		Wifi wifi3 = new Wifi("wifi2", "mac4", 5000, -90);
		Wifi wifi4 = new Wifi("wifi2", "mac5", 5000, -50);
		Wifi wifi5 = new Wifi("wifi2", "mac6", 5000, -80);
		Wifi wifi6 = new Wifi("wifi2", "mac7", 5000, -90);
		Wifi wifi7 = new Wifi("wifi2", "mac8", 5000, -20);
		Wifi wifi8 = new Wifi("wifi2", "mac9", 5000, -40);
		Wifi wifi9 = new Wifi("wifi2", "mac10", 5000, -70);
		
		arrayWifi.add(wifi);
		arrayWifi.add(wifi1);
		arrayWifi.add(wifi2);
		arrayWifi.add(wifi3);
		arrayWifi.add(wifi4);
		arrayWifi.add(wifi5);
		arrayWifi.add(wifi6);
		arrayWifi.add(wifi7);
		arrayWifi.add(wifi8);
		arrayWifi.add(wifi9);
		
		arrayWifi1.add(wifi6);
		arrayWifi1.add(wifi4);
		arrayWifi1.add(wifi3);
		
		arrayWifi2.add(wifi);
		arrayWifi2.add(wifi7);
		arrayWifi2.add(wifi2);
		GregorianCalendar date = new GregorianCalendar(2017, 8, 12, 22, 00, 10);
		EarthCoordinate earth = new EarthCoordinate(100.0, 34.0, 890.0);
		String id = "id";
		SampleScan scan1 = new SampleScan(date, id, earth, arrayWifi);
		SampleScan scan2 = new SampleScan(date, id, earth, arrayWifi1);
		SampleScan scan3 = new SampleScan(date, id, earth, arrayWifi2);
		ArrayList<SampleScan> sc = new ArrayList<SampleScan>();
		sc.add(scan1);
		sc.add(scan2);
		sc.add(scan3);
		Filter.removeDuplicateMac(sc);
		assertEquals(4, sc.get(2).getArrayStrongerWifi().size());
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
