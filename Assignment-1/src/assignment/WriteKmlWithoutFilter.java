package assignment;

import java.util.ArrayList;

/**
 * This class write the kml file without filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel.
 */
public class WriteKmlWithoutFilter extends WriteKml implements WriteFile {

	/**
	 * Empty constructor.
	 */
	public WriteKmlWithoutFilter(){}

	/**
	 * The method check the data, but without filter.
	 * @exception InputException : printStackTrace.
	 */
	public void checkData(ArrayList<Scan> array, String fileNameExport) {
		initialize();
		for (Scan scan : array)
			addNetwork(scan);
		try {
			createFile(fileNameExport);
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}

}
