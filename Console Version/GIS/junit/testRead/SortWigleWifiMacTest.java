package testRead;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import libraries.ReadFolder;
import objects.Mac;
import read.ReadCombo;
import read.SampleScan;
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
		ArrayList<SampleScan> array = new ArrayList<SampleScan>();
		String fileName = ReadFolder.getAbsolutePath();
		File file = new File("Combo.csv");
		ReadCombo readCombo = new ReadCombo(fileName, array, file);
		readCombo.readBuffer();
		ArrayList<Mac> arrayMac = new ArrayList<Mac>();
		SortWigleWifiMac sort = new SortWigleWifiMac();
		arrayMac = sort.sortBy(array);
		assertEquals(591,  arrayMac.size());
	}

}
