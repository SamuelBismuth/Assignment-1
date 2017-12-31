package libraries;

import java.util.ArrayList;

import objects.CsvFile;
import objects.LineAlgo1;
import objects.Mac;
import objects.SampleScan;
import objects.WeigthAverage;

public class DataBase {

	private static ArrayList<CsvFile> arrayCsvFile = new ArrayList<CsvFile>();
	private static ArrayList<SampleScan> arraySampleScan = new ArrayList<SampleScan>();
	private static ArrayList<Mac> arrayMac = new ArrayList<Mac>();
	private static ArrayList<LineAlgo1> arrayLineAlgo1 = new ArrayList<LineAlgo1>();
	private static ArrayList<WeigthAverage> arrayWeightAverage= new ArrayList<WeigthAverage>();
	
	/**
	 * @return the arrayCsvFile
	 */
	public static ArrayList<CsvFile> getArrayCsvFile() {
		return arrayCsvFile;
	}
	
	/**
	 * @param arrayCsvFile the arrayCsvFile to set
	 */
	public static void setArrayCsvFile(ArrayList<CsvFile> arrayCsvFile) {
		DataBase.arrayCsvFile = arrayCsvFile;
	}
	
	/**
	 * @return the arraySampleScan
	 */
	public static ArrayList<SampleScan> getArraySampleScan() {
		return arraySampleScan;
	}
	
	/**
	 * @param arraySampleScan the arraySampleScan to set
	 */
	public static void setArraySampleScan(ArrayList<SampleScan> arraySampleScan) {
		DataBase.arraySampleScan = arraySampleScan;
	}
	
	/**
	 * @return the arrayMac
	 */
	public static ArrayList<Mac> getArrayMac() {
		return arrayMac;
	}
	
	/**
	 * @param arrayMac the arrayMac to set
	 */
	public static void setArrayMac(ArrayList<Mac> arrayMac) {
		DataBase.arrayMac = arrayMac;
	}
	
	/**
	 * @return the arrayLineAlgo1
	 */
	public static ArrayList<LineAlgo1> getArrayLineAlgo1() {
		return arrayLineAlgo1;
	}
	
	/**
	 * @param arrayLineAlgo1 the arrayLineAlgo1 to set
	 */
	public static void setArrayLineAlgo1(ArrayList<LineAlgo1> arrayLineAlgo1) {
		DataBase.arrayLineAlgo1 = arrayLineAlgo1;
	}
	
	/**
	 * @return the arrayWeightAverage
	 */
	public static ArrayList<WeigthAverage> getArrayWeightAverage() {
		return arrayWeightAverage;
	}
	
	/**
	 * @param arrayWeightAverage the arrayWeightAverage to set
	 */
	public static void setArrayWeightAverage(ArrayList<WeigthAverage> arrayWeightAverage) {
		DataBase.arrayWeightAverage = arrayWeightAverage;
	}

	public static boolean addArraySampleScan(ArrayList<SampleScan> arrayToAdd) {
		return arraySampleScan.addAll(arrayToAdd);
	}

	public static boolean addArrayCsvFile(ArrayList<CsvFile> arrayToAdd) {
		return arrayCsvFile.addAll(arrayToAdd);
	}

	public static int numberOfWifi() {
		int sum = 0;
		for (SampleScan sampleScan : arraySampleScan)
			sum += sampleScan.getArrayStrongerWifi().size();
		return sum;
	}

	public static void clear() {
		arraySampleScan.clear();
	}

	/**
	 * This method check if the id exist into the database.
	 * @param id.
	 * @return true if the id exist.
	 * @return false if the id does't exist.
	 */
	public static boolean containsTheId(String id) {
		for (CsvFile csvFile : arrayCsvFile)
			if (csvFile.getId() == id) return true;
		return false;
	}
	
}
