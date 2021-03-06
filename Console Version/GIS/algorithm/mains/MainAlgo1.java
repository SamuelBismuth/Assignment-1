package mains;

import java.io.File;
import java.util.ArrayList;

import cast.CastFromMacToLineAlgo1;
import cast.CastFromCsvFileToMac;
import libraries.OpenFile;
import libraries.User;
import objects.Mac;
import objects.SampleScan;
import read.ReadCombo;
import read.ReadCsv;
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
public class MainAlgo1 {

	@SuppressWarnings("unchecked")
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
		String fileComboName = User.getFileName("combo");
		String fileComboPath = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);

		//Read the combo file
		File combo = new File(fileComboName + ".csv");
		arrayScan = new ArrayList<SampleScan>();
		ReadCsv<SampleScan> readCombo = new ReadCombo(fileComboPath, arrayScan, combo);
		readCombo.readBuffer();

		//Sort combo (mac)
		CastFromCsvFileToMac sortMac = new CastFromCsvFileToMac();
		arrayMac = sortMac.todo(arrayScan);

		//Filtering the combo
		CastFromMacToLineAlgo1 filter = new CastFromMacToLineAlgo1();		
		WriteFile<Mac> write = null;
		write = (WriteFile<Mac>) filter.cast(arrayMac);

		//Write the Csv
		write.receiveData(arrayMac);

		//Open the Csv
		OpenFile.open(write.getFileName());
	}

}
