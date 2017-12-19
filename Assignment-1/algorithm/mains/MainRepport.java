package mains;

import java.io.File;
import java.util.ArrayList;

import algorithms.Difference;
import libraries.Repport;
import read.ReadCombo;
import read.ReadComboAlgo1;
import read.ReadCsv;
import read.SampleScan;
import write.WriteCsvDiff;
import write.WriteFile;

public class MainRepport {

	private static ArrayList<Difference> arrayDiffAlgo1 = new ArrayList<Difference>();
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

		//ALGORITHM 1.

		ArrayList<SampleScan> arrayScanBoaz = new ArrayList<SampleScan>();
		File comboBoaz = new File("Algo1_4_BM3_comb_all_.csv");
		ReadCsv<SampleScan> readComboAlgo1 = new ReadComboAlgo1("C:\\Users\\Samuel\\git\\Assignment-1\\Assignment-1\\", arrayScanBoaz, comboBoaz);
		readComboAlgo1.readBuffer();

		ArrayList<SampleScan> arrayScan = new ArrayList<SampleScan>();
		File combo = new File("Algo1BM3.csv");
		readComboAlgo1 = new ReadComboAlgo1("C:\\Users\\Samuel\\git\\Assignment-1\\Assignment-1\\", arrayScan, combo);
		readComboAlgo1.readBuffer();

		Repport.algo1(arrayScanBoaz, arrayScan, arrayDiffAlgo1);

		WriteFile<Difference> write = new WriteCsvDiff("DIFFAlgo1");
		write.receiveData(arrayDiffAlgo1);

		//ALGORITHM 2.
		
		ArrayList<SampleScan> arrayScanBoazAlgo2 = new ArrayList<SampleScan>();
		File comboBoazAlgo2 = new File("Algo2_BM2_TS2.csv");
		ReadCsv<SampleScan> readComboAlgo2 = new ReadCombo("C:\\Users\\Samuel\\git\\Assignment-1\\Assignment-1\\", arrayScanBoazAlgo2, comboBoazAlgo2);
		readComboAlgo2.readBuffer();

		ArrayList<SampleScan> arrayScanAlgo2 = new ArrayList<SampleScan>();
		File comboAlgo2 = new File("Algo2BM2.csv");
		readComboAlgo2 = new ReadCombo("C:\\Users\\Samuel\\git\\Assignment-1\\Assignment-1\\", arrayScanAlgo2, comboAlgo2);
		readComboAlgo2.readBuffer();

		Repport.algo2(arrayScanBoazAlgo2, arrayScanAlgo2, arrayDiffAlgo2);
		
		WriteFile<Difference> writealgo2 = new WriteCsvDiff("diffAlgo2");
		writealgo2.receiveData(arrayDiffAlgo2);
		
	}

}
