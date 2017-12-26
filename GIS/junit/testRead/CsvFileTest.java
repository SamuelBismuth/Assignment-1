package testRead;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import objects.CsvFile;
import objects.WigleWifiLine;

public class CsvFileTest {

	/**
	 * This method check we can get the id of the CsvFile
	 */
	@Test
	public void getIdTest() {
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
	}
	
	/**
	 * This method check we can get the ArrayList of the CsvFile
	 */
	@Test
	public void getWigleWifiLineTest(){
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
		assertEquals(lines, csv.getWigleWifiLine());
	}

}
