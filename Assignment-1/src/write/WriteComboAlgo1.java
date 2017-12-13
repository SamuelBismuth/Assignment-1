package write;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import assignment.InputException;
import assignment.SampleAlgo1;
import assignment.Scan;

public class WriteComboAlgo1 implements WriteFile <SampleAlgo1>{

	private FileWriter fw;
	private PrintWriter outs;
	private ArrayList<SampleAlgo1> array;

	/**
	 * Constructor.
	 * @param array.
	 * @param fileNameExport.
	 * @exception IOException : Error writing the file.
	 */
	protected WriteComboAlgo1(ArrayList<SampleAlgo1> array) {
		this.array = array;
	}

	public void initialize() {
		outs.println("Index," + "Mac Name,"+ "SSID," + "Frequency," + "Signal,"  + "Lat," + "Lon," + "Alt," + "Time,");
	}

	public void checkData(ArrayList<SampleAlgo1> array, String fileNameExport) {
		for (SampleAlgo1 sample : array) {
			outs.print(sample.getIndex() + ",");
			outs.print(sample.getMacName() + ",");
			outs.print(sample.getSsid() + ",");
			outs.print(sample.getFrequency() + ",");
			outs.print(sample.getSignal() + ",");
			outs.print(sample.getLocalisation().getLatitude() + ",");
			outs.print(sample.getLocalisation().getLongitude() + ",");
			outs.print(sample.getLocalisation().getAltitude() + ",");
			outs.print(sample.getDate().getTime() + ", ");
			outs.println("Approx. w-center algo1");
		}
	}


	public void createFile(String fileNameExport) throws InputException {
		try {
			this.fw = new FileWriter(fileNameExport);
			this.outs = new PrintWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialize();
		checkData(array, "");
		try {
			outs.close(); 
			fw.close();
		} 
		catch (IOException ex) {
			System.out.println("Error writing file : " + fileNameExport + ". " + ex);
		}
	}

	public void addNetwork(Scan scan) {
		//don't need this function.
	}

}
