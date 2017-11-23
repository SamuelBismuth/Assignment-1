package assignment1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class writes the csv file.
 * This class implement @see {@link WriteFile} and extends {@link UserChoice}.
 * @author Orel and Samuel
 */

public class WriteCsv extends UserChoice implements WriteFile {

	private FileWriter fw;
	private PrintWriter outs;
	private ArrayList<Wifi> array;
	private String fileNameExport;

	/**
	 * Test constructor.
	 */
	public WriteCsv(ArrayList<Wifi> array, String test) {
		this.array = array;
	}
	
	/**
	 * Constructor.
	 * @param array.
	 * @exception IOException : Error writing the file.
	 */
	@SuppressWarnings("resource")
	protected WriteCsv(ArrayList<Wifi> array) {
		try {
			System.out.println("Input a name for the csv file you want to create : ");
			this.fileNameExport = new Scanner(System.in).nextLine() + ".csv";
			this.fw = new FileWriter(fileNameExport);
			this.outs = new PrintWriter(fw);
			this.array = array;
			if (array.size() != 0) {
				initialize();
				checkData(array);
			} 
			else System.out.println("Nothing to write.");
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
				"SSID2," + "MAC2," + "Frequency2," + "Signal2," + "SSID3," + "MAC3," + "Frequency3," + "Signal3," + "SSID4," + "MAC4," + "Frequency4," + "Signal4,"
				+ "SSID5," + "MAC5," + "Frequency5," + "Signal5," + "SSID6," + "MAC6," + "Frequency6," + "Signal6," + "SSID7," + "MAC7," + "Frequency7," + "Signal7," +
				"SSID8," + "MAC8," + "Frequency8," + "Signal8," + "SSID9," + "MAC9," + "Frequency9," + "Signal9," +"SSID10," + "MAC10," + "Frequency10," + "Signal10,");
	}

	/**
	 * This method check if the wifi got a new time or not, then, write the data we need.
	 * @param array,
	 */
	public void checkData(ArrayList<Wifi> array) {
		for (int i = 0; i < array.size(); i++) {
			if (isFirst(array, i)) {
				outs.print(array.get(i).getTime().getTime() + ",");
				outs.print(array.get(i).getId() + ",");
				outs.print(array.get(i).getPointLocation().getLatitude() + ",");
				outs.print(array.get(i).getPointLocation().getLongitude() + ",");
				outs.print(array.get(i).getPointLocation().getAltitude() + ",");
				outs.print(wifiNetworks(array, i) + ",");
				addNetwork(array.get(i));
			}
			else {
				addNetwork(array.get(i));
				while (i > 10 && moreThanTen(array, i)) i++;
			}
		}
		createFile();
		userChoice(array);
	}

	/**
	 * This method write the data we need.
	 * @param wifi.
	 */
	public void addNetwork(Wifi wifi) {
		outs.print(wifi.getName() + ",");
		outs.print(wifi.getMac() + ",");
		outs.print(wifi.getFrequency() + ",");
		outs.print(wifi.getSignal() + ",");
	}


	/**
	 * This method create the file and open it.
	 */
	public void createFile() {
		try {
			outs.close(); // Close all the methods.
			fw.close();
			new OpenFile(fileNameExport); // Open the file.
		} 
		catch (IOException ex) {
			System.out.println("Error writing file." + ex);
		}
	}

	// private methods.

	/**
	 * The method allow us to know if we need a new line.
	 * @param matrix
	 * @param i
	 * @return false if the time is new
	 * @return true if not
	 */
	private boolean isFirst(ArrayList<Wifi> matrix,  int i) {
		if (i == 0) return true;
		if(array.get(i).getTime().equals(matrix.get(i - 1).getTime())) return false;
		outs.println();
		return true;
	}

	/**
	 * The method controls the number of wifi networks.
	 * @param array
	 * @param i
	 * @return true if there is more than 10 wifi
	 * @return false if not
	 */
	private boolean moreThanTen(ArrayList<Wifi> array, int i) {
		try {
			if(array.get(i).getTime().equals(array.get(i - 10).getTime())) return true;
			return false;
		}
		catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}

	/**
	 * The method counts the number of wifi in the line.
	 * @param array
	 * @param i
	 * @return the number of wifi
	 */
	private String wifiNetworks(ArrayList<Wifi> array, int i) {
		int count = 1;
		while (i < array.size() - 1 && count < 10 && array.get(i).getTime().equals(array.get(++i).getTime())) count ++;
		return Integer.toString(count);
	}

}