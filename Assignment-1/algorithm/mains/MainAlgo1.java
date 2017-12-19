package mains;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import algorithms.Mac;
import filter.Filtering;
import filter.FilteringCsvMac;
import libraries.InputException;
import libraries.OpenFile;
import read.ReadCombo;
import read.ReadCsv;
import read.SampleScan;
import read.SortWigleWifiMac;
import write.WriteFile;

public class MainAlgo1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		//Get workspace path
		String folderPathWorkspace = new File(".").getAbsolutePath();
		
		//Initialize the array
		ArrayList<SampleScan> arrayScan =  new ArrayList<SampleScan>();
		ArrayList<Mac> arrayMac = new ArrayList<Mac>();

		//  ==============================
		//  = BEGINNING OF THE ALGORITHM =
		//  ==============================

		/////////////////////////////
		//Third part : Algorithm 1.//
		/////////////////////////////
		
		//Get file combo path
		System.out.println("Input the name of the combo file please :");
		String fileComboName = new Scanner(System.in).nextLine();
		String fileComboPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);
		
		//Read the combo file
		File combo = new File(fileComboName + ".csv");
		arrayScan = new ArrayList<SampleScan>();
		ReadCsv<SampleScan> readCombo = new ReadCombo(fileComboPath, arrayScan, combo);
		readCombo.readBuffer();
		
		//Sort combo (mac)
		SortWigleWifiMac sortMac = new SortWigleWifiMac();
		arrayMac = sortMac.sortBy(arrayScan);
	
		//Filtering the combo
		Filtering<Mac> filter = new FilteringCsvMac();		
		WriteFile<Mac> write = null;
		try {
			write = filter.filteringBy(arrayMac);
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
				
		//Write the Csv
		write.receiveData(arrayMac);
		
		//Open the Csv
		OpenFile.open(write.getFileName());
	}

}
