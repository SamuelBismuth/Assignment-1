package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * This class read a csv file.
 * @author Orel and Samuel.
 * @param <T>.
 */
public abstract class ReadCsv <T> {
	
	protected String folderName;
	protected ArrayList<T> array;
	protected File file;

	/**
	 * Constructor.
	 * @param file.
	 * @param folderName.
	 * @param array.
	 */
	protected ReadCsv(String folderName, ArrayList<T> array, File file) {
		this.folderName = folderName;
		this.array = array;
		this.file = file;
	}

	public abstract void readBuffer();
	
	/**
	 * Read the buffer.
	 * @param path.
	 * @return the buffer.
	 */
	public BufferedReader readFile(String path) {
		try {
			Reader in = new FileReader(path);
			BufferedReader br = new BufferedReader(in);
			return br;
		} 
		catch (IOException ex) {
			System.out.println("Error reading file : " + ex);
			System.exit(0);
			return null;
		}
	}
	
}
