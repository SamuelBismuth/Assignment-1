package read;

import java.io.BufferedReader;

import org.apache.commons.csv.CSVRecord;

/**
 * This interface defines three methods : readFile, readBuffer, and inputObject.
 * Then, two classes implements this interface : @see {@link ReadWigleWifi}, @see {@link ReadCombo}.
 * 
 * The main goal of this interface his to read a file.
 * We are able to read a Wigle Wifi file, or a combo file.
 * 
 * @author Orel and Samuel.
 */
public interface ReadFile<T> {
	
	public BufferedReader readFile(String path);
	public void readBuffer();
	public T inputObject(CSVRecord record, String i);

}
