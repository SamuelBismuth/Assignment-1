package assignment;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class write the kml file with the time filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel.
 */

public class WriteKmlTime extends WriteKml implements WriteFile<Scan> {

	private GregorianCalendar dateBegining;
	private GregorianCalendar dateEnd;

	/**
	 * Constructor.
	 * @param dateBegining.
	 * @param dateEnd.
	 */
	public WriteKmlTime(GregorianCalendar dateBegining, GregorianCalendar dateEnd, ArrayList<Mac> array) {
		super(array);
		this.dateBegining = dateBegining;
		this.dateEnd = dateEnd;
	}

	/**
	 * The method check the data, by the time.
	 * @see {@link GregorianCalendar}.
	 * @exception InputException : printStackTrace.
	 */
	public void checkData(ArrayList<Scan> array, String fileNameExport) {
		initialize();
		for (Scan scan : array)
			if(scan.getTime().after(dateBegining) && scan.getTime().before(dateEnd)) 
				addNetwork(scan);
		try {
			createFile(fileNameExport);
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}

}
