package read;

import java.io.BufferedReader;

import org.apache.commons.csv.CSVRecord;

import library.ReadFolder;

/**
 * This interface defines only one method : read.
 * Then, two classes implements this interface : @see {@link ReadWigleWifi}, @see {@link ReadFolder}.
 * @author Orel and Samuel.
 */
public interface ReadFile<T> {
	
	public BufferedReader readFile(String path);
	public void readBuffer();
	public T inputObject(CSVRecord record, String i);

}
