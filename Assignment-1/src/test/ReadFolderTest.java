package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import assignment1.ReadFolder;
import assignment1.Scan;

public class ReadFolderTest {

	@Test 
	public void testRead() {
		String folderName = "C:/Users/Samuel/workspace/Assignment 1/csvFiles";
		ArrayList<Scan> array = new ArrayList<Scan>();
		ReadFolder readFolder = new ReadFolder(array);
		readFolder.read(folderName);
		assertEquals(2169, array.size());
		array.clear();
		String folderName2 = "C:/Users/Samuel/workspace/Assignment 1/csvFiles9";
		ReadFolder readFolder2 = new ReadFolder(array);
		readFolder2.read(folderName2);
		assertEquals(0, array.size());
	}

}
