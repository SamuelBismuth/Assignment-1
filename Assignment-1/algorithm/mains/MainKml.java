package mains;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import filter.Filtering;
import libraries.InputException;
import libraries.OpenFile;
import libraries.ReadFolder;
import libraries.UserChoice;
import read.CsvFile;
import read.ReadCombo;
import read.ReadCsv;
import read.ReadFile;
import read.ReadWigleWifi;
import read.SampleScan;
import read.SortWigleWifiTime;
import read.WigleWifiLine;
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
public class MainKml {

	/**
	 * The main method.
	 * To run the project, you need to put the folder into you workspace.
	 * And also import the two api's.
	 * @See NOTICE for more details.
	 * @param args.
	 * TODO : For threads, one for the csv sorted, one for the kml, and two for the two algorithm. (in function of the algorithms).
	 * TODO : junit
	 * TODO : display wait message.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {

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
		String fileNameCombo = UserChoice.getFileName("csv file");
		WriteFile<SampleScan> write = new WriteCombo(fileNameCombo);
		write.receiveData(arrayScan);

		//Open the file
		OpenFile.open(fileNameCombo + ".csv");

		////////////////////////////////////////
		//Second part : Writting the kml file.//
		////////////////////////////////////////

		//Read the combo
		File combo = new File(fileNameCombo+ ".csv");
		arrayScan = new ArrayList<SampleScan>();
		ReadCsv<SampleScan> readCombo = new ReadCombo(folderPath, arrayScan, combo);
		readCombo.readBuffer();
		
		//Choice of the user
		Filtering<SampleScan> filter = UserChoice.userChoice();

		//Filtering kml
		try {
			write = filter.filteringBy(arrayScan);
		} 
		catch (InputException e) {
			e.printStackTrace();
		}

		//Write kml
		write.receiveData(arrayScan);

		//Open the file
		OpenFile.open(write.getFileName());
	}
	
}
