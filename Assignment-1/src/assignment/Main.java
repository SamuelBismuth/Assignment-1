package assignment;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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

public class Main {

	/**
	 * The main method.
	 * To run the project, you need to put the folder into you workspace.
	 * And also import the two api's.
	 * @See NOTICE for more details.
	 * @param args.
	 * TODO : For threads, one for the csv sorted, one for the kml, and two for the two algorithm. (in function of the algorithms).
	 * TODO : junit
	 * TODO : check radius 
	 * TODO : STRONGER MAC (WHAT ABOUT THE ALGORITHM 1) 
	 * TODO : algo 2
	 * TODO : warning clone arrayList.
	 * TODO : send null to filtering by (algo1)
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		//Get workspace path
		String folderPathWorkspace = new File(".").getAbsolutePath();
		
		//Initialize the array
		ArrayList<CsvFile> arrayCsv = new ArrayList<CsvFile>();
		ArrayList<Scan> arrayScan =  new ArrayList<Scan>();
		ArrayList<Mac> arrayMac = new ArrayList<Mac>();

		//  ==============================
		//  = BEGINNING OF THE ALGORITHM =
		//  ==============================

//		///////////////////////////////////////
//		//First part : Writting the csv file.//
//		///////////////////////////////////////
//		
//		//Get folder path
//		System.out.println("Input the name of the folder please :");
//		String folderName = new Scanner(System.in).nextLine();
//		String folderPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1) + folderName;
//
//		//Read the csv file
//		ReadFolder rd = new ReadFolder();
//		File[] listOfFile = rd.read(folderPath);
//		Read read = new ReadCsv(folderName, arrayCsv, listOfFile);
//		
//		//Sort Csv (time)
//		SortCsv<Scan> sortScan = new SortCsvTime();
//		arrayScan = sortScan.sortBy(arrayCsv);
//		
//		//Sort Csv (mac)
//		SortCsv<Mac> sortMac = new SortCsvMac();
//		arrayMac = sortMac.sortBy(arrayCsv);
//
//		//Write Csv
//		System.out.println("Input a name for the csv file you want to create : ");
//		String fileNameCsvExport = new Scanner(System.in).nextLine() + ".csv";
//		WriteFile write = new WriteCsv(fileNameCsvExport);
//		write.checkData(arrayScan, fileNameCsvExport);
//
//		//Open the file
//		read = new OpenFile();
//		read.read(fileNameCsvExport);
//
//		////////////////////////////////////////
//		//Second part : Writting the kml file.//
//		////////////////////////////////////////
//
//		//Choice of the user (kml).
//		UserChoiceKml choice = new UserChoiceKml();
//		Filtering<Scan> filter = null;
//		filter = choice.userChoice();
//
//		//Filtering csv (kml)
//		try {
//			write =  filter.filteringBy(arrayScan, arrayMac);
//		} 
//		catch (InputException e) {
//			e.printStackTrace();
//		}
//
//		//Write kml
//		System.out.println("Input a name for the kml file you want to create : ");
//		String fileNameKmlExport = new Scanner(System.in).nextLine() + ".kml";
//		write.checkData(arrayScan, fileNameKmlExport);
//
//		new OpenFile(fileNameKmlExport); // Open the file.

		/////////////////////////////
		//Third part : Algorithm 1.//
		/////////////////////////////
		
		//Get file combo path
		System.out.println("Input the name of the combo file please :");
		String fileComboName = new Scanner(System.in).nextLine();
		String fileComboPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1) + fileComboName;
		File file = new File(fileComboPath);
		
		//Read the combo file
		Read read = new ReadCombo(fileComboName, arrayCsv, file);
		
		//Sort combo (mac)
		SortCsv<Mac> sortMac = new SortCsvMac();
		arrayMac = sortMac.sortBy(arrayCsv);
		
		//Choice of the user (mac).
		UserChoice<Mac> choice = new UserChoiceMac(arrayMac);
		Filtering<Mac> filter  = null;
		try {
			filter  = choice.userChoice();
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
		
		//Filtering the csv
		try {
			WriteFile write =  filter.filteringBy(arrayMac, null);
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
		
		//Write the Csv
		System.out.println("Input a name for the csv file you want to create : ");
		String fileNameCsvExport = new Scanner(System.in).nextLine() + ".csv";
		
		//Open the Csv
		read = new OpenFile();
		read.read(fileNameCsvExport);
	}

}
