package com.gis.gisapplication;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import libraries.ReadFolder;
import objects.CsvFile;
import read.ReadWigleWifi;

/**
 * @author Orel and Samuel
 *
 */
public class ReadWigleWifiUnitTest {

	/**
	 */
	@Test
	public void testRead() {
		ArrayList<CsvFile> array = new ArrayList<CsvFile>();
		String fileName = ReadFolder.getAbsolutePath();
		File file = new File("WigleWifi.csv");
		ReadWigleWifi readWigleWifi = new ReadWigleWifi(fileName, array, null);
		readWigleWifi.readBuffer();
		assertEquals(1, array.size());
		assertEquals(1319, array.get(0).getWigleWifiLine().size());
	}

	/**
	 * Test method for ReadWigleWifi.getId.
	 */
	@Test
	public void testGetId() {
		String firstLine = "WigleWifi-1.4,appRelease=2.25,model=Lenovo PB2-690Y,release=6.0.1,"
				+ "device=PB2PRO,display=PB2-690Y_S200032_161214,board=msm8952,brand=Lenovo";
		assertEquals("PB2-690Y_S200032_161214", ReadWigleWifi.getId(firstLine));
	}
	
	/**
	 * Test method for ReadWigleWifi.checkTheFile.
	 */
	/*@Test
	public void testCheckTheFile() {
		String firstLine = "WigleWifi-1.4,appRelease=2.25,model=Lenovo PB2-690Y,release=6.0.1,"
				+ "device=PB2PRO,display=PB2-690Y_S200032_161214,board=msm8952,brand=Lenovo";
		assertTrue(ReadWigleWifi.checkTheFile(firstLine));
		firstLine = ",appRelease=2.25,model=Lenovo PB2-690Y,release=6.0.1,"
				+ "device=PB2PRO,display=PB2-690Y_S200032_161214,board=msm8952,brand=Lenovo";
		assertFalse(ReadWigleWifi.checkTheFile(firstLine));
	}*/
}
