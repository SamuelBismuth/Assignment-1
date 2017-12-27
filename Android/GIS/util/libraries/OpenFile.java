package libraries;

import java.io.IOException;

/**
 * This class opens a file with the help of the terminal.
 * @author Orel and Samuel.
 */

public class OpenFile {

	/**
	 * Open the file asked for.
	 * @exception InputException : Error opening the file.
	 */
	public static void open(String fileName) {
		try {
			Process p = Runtime.getRuntime().exec("cmd.exe /c" + fileName);
			new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
		}
		catch (IOException ex) {
			System.out.println("Error opening the file." + ex);		
		}
	}
	
}
