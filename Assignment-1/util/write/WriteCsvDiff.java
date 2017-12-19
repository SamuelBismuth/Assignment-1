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
public class WriteCsvDiff implements WriteFile<Difference> {
	
	private FileWriter fw;
	private PrintWriter outs;
	private String fileName;

	/**
	 * Constructor.
	 * @param array.
	 * @exception IOException : printStackTrace.
	 */
	public WriteCsvDiff(String fileName) {
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
		writeFile();
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
	