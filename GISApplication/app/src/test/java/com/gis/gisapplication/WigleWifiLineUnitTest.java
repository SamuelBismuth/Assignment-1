package com.gis.gisapplication;

import static org.junit.Assert.*;
import org.junit.Test;

import objects.WigleWifiLine;

public class WigleWifiLineUnitTest {

	/**
	 * This method check if we got the Mac of the WigleWifiLine
	 */
	@Test
	public void getMacTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id); 
		assertEquals(mac, ln.getMac());
	}
	
	/**
	 * This method check if we got the Ssid of the WigleWifiLine
	 */
	@Test
	public void getSsidTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(ssid, ln.getSsid());
	}
	
	/**
	 * This method check if we got the AuthMode of the WigleWifiLine
	 */
	@Test
	public void getAuthModeTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(authMode, ln.getAuthMode());
	}
	
	/**
	 * This method check if we got the Firstseen of the WigleWifiLine
	 */
	@Test
	public void getFirstseenTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(firstseen, ln.getFirstseen());
	}
	
	/**
	 * This method check if we got the Channel of the WigleWifiLine
	 */
	@Test
	public void getChannelTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(channel, ln.getChannel());
	}
	
	/**
	 * This method check if we got the Rssi of the WigleWifiLine
	 */
	@Test
	public void getRssitest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(rssi, ln.getRssi());
	}
	
	/**
	 * This method check if we got the CurrentLatitude of the WigleWifiLine
	 */
	@Test
	public void getCurrentLatitudetest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(currentLatitude, ln.getCurrentLatitude());
	}
	
	/**
	 * This method check if we got the CurrentLongitude of the WigleWifiLine
	 */
	@Test
	public void getCurrentLongitudeTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(currentLongitude, ln.getCurrentLongitude());
	}
	
	/**
	 * This method check if we got the AltitudeMeters of the WigleWifiLine
	 */
	@Test
	public void getAltitudeMetersTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(altitudeMeters, ln.getAltitudeMeters());
	}
	
	/**
	 * This method check if we got the AccuracyMeters of the WigleWifiLine
	 */
	@Test
	public void getAccuracyMetersTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(accuracyMeters, ln.getAccuracyMeters());
	}
	
	/**
	 * This method check if we got the type of the WigleWifiLine
	 */
	@Test
	public void getTypeTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(type, ln.getType());
	}
	
	/**
	 * This method check if we got the id of the WigleWifiLine
	 */
	@Test
	public void getIdTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(id, ln.getId());
	}
	
	/**
	 * This method check the compare of the mac of the WigleWifiLine
	 */
	@Test
	public void compareToTest() {
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
		String id = "net";
		WigleWifiLine ln = new WigleWifiLine(mac, ssid, authMode, firstseen, channel, rssi, currentLatitude, currentLongitude, altitudeMeters, accuracyMeters, type,id);
		assertEquals(0, ln.compareTo(ln));
	}

}
