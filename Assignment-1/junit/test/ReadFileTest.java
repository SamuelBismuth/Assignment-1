package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import assignment1.ReadFile;
import assignment1.Scan;

public class ReadFileTest {

	@Test
	public void testReadFile() {
		String folderName = "C:/Users/Samuel/workspace/Assignment 1/csvFiles";
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		ArrayList<SampleScan> array = new ArrayList<SampleScan>();
		ReadWigleWifi readFile = new ReadWigleWifi(listOfFiles[1], array);
		readFile.read(folderName);
		assertEquals(343, array.size());
		array.clear();
		ReadWigleWifi readFile2 = new ReadWigleWifi(listOfFiles[0], array);
		readFile2.read(folderName);
		assertEquals(0, array.size());
		array.clear();
		ReadWigleWifi readFile3 = new ReadWigleWifi(listOfFiles[2], array);
		readFile3.read(folderName);
		assertEquals(811, array.size());
	}	

}
