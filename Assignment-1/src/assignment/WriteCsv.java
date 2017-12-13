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

public abstract class WriteCsv implements WriteFile <Scan>{

	private FileWriter fw;
	private PrintWriter outs;

	public abstract void initialize();
	public abstract void checkData(ArrayList<Scan> array, String fileNameExport);
	public abstract  void addNetwork(Scan scan);

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
	
	/**
	 * Initialize fw and outs.
	 * @param fw.
	 * @param outs.
	 */
	protected void close(FileWriter fw, PrintWriter outs, String fileNameExport) {
		this.fw = fw;
		this.outs = outs;
		createFile(fileNameExport);
	}
	

}