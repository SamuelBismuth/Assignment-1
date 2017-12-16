package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import library.ReadFolder;
import read.ReadCombo;
import read.SampleScan;
import read.Wifi;

/**
 * @author Orel and Samuel
 *
 */
public class ReadComboTest {

	/**
	 * Test method for {@link read.ReadCombo#readBuffer()}.
	 * Test for boaz's combo, boaz's combo no gps, and our files
	 */
	@Test
	public void testReadBuffer() {
		//Our combo
		ArrayList<SampleScan> array = new ArrayList<SampleScan>();
		String fileName = ReadFolder.getAbsolutePath();
		File file = new File("SortedTable.csv");
		ReadCombo readCombo = new ReadCombo(fileName, array, file);
		readCombo.readBuffer();
		for (SampleScan scan : array) {
			System.out.print(scan.toString() + " ### ");
			for (Wifi wifi : scan.getArrayStrongerWifi()) {
				System.out.print(wifi.toString()+ " ### ");
			}
			System.out.println();
		}
		assertEquals(362, array.size());
		//Boaz combo
		array.clear();
		file = new File("combo.csv");
		readCombo = new ReadCombo(fileName, array, file);
		readCombo.readBuffer();
		assertEquals(347, array.size());
		//Boaz combo no gps
		array.clear();
		file = new File("comboNoGps.csv");
		readCombo = new ReadCombo(fileName, array, file);
		readCombo.readBuffer();
		assertEquals(33, array.size());
	}

}
