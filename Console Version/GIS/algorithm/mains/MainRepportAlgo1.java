package mains;

import java.io.File;
import java.util.ArrayList;

import libraries.Comparison;
import objects.Difference;
import objects.SampleScan;
import read.ReadComboAlgo1;
import read.ReadCsv;
import write.WriteDifference;
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
public class MainRepportAlgo1 {

	private static ArrayList<Difference> arrayDiffAlgo1 = new ArrayList<Difference>();

	/**
	 * This main method is used to write the csv file as support of the repport.
	 * @param args.
	 * Here is the idea :
	 * Taking all the the coordinates af boaz's combo, and all coordinates ouf our combo, we try to comnpare the both.
	 * To do this, we use the next formula :
	 * For one coordinate : |our.coordinate - boaz coordinate|
	 * then, we sum all the coordinate.
	 * And, we divide the sum with the number of sample (4 here).
	 */
	public static void main(String[] args) {

		////////////////
		//Algorithm 1.//
		////////////////

		ArrayList<SampleScan> arrayScanBoaz = new ArrayList<SampleScan>();
		File comboBoaz = new File("Algo1_BM3_4.csv");
		ReadCsv<SampleScan> readComboAlgo1 = new ReadComboAlgo1("C:\\Users\\Samuel\\git\\Assignment-1\\Assignment-1\\", arrayScanBoaz, comboBoaz);
		readComboAlgo1.readBuffer();

		ArrayList<SampleScan> arrayScan = new ArrayList<SampleScan>();
		File combo = new File("Our_Algo1_BM3_4.csv");
		readComboAlgo1 = new ReadComboAlgo1("C:\\Users\\Samuel\\git\\Assignment-1\\Assignment-1\\", arrayScan, combo);
		readComboAlgo1.readBuffer();

		Comparison.algo1(arrayScanBoaz, arrayScan, arrayDiffAlgo1);

		WriteFile<Difference> write = new WriteDifference("Compare_Algo1_BM3_4");
		write.receiveData(arrayDiffAlgo1);

	}

}
