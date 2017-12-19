package mains;

import java.io.File;
import java.util.ArrayList;

import algorithms.Difference;
import libraries.Comparison;
import read.ReadCombo;
import read.ReadCsv;
import read.SampleScan;
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
public class MainRepportAlgo2 {

	private static ArrayList<Difference> arrayDiffAlgo2 = new ArrayList<Difference>();

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
		//Algorithm 2.//
		////////////////

		ArrayList<SampleScan> arrayScanBoazAlgo2 = new ArrayList<SampleScan>();
		File comboBoazAlgo2 = new File("Algo2_BM3_TS2_4.csv");
		ReadCsv<SampleScan> readComboAlgo2 = new ReadCombo("C:\\Users\\Samuel\\git\\Assignment-1\\Assignment-1\\", arrayScanBoazAlgo2, comboBoazAlgo2);
		readComboAlgo2.readBuffer();

		ArrayList<SampleScan> arrayScanAlgo2 = new ArrayList<SampleScan>();
		File comboAlgo2 = new File("Our_Algo2_BM3_TS2_4.csv");
		readComboAlgo2 = new ReadCombo("C:\\Users\\Samuel\\git\\Assignment-1\\Assignment-1\\", arrayScanAlgo2, comboAlgo2);
		readComboAlgo2.readBuffer();

		Comparison.algo2(arrayScanBoazAlgo2, arrayScanAlgo2, arrayDiffAlgo2);

		WriteFile<Difference> writealgo2 = new WriteDifference("Compare_Algo2_BM3_TS2_4");
		writealgo2.receiveData(arrayDiffAlgo2);
	}
	
}
