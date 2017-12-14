package write;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import algorithm1.LineAlgo1;

public class WriteComboAlgo1 implements WriteFile<LineAlgo1> {

	private FileWriter fw;
	private PrintWriter outs;
	private String fileName;

	/**
	 * Constructor.
	 * @param array.
	 * @param fileNameExport.
	 * @exception IOException : Error writing the file.
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

	@Override
	public void receiveData(ArrayList<LineAlgo1> array) {
		writeHeader();
		for (LineAlgo1 line : array) {
			outs.print(line.getIndex() + ",");
			outs.print(line.getMacName() + ",");
			outs.print(line.getSsid() + ",");
			outs.print(line.getFrequency() + ",");
			outs.print(line.getSignal() + ",");
			outs.print(line.getLocalisation().getLatitude() + ",");
			outs.print(line.getLocalisation().getLongitude() + ",");
			outs.print(line.getLocalisation().getAltitude() + ",");
			outs.print(line.getDate().getTime() + ", ");
			outs.println("Approx. w-center algo1");
		}
		writeFile();
	}

	@Override
	public void writeHeader() {
		outs.println("Index," + "Mac Name,"+ "SSID," + "Frequency," + "Signal,"  + "Lat," + "Lon," + "Alt," + "Time,");
	}

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
	public String getFileName() {
		return fileName;
	}

}
