package assignment1;

import java.util.ArrayList;

/**
 * This class interface define for method : initialize, an abstract method checkData, addNetwork and createFile.
 * Then, six classes implements this interface : @see {@link WriteCsv}, @see {@link WriteKml}, @see {@link WriteKmlPlace}, @see {@link WriteKmlId}, @see {@link WriteKmlTime}, @see {@link WriteKmlWithoutFilter}.
 * @author Orel and Samuel
 */
public interface WriteFile {

	public void initialize();
	abstract void checkData(ArrayList<Scan> array, String fileNameExport);
	public void addNetwork(Scan scan);
	public void createFile(String fileNameExport) throws InputException;
	
}
