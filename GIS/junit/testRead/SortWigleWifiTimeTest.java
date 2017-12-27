package testRead;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import cast.CastFromCsvFileToSampleScan;
import libraries.ReadFolder;
import objects.CsvFile;
import objects.SampleScan;
import read.ReadWigleWifi;

/**
 * @author Samuel
 *
 */
public class SortWigleWifiTimeTest {

	/**
	 * Test method for {@link cast.CastFromCsvFileToSampleScan#sortBy()}.
	 */
	@Test
	public void testSortBy() {
		ArrayList<CsvFile> array = new ArrayList<CsvFile>();
		String fileName = ReadFolder.getAbsolutePath();
		File file = new File("WigleWifi");
		ReadWigleWifi readWigleWifi = new ReadWigleWifi(fileName, array, file);
		readWigleWifi.readBuffer();
		ArrayList<SampleScan> arrayScan = new ArrayList<SampleScan>();
		CastFromCsvFileToSampleScan sort = new CastFromCsvFileToSampleScan();
		arrayScan = sort.sortBy(array);
		assertEquals(200,  arrayScan.size());
	}

}
