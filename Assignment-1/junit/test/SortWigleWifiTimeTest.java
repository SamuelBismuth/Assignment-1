package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import libraries.ReadFolder;
import read.CsvFile;
import read.ReadWigleWifi;
import read.SampleScan;
import read.SortWigleWifiTime;

/**
 * @author Samuel
 *
 */
public class SortWigleWifiTimeTest {

	/**
	 * Test method for {@link read.SortWigleWifiTime#sortBy()}.
	 */
	@Test
	public void testSortBy() {
		ArrayList<CsvFile> array = new ArrayList<CsvFile>();
		String fileName = ReadFolder.getAbsolutePath();
		File file = new File("WigleWifi");
		ReadWigleWifi readWigleWifi = new ReadWigleWifi(fileName, array, file);
		readWigleWifi.readBuffer();
		ArrayList<SampleScan> arrayScan = new ArrayList<SampleScan>();
		SortWigleWifiTime sort = new SortWigleWifiTime();
		arrayScan = sort.sortBy(array);
		assertEquals(200,  arrayScan.size());
	}

}
