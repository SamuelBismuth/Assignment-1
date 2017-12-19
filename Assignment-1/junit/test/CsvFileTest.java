package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import read.CsvFile;
import read.WigleWifiLine;

public class CsvFileTest {

	/**
	 * This method check if the functions of the object CsvFile are working.
	 */
	@Test
	public void test() {
		String id = "net";
		String mac = "dfg";
		String ssid = "ert"; 
		String authMode = "dfg";
		String firstseen = "er";
		String channel = "hg";
		String rssi = "456";
		String currentLatitude = "09";
		String currentLongitude = "65";
		String altitudeMeters = "rte";
		String accuracyMeters = "908";
		String type = "987";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id); 
		ArrayList<WigleWifiLine> lines = new ArrayList<WigleWifiLine>();
		lines.add(ln);
		CsvFile csv = new CsvFile(id, lines);
		assertEquals(id, csv.getId());
		assertEquals(lines, csv.getWigleWifiLine());
	}

}
