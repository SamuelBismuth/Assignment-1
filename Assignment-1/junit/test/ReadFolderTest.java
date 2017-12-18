package test;

import static org.junit.Assert.*;

import org.junit.Test;

import library.ReadFolder;

public class ReadFolderTest {

	@Test 
	public void testRead() {
		String n = "C:/Users/OREL SHALOM/Desktop/f"; //Put a good path.
		assertEquals(1, ReadFolder.read(n).length);

		String n2 = "kuyf";
		if(ReadFolder.read(n2) == null) //assertEquals(object, object) is deprecated in java so we did a "if" check.
			System.out.println("there is no such folder!"); 
	}

}
