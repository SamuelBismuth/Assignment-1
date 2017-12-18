package libraries;

import java.io.File;

import read.ReadWigleWifi;

/**
 * This class reads a folder, and return all the files.
 * @author Orel and Samuel.
 */

public class ReadFolder {

	/**
	 * The method reads the folder and send all the file to @see {@link ReadWigleWifi}.
	 * @param FolderName.
	 * @exception NullPointerException : There is no file to read.
	 */
	public static File[] read(String folderName) {
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
	
	/**
	 * This method return the absolut path, where are found the file.
	 * @return absolutePath
	 */
	public static String getAbsolutePath() {
		String folderPathWorkspace  = new File(".").getAbsolutePath();
		return folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);
	}

}
