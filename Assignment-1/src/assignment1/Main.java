package assignment1;

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
	 * TODO : For threads, one for the csv sorted, one for the kml, and two for the two algorithm.
	 * TODO : junit
	 * TODO : check radius + MAC 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String folderPathWorkspace = new File(".").getAbsolutePath();
		System.out.println("Input the name of the folder please :");
		String folderName = new Scanner(System.in).nextLine();
		String folderPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1) + folderName;
		ArrayList<CsvFile> arrayCsv = new ArrayList<CsvFile>();
		
		//Beginning the algorithm.

		//Read
		Read rd = new ReadFolder(arrayCsv);
		rd.read(folderPath);
		
		//Filtering Csv
		FilteringCsvTime filterTimecsv = new FilteringCsvTime();
		ArrayList<Scan> array = (ArrayList<Scan>) filterTimecsv.filteringBy(arrayCsv).clone();
		FilteringCsvMac filterMaccsv = new FilteringCsvMac();
		ArrayList<Mac> arrayMac = (ArrayList<Mac>) filterMaccsv.filteringBy(arrayCsv).clone();
		
		//Write Csv
		System.out.println("Input a name for the csv file you want to create : ");
		String fileNameCsvExport = new Scanner(System.in).nextLine() + ".csv";
		WriteFile write = new WriteCsv(fileNameCsvExport);
		write.checkData(array, fileNameCsvExport);
		new OpenFile(fileNameCsvExport); // Open the file.


		//Choice of the user kml.
		UserChoice user = new UserChoiceKml();
		FilteringKml filter =  user.userChoice();
		
		//Filtering kml
		try {
			write =  filter.filteringBy(array);
		} 
		catch (InputException e) {
			e.printStackTrace();
		}
		
		//Write kml
		System.out.println("Input a name for the kml file you want to create : ");
		String fileNameKmlExport = new Scanner(System.in).nextLine() + ".kml";
		write.checkData(array, fileNameKmlExport);
		new OpenFile(fileNameKmlExport); 
	}
	
}
