package write;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import algorithms.Difference;

/**
 * This class writes the csv file for the repports.
 * This class implement @see {@link WriteFile}.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 */
public class WriteDifference implements WriteFile<Difference> {

	private FileWriter fw;
	private PrintWriter outs;
	private String fileName;

	/**
	 * Constructor.
	 * @param array.
	 * @exception IOException : printStackTrace.
	 */
	public WriteDifference(String fileName) {
		try {
			fileName += ".csv";
			this.fileName = fileName;
			this.fw = new FileWriter(fileName);
			this.outs = new PrintWriter(fw);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method receives the data and for all the scan into the array, write the csv file.
	 * @param array.
	 */
	@Override
	public void receiveData(ArrayList<Difference> array) {
		writeHeader();
		for (Difference scan : array) {
			outs.print(scan.getMacName() + ",");
			outs.print(scan.getDiff().getLatitude() + ",");
			outs.print(scan.getDiff().getLongitude() + ",");
			outs.println(scan.getDiff().getAltitude() + ",");
		}
		outs.println();
		sumLocalisation(array);
		writeFile();
	}

	/**
	 * This method calculates the sum between all the gps coordinates and divide the result by the number of sample mac.
	 * @param array.
	 */
	private void sumLocalisation(ArrayList<Difference> array) {
		double sumLatitude = 0,
				sumLongitude = 0,
				sumAltitude = 0;
		for (Difference scan : array) {
			sumLatitude += scan.getDiff().getLatitude();
			sumLongitude += scan.getDiff().getLongitude();
			sumAltitude += scan.getDiff().getAltitude();
		}
		outs.println("Average latitude : " + sumLatitude/(array.size()));
		outs.println("Average longitude : " + sumLongitude/(array.size()));
		outs.println("Average altitude : " + sumAltitude/(array.size()));
	}

	/**
	 * This method write the header of the file we need to write.
	 */
	@Override
	public void writeHeader() {
		outs.println("Mac name" + "Diff Lat," + "Diff Lon," + "Diff Alt,");
	}

	/**
	 * This method write the file by closing the method.
	 * @exception IOException : Error writing file.
	 */
	@Override
	public void writeFile() {	
		try {
			outs.close(); 
			fw.close();
		} 
		catch (IOException ex) {
			System.out.println("Error writing file : " + ex);
		}
	}

	/**
	 * @return fileName.
	 */
	@Override
	public String getFileName() {
		return fileName;
	}

}
