package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class writes the csv file.
 * This class implement @see {@link WriteFile}.
 * @author Orel and Samuel.
 */

public class WriteCsv implements WriteFile {

	private FileWriter fw;
	private PrintWriter outs;

	/**
	 * Constructor.
	 * @param array.
	 * @exception IOException : Error writing the file.
	 */
	protected WriteCsv(String fileNameExport) {
		try {
			this.fw = new FileWriter(fileNameExport);
			this.outs = new PrintWriter(fw);
		} 
		catch (IOException ex) {
			System.out.println("Error writing the file." + ex);
		}
	}

	/**
	 * This method initialize the file we need to write.
	 */
	public void initialize() {
		outs.println("Time," + "ID," + "Lat," + "Lon," + "Alt," + "#Wifi networks," + "SSID1," + "MAC1," + "Frequency1," + "Signal1," +
				"SSID2," + "MAC2," + "Frequency2," + "Signal2," + "SSID3," + "MAC3," + "Frequency3," + "Signal3," + "SSID4," + "MAC4," + 
				"Frequency4," + "Signal4," + "SSID5," + "MAC5," + "Frequency5," + "Signal5," + "SSID6," + "MAC6," + "Frequency6," + 
				"Signal6," + "SSID7," + "MAC7," + "Frequency7," + "Signal7," + "SSID8," + "MAC8," + "Frequency8," + "Signal8," + 
				"SSID9," + "MAC9," + "Frequency9," + "Signal9," +"SSID10," + "MAC10," + "Frequency10," + "Signal10,");
	}

	/**
	 * This method check if the scan got a new time or not, then, write the data we need.
	 * @param array.
	 * @param fileNameExport.
	 * @exception Exception e : print stack trace.
	 */
	public void checkData(ArrayList<Scan> array, String fileNameExport) {
		initialize();
		for (Scan scan : array) {
			outs.print(scan.getTime().getTime() + ",");
			outs.print(scan.getId() + ",");
			outs.print(scan.getPointLocation().getLatitude() + ",");
			outs.print(scan.getPointLocation().getLongitude() + ",");
			outs.print(scan.getPointLocation().getAltitude() + ",");
			outs.print(scan.getWifiNetworks() + ",");
			addNetwork(scan);
		}
		try {
			createFile(fileNameExport);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method write the data we need.
	 * @param scan.
	 */
	public void addNetwork(Scan scan) {
		for (Wifi wifi : scan.getArrayStrongerWifi()) {
			outs.print(wifi.getName() + ",");
			outs.print(wifi.getMac() + ",");
			outs.print(wifi.getFrequency() + ",");
			outs.print(wifi.getSignal() + ",");
		}
		outs.println();
	}

	/**
	 * This method close the methods @see {@link FileWriter} and @see {@link PrintWriter}.
	 */
	public void createFile(String fileNameExport) {
		try {
			outs.close(); 
			fw.close();
		} 
		catch (IOException ex) {
			System.out.println("Error writing file : " + fileNameExport + ". " + ex);
		}
	}

}