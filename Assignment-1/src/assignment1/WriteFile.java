package assignment1;

import java.util.ArrayList;

/**
 * This class interface define for method : initialize, an abstract method checkData, addNetwork and createFile.
 * Then, six classes implements this interface : @see {@link WriteCsv}, @see {@link WriteKml}, @see {@link WriteKmlPlace}, @see {@link WriteKmlId}, @see {@link WriteKmlTime}, @see {@link WriteKmlWithoutFilter}.
 * @author Orel and Samuel
 */
public interface WriteFile {

	public void initialize();
	abstract void checkData(ArrayList<Wifi> array);
	public void addNetwork(Wifi wifi);
	public void createFile() throws InputException;
	
}
