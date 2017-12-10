package assignment;

import java.util.ArrayList;

/**
 * This class write the kml file with the id filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel @author Samuel.
 */
public class WriteKmlId extends WriteKml implements WriteFile {

	private String id;
	
	/**
	 * Constructor.
	 * @param id
	 */
	protected WriteKmlId(String id) {
		this.id = id;
	}

	/**
	 * The method check the data, by the id.
	 * @param array.
	 * @param fileNameExport.
	 * @exception InputException : printStackTrace.
	 */
	public void checkData(ArrayList<Scan> array, String fileNameExport) {
		initialize();
		for (Scan scan : array)
			if(scan.getId().equals(id)) 
				addNetwork(scan);
			try {
				createFile(fileNameExport);
			} 
			catch (InputException e) {
				System.out.println(e);
			}
	}

}
