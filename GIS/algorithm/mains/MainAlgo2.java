package mains;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import libraries.Algorithm2;
import libraries.OpenFile;
import libraries.User;
import objects.SampleScan;
import objects.WeigthAverage;
import read.ReadCombo;
import read.ReadCsv;
import write.WriteCombo;
import write.WriteFile;

/**
 * The Main class.
 * About the warnings :
 * In the book : "Introduction to Java Programming," by Liang (10th ed),
 * "Note: If you use an IDE such as Eclipse or NetBeans, 
 * you will get a warning to ask you to close the input to prevent a potential resource leak
 * Ignore the warning because the input is automatically closed when your program is terminated. 
 * In this case, there will be no resource leaking." (page 39)
 * @author Orel and Samuel.
 */
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
		String fileInput = User.getFileName("combo input");
		String fileInputPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);

		//Read the input file
		File inputFile = new File(fileInput + ".csv");
		ReadCsv<SampleScan> readInput = new ReadCombo(fileInputPath, arrayInput, inputFile);
		readInput.readBuffer();

		//Get file data path
		String fileData = User.getFileName("combo data");
		String fileDataPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);

		//Read the database file
		File dataFile = new File(fileData + ".csv");
		ReadCsv<SampleScan> readData = new ReadCombo(fileDataPath, arrayDataScan, dataFile);
		readData.readBuffer();

		for (SampleScan data : arrayDataScan) 
			arrayData.add(new WeigthAverage(data));

		ArrayList<WeigthAverage> arrayTemp = new ArrayList<WeigthAverage>();

		//Algorithm 2
		for (SampleScan input : arrayInput) {
			Algorithm2.mmeset(arrayData);
			arrayTemp = Algorithm2.setArrayRelevantNumber(input, arrayData);
			double sum = 0;
			for (WeigthAverage scan : arrayData) {
				sum += scan.getRelevantNumber();
			}
			if (sum != 0) {
				Algorithm2.setArrayPi(input, arrayTemp);
				Algorithm2.setLocation(input, arrayTemp);
			}
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
