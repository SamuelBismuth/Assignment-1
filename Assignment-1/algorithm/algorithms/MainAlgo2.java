package algorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import library.Algorithm2;
import library.OpenFile;
import read.ReadCombo;
import read.ReadCsv;
import read.SampleScan;
import write.WriteCombo;
import write.WriteFile;

public class MainAlgo2 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		//////////////////////////////
		//Fourth part : Algorithm 2.//
		//////////////////////////////

		//Get workspace path
		String folderPathWorkspace = new File(".").getAbsolutePath();

		//Initialize the array
		ArrayList<SampleScan> arrayInput =  new ArrayList<SampleScan>();
		ArrayList<SampleScan> arrayDataScan =  new ArrayList<SampleScan>();
		ArrayList<WeigthAverage> arrayData =  new ArrayList<WeigthAverage>();

		//Get file input path
		System.out.println("Input the name of the combo input file please :");
		String fileInput = new Scanner(System.in).nextLine();
		String fileInputPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);

		//Read the input file
		File inputFile = new File(fileInput + ".csv");
		ReadCsv<SampleScan> readInput = new ReadCombo(fileInputPath, arrayInput, inputFile);
		readInput.readBuffer();

		//Get file data path
		System.out.println("Input the name of the combo Data file please :");
		String fileData = new Scanner(System.in).nextLine();
		String fileDataPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);

		//Read the database file
		File dataFile = new File(fileData + ".csv");
		ReadCsv<SampleScan> readData = new ReadCombo(fileDataPath, arrayDataScan, dataFile);
		readData.readBuffer();

		for (SampleScan data : arrayDataScan)
			arrayData.add(new WeigthAverage(data));

		//Set RelevantNumber
		for (SampleScan input : arrayInput) {
			Algorithm2.setArrayRelevantNumber(input, arrayData);
			Algorithm2.setArrayPi(input, arrayData);
			Algorithm2.setLocation(input, arrayData);
		}

		//Write the arrayScan
		System.out.println("Input a name for the csv file you want to create : ");
		String fileName = new Scanner(System.in).nextLine();
		WriteFile<SampleScan> write = new WriteCombo(fileName);
		write.receiveData(arrayInput);

		//Open the Csv
		OpenFile.open(write.getFileName());
	}

}
