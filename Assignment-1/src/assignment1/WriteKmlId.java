package assignment1;

import java.util.ArrayList;

/**
 * This class write the kml file with the id filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel @author Samuel
 */
public class WriteKmlId extends WriteKml implements WriteFile {

	private String id;
	
	/**
	 * Test constructor.
	 * @param id
	 */
	public WriteKmlId(String id) {
		this.id = id;
	}
	
	/**
	 * Constructor.
	 * @param array
	 * @param id
	 */
	protected WriteKmlId(ArrayList<Wifi> array, String id) {
		this.id = id;
		initialize();
		checkData(array);
	}


	/**
	 * The method check the data, by the id.
	 * @param array.
	 * @exception InputException : printStackTrace.
	 */
	public void checkData(ArrayList<Wifi> array) {
		for (Wifi wifi : array)
			if(sameMac(array, wifi) && wifi.getId().equals(id)) 
				addNetwork(wifi);
			try {
				createFile();
			} 
			catch (InputException e) {
				System.out.println(e);
			}
	}
	
}
