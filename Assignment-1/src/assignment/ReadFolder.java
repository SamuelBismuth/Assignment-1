package assignment;

import java.io.File;
import java.util.ArrayList;

/**
 * This class reads a folder, and sends all the files in the method read file.
 * This class implements @see {@link Read}.
 * @author Orel and Samuel
 */

public class ReadFolder implements Read {

	private ArrayList<CsvFile> array = new ArrayList<CsvFile>();
	
	/**
	 * Constructor.
	 * @param fileFolder.
	 */
	public ReadFolder(ArrayList<CsvFile> array) {
		this.array = array;
	}

	/**
	 * The method reads the folder and send all the file to @see {@link ReadFile}.
	 * @param FolderName.
	 * @exception NullPointerException : There is no file to read.
	 */
	public void read(String folderName) {
		try {
			File folder = new File(folderName);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) if(file.getName().contains(".csv")) new ReadFile(file, folderName, array);
		}
		catch (NullPointerException ex) {
			System.out.println("there is no file to read : " + ex);
			System.exit(0);
		}
	}

}
