package read;

import java.io.File;

/**
 * This class reads a folder, and return all the files.
 * @author Orel and Samuel.
 */

public class ReadFolder {

	/**
	 * Empty constructor.
	 */
	public ReadFolder() {}

	/**
	 * The method reads the folder and send all the file to @see {@link ReadCsv}.
	 * @param FolderName.
	 * @exception NullPointerException : There is no file to read.
	 */
	public File[] read(String folderName) {
		try {
			File folder = new File(folderName);
			File[] listOfFiles = folder.listFiles();
			return listOfFiles;
		}
		catch (NullPointerException ex) {
			System.out.println("there is no file to read : " + ex);
			System.exit(0);
		}
		return null;
	}

}
