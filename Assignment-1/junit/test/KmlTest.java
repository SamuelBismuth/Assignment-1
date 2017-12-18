/**
 * 
 */
package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import libraries.ReadFolder;
import read.CsvFile;
import read.ReadCombo;
import read.ReadCsv;
import read.ReadFile;
import read.ReadWigleWifi;
import read.SampleScan;
import read.SortWigleWifiTime;
import read.Wifi;
import read.WigleWifiLine;
import write.WriteCombo;
import write.WriteFile;

/**
 * @author Samuel
 *
 */
public class KmlTest {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		//Get workspace path
		String folderPathWorkspace = new File(".").getAbsolutePath();

		//Initialize the array
		ArrayList<CsvFile> arrayCsv = new ArrayList<CsvFile>();
		ArrayList<SampleScan> arrayScan =  new ArrayList<SampleScan>();

		//  ==============================
		//  = BEGINNING OF THE ALGORITHM =
		//  ==============================

		///////////////////////////////////////
		//First part : Writting the csv file.//
		///////////////////////////////////////

		//Get folder path
		System.out.println("Input the name of the folder please : ");
		String folderName = new Scanner(System.in).nextLine();
		String folderPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);

		//Read the csv file
		File[] listOfFile = ReadFolder.read(folderName);
		ReadFile<WigleWifiLine> read;
		for (File file : listOfFile) {
			read = new ReadWigleWifi(folderPath, arrayCsv, file);
			read.readBuffer();
		}

		//Sort Csv (time)
		SortWigleWifiTime sortScan = new SortWigleWifiTime();
		arrayScan = sortScan.sortBy(arrayCsv);

		//Write Csv
		WriteFile<SampleScan> write = new WriteCombo("TestCombo");
		write.receiveData(arrayScan);
		
		////////////////////////////////////////
		//Second part : Writting the kml file.//
		////////////////////////////////////////

		//Read the combo
		File combo = new File("TestCombo.csv");
		ArrayList<SampleScan> arrayScan2 = new ArrayList<SampleScan>();
		ReadCsv<SampleScan> readCombo = new ReadCombo(folderPath, arrayScan2, combo);
		readCombo.readBuffer();

		for (SampleScan scan : arrayScan2) {
			System.out.print(scan.toString() + " ### ");
			for (Wifi wifi : scan.getArrayStrongerWifi()) {
				System.out.print(wifi.toString()+ " ### ");
			}
			System.out.println();
		}

		//Write Csv
		WriteFile<SampleScan> write2 = new WriteCombo("TestCombo2");
		write2.receiveData(arrayScan);
	}

}
