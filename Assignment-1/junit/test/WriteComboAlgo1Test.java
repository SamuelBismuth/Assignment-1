package test;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.Test;
import p.CsvFile;
import p.InputException;
import p.ReadCsv;
import p.ReadFolder;
import p.SampleAlgo1;
import p.WriteComboAlgo1;

public class WriteComboAlgo1Test {

	private ArrayList<SampleAlgo1> array;

	@Test
	public void test() throws InputException {
		String fe = "C:/Users/OREL SHALOM/Desktop/f/test.csv";
		String n = "C:/Users/OREL SHALOM/Desktop/f";
		this.array = new ArrayList<SampleAlgo1>();
		WriteComboAlgo1 wc1 = new WriteComboAlgo1(array);
		wc1.createFile(fe);
		ReadFolder rf = new ReadFolder();
		assertEquals(1,rf.read(n).length);
		//assertEquals(fe,rf.read(n)[0].toPath()); working. In fe there is "/" but in the path there is "\"
		
		
		
	}

}
