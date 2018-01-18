package testLibraries;

import static org.junit.Assert.*;

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
public class SortWigleWifiTimeUnitTest {

	/**
	 */
	@Test
	public void testSortBy() {
		ArrayList<CsvFile> array = new ArrayList<CsvFile>();
		String fileName = ReadFolder.getAbsolutePath();
		ReadWigleWifi readWigleWifi = new ReadWigleWifi(fileName, array, null);
		readWigleWifi.readBuffer();
		ArrayList<SampleScan> arrayScan = new ArrayList<SampleScan>();
		CastFromCsvFileToSampleScan sort = new CastFromCsvFileToSampleScan();
		arrayScan = sort.cast(array);
		assertEquals(200,  arrayScan.size());
	}

}
