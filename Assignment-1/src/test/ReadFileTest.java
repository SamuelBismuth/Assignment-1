package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import assignment1.ReadFile;
import assignment1.Wifi;

public class ReadFileTest {

	@Test
	public void testReadFile() {
		String folderName = "C:/Users/Samuel/workspace/Assignment 1/csvFiles";
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		ArrayList<Wifi> array = new ArrayList<Wifi>();
		ReadFile readFile = new ReadFile(listOfFiles[1], array);
		readFile.read(folderName);
		assertEquals(343, array.size());
		array.clear();
		ReadFile readFile2 = new ReadFile(listOfFiles[0], array);
		readFile2.read(folderName);
		assertEquals(0, array.size());
		array.clear();
		ReadFile readFile3 = new ReadFile(listOfFiles[2], array);
		readFile3.read(folderName);
		assertEquals(811, array.size());
	}	

}
