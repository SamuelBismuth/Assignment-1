package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import assignment.InputException;
import assignment.ReadFolder;
import assignment.Scan;
import p.SampleAlgo1;
import p.WriteComboAlgo1;

public class ReadFolderTest {

	@Test 
	public void testRead() {
		String fe = "C:/Users/OREL SHALOM/Desktop/f/test.csv";
		String n = "C:/Users/OREL SHALOM/Desktop/f";
		ReadFolder readFolder = new ReadFolder();
		assertEquals(1, readFolder.read(n).length);

		String n2 = "kuyf";
		if(readFolder.read(n2) == null) //assertEquals(object, object) is deprecated in java so we did a "if" check.
			System.out.println("there is no such folder!"); 
	}

}
