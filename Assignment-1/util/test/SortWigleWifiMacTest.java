package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import algorithm1.Mac;
import library.ReadFolder;
import read.CsvFile;
import read.ReadWigleWifi;
import read.SortWigleWifiMac;

/**
 * @author Orel and Samuel.
 *
 */
public class SortWigleWifiMacTest {

	/**
	 * Test method for {@link read.SortWigleWifiMac#sortBy()}.
	 */
	@Test
	public void testSortBy() {
		ArrayList<CsvFile> array = new ArrayList<CsvFile>();
		String fileName = ReadFolder.getAbsolutePath();
		File file = new File("WigleWifi");
		ReadWigleWifi readWigleWifi = new ReadWigleWifi(fileName, array, file);
		readWigleWifi.readBuffer();
		ArrayList<Mac> arrayMac = new ArrayList<Mac>();
		SortWigleWifiMac sort = new SortWigleWifiMac();
		arrayMac = sort.sortBy(array);
		assertEquals(632,  arrayMac.size());
	}

}
