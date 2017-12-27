package testRead;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import cast.CastFromCsvFileToMac;
import libraries.ReadFolder;
import objects.Mac;
import objects.SampleScan;
import read.ReadCombo;

/**
 * @author Orel and Samuel.
 *
 */
public class SortWigleWifiMacTest {

	/**
	 * Test method for {@link cast.CastFromCsvFileToMac#sortBy()}.
	 */
	@Test
	public void testSortBy() {
		ArrayList<SampleScan> array = new ArrayList<SampleScan>();
		String fileName = ReadFolder.getAbsolutePath();
		File file = new File("Combo.csv");
		ReadCombo readCombo = new ReadCombo(fileName, array, file);
		readCombo.readBuffer();
		ArrayList<Mac> arrayMac = new ArrayList<Mac>();
		CastFromCsvFileToMac sort = new CastFromCsvFileToMac();
		arrayMac = sort.sortBy(array);
		assertEquals(591,  arrayMac.size());
	}

}
