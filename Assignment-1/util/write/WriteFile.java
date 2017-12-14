package write;

import java.util.ArrayList;

import library.InputException;

/**
 * This class interface defines for method : initialize, an abstract method checkData, addNetwork and createFile.
 * Then, six classes implements this interface :
 * Two abstract classes :  @see {@link WriteCsv}, @see {@link WriteKml}.
 * WriteKml is extended by : @see {@link WriteKmlPlace}, @see {@link WriteKmlId}, @see {@link WriteKmlTime}, @see {@link WriteKmlWithoutFilter}.
 * @author Orel and Samuel.
 */
public interface WriteFile <T> {
	
	public abstract void receiveData(ArrayList<T> array);
	public void writeHeader();
	public void writeFile() throws InputException;
	public String getFileName();
	
}
