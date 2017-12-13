package read;

import java.io.IOException;

import assignment.InputException;

/**
 * This class opens a file with the help of the terminal.
 * This class implements @see {@link Read}.
 * @author Orel and Samuel.
 */

public class OpenFile implements Read {

	/**
	 * Empty constructor.
	 */
	protected OpenFile() {}
	
	/**
	 * Open the file asked for.
	 * @exception InputException : Error opening the file.
	 */
	public void read(String fileName) {
		try
		{
			Process p = Runtime.getRuntime().exec("cmd.exe /c" + fileName);
			new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
		}
		catch (IOException ex)
		{
			System.out.println("Error opening the file." + ex);		
		}
	}
	
}
