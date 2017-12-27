package write;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import objects.LineAlgo1;

/**
 * This class writes a csv with the format of the algorithm 1.
 * This class implement @see {@link WriteFile}.
 * @author Orel and Samuel.
 * @param LineAlgo1.
 */
public class WriteComboAlgo1 implements WriteFile<LineAlgo1> {

	private FileWriter fw;
	private PrintWriter outs;
	private String fileName;

	/**
	 * Constructor.
	 * @param array.
	 * @exception IOException : printStackTrace.
	 */
	public WriteComboAlgo1(String fileName) {
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
	 * This method receives the data and for all the mac into the array, write the csv file.
	 * @param array.
	 */
	@Override
	public void receiveData(ArrayList<LineAlgo1> array) {
		writeHeader();
		for (LineAlgo1 lineAlgo1 : array) {
			outs.print(lineAlgo1.getIndex() + ",");
			outs.print(lineAlgo1.getMacName() + ",");
			outs.print(lineAlgo1.getNumberOfMac() + ",");
			outs.print(lineAlgo1.getSsid() + ",");
			outs.print(lineAlgo1.getSignal() + ",");
			outs.print(lineAlgo1.getLocalisation().getLatitude() + ",");
			outs.print(lineAlgo1.getLocalisation().getLongitude() + ",");
			outs.print(lineAlgo1.getLocalisation().getAltitude() + ",");
			outs.print(lineAlgo1.getDate().getTime() + ", ");
			outs.println("Approx. w-center algo1");
		}
		writeFile();
	}
	
	/**
	 * This method write the header of the file we need to write.
	 */
	@Override
	public void writeHeader() {
		outs.println("Index," + "Mac Name,"+ "Number of mac" +"SSID," + "Frequency," + "Signal,"  + "Lat," + "Lon," + "Alt," + "Time,");
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
