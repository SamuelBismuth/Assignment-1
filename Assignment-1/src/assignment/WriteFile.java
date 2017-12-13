package assignment;

import java.util.ArrayList;

/**
 * This class interface defines for method : initialize, an abstract method checkData, addNetwork and createFile.
 * Then, six classes implements this interface :
 * Two abstract classes :  @see {@link WriteCsv}, @see {@link WriteKml}.
 * WriteKml is extended by : @see {@link WriteKmlPlace}, @see {@link WriteKmlId}, @see {@link WriteKmlTime}, @see {@link WriteKmlWithoutFilter}.
 * @author Orel and Samuel.
 */
public interface WriteFile <T>{

	public void initialize();
	abstract void checkData(ArrayList<T> array, String fileNameExport);
	public void addNetwork(Scan scan);
	public void createFile(String fileNameExport) throws InputException;
	
}
