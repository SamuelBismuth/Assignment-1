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
	 * TODO : Generic (check the warning : is there anothere way ?)
	 * TODO : Question about the two types of classes : is there one class (road of the algorithm) need to be static ?
	 * TODO : stam
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		//Get path
		String folderPathWorkspace = new File(".").getAbsolutePath();
		System.out.println("Input the name of the folder please :");
		String folderName = new Scanner(System.in).nextLine();
		String folderPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1) + folderName;

		//Initialize the array
		ArrayList<CsvFile> arrayCsv = new ArrayList<CsvFile>();
		ArrayList<Scan> arrayScan =  new ArrayList<Scan>();
		ArrayList<Mac> arrayMac = new ArrayList<Mac>();

		//  ==============================
		//  = BEGINNING OF THE ALGORITHM =
		//  ==============================

		///////////////////////////////////////
		//First part : Writting the csv file.//
		///////////////////////////////////////

		//Read the csv file
		Read rd = new ReadFolder(arrayCsv);
		rd.read(folderPath);

		//Filtering Csv (time)
		SortCsvTime filterTimecsv = new SortCsvTime();
		arrayScan = filterTimecsv.sortBy(arrayCsv);

		//Write Csv
		System.out.println("Input a name for the csv file you want to create : ");
		String fileNameCsvExport = new Scanner(System.in).nextLine() + ".csv";
		WriteFile write = new WriteCsv(fileNameCsvExport);
		write.checkData(arrayScan, fileNameCsvExport);

		new OpenFile(fileNameCsvExport); // Open the file.

		///////////////////////////////////////
		//Second part : Writting the kml file.//
		///////////////////////////////////////

		//Choice of the user (kml).
		UserChoice choice = new UserChoiceKml();
		Filtering filter = null;
		try {
			filter = choice.userChoice();
		} catch (InputException e2) {
			e2.printStackTrace();
		}

		//Filtering csv (kml)
		try {
			write =  filter.filteringBy(arrayScan);
		} 
		catch (InputException e) {
			e.printStackTrace();
		}

		//Write kml
		System.out.println("Input a name for the kml file you want to create : ");
		String fileNameKmlExport = new Scanner(System.in).nextLine() + ".kml";
		write.checkData(arrayScan, fileNameKmlExport);

		new OpenFile(fileNameKmlExport); // Open the file.

		/////////////////////////////
		//Third part : Algorithm 1.//
		/////////////////////////////
		
		//Filtering csv
		SortCsvMac filterMaccsv = new SortCsvMac();
		arrayMac = filterMaccsv.sortBy(arrayCsv);
		
		//Choice of the user (mac).
		choice = new UserChoiceMac(arrayMac);
		try {
			filter = choice.userChoice();
		} 
		catch (InputException e1) {
			e1.printStackTrace();
		}
		
		//Filtering csv (kml)
		try {
			filter.filteringBy(arrayMac);
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
	}

}
