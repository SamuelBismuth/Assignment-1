package write;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import read.SampleScan;
import read.Wifi;

/**
 * This class writes the csv file.
 * This class implement @see {@link WriteFile}.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 */
public class WriteCombo implements WriteFile<SampleScan> {

	private FileWriter fw;
	private PrintWriter outs;
	private String fileName;

	/**
	 * Constructor.
	 * @param array.
	 * @exception IOException : Error writing the file.
	 */
	public WriteCombo(String fileName) {
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
	public void receiveData(ArrayList<SampleScan> array) {
		writeHeader();
		for (SampleScan scan : array) {
			outs.print(scan.getTime().getTime() + ",");
			outs.print(scan.getId() + ",");
			outs.print(scan.getPointLocation().getLatitude() + ",");
			outs.print(scan.getPointLocation().getLongitude() + ",");
			outs.print(scan.getPointLocation().getAltitude() + ",");
			outs.print(scan.getWifiNetworks() + ",");
			addNetwork(scan);
		}
		writeFile();
	}
	
	/**
	 * This method write the header of the file we need to write.
	 */
	@Override
	public void writeHeader() {
		outs.println("Time," + "ID," + "Lat," + "Lon," + "Alt," + "#Wifi networks," + "SSID1," + "MAC1," + "Frequency1," + "Signal1," +
				"SSID2," + "MAC2," + "Frequency2," + "Signal2," + "SSID3," + "MAC3," + "Frequency3," + "Signal3," + "SSID4," + "MAC4," + 
				"Frequency4," + "Signal4," + "SSID5," + "MAC5," + "Frequency5," + "Signal5," + "SSID6," + "MAC6," + "Frequency6," + 
				"Signal6," + "SSID7," + "MAC7," + "Frequency7," + "Signal7," + "SSID8," + "MAC8," + "Frequency8," + "Signal8," + 
				"SSID9," + "MAC9," + "Frequency9," + "Signal9," +"SSID10," + "MAC10," + "Frequency10," + "Signal10,");
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
	
	//Private unimplemented method.
	
	/**
	 * This method write the data we need.
	 * @param scan.
	 */
	private void addNetwork(SampleScan scan) {
		for (Wifi wifi : scan.getArrayStrongerWifi()) {
			outs.print(wifi.getName() + ",");
			outs.print(wifi.getMac() + ",");
			outs.print(wifi.getFrequency() + ",");
			outs.print(wifi.getSignal() + ",");
		}
		outs.println();
	}
	
}