package assignment1;

import java.util.ArrayList;

/**
 * This class write the kml file without filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel
 */
public class WriteKmlWithoutFilter extends WriteKml implements WriteFile {

	/**
	 * Empty constructor.
	 */
	public WriteKmlWithoutFilter(){}
	
	/**
	 * Constructor.
	 * @param array
	 */
	protected WriteKmlWithoutFilter(ArrayList<Wifi> array) {
		initialize();
		checkData(array);
	}

	/**
	 * The method check the data, but without filter (only the mac).
	 * @exception InputException : printStackTrace.
	 */
	public void checkData(ArrayList<Wifi> array) {
		for (Wifi wifi : array)
			if(sameMac(array, wifi)) 
				addNetwork(wifi);
		try {
			createFile();
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}

}
