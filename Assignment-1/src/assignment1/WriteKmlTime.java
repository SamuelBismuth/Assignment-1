package assignment1;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class write the kml file with the time filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel
 */

public class WriteKmlTime extends WriteKml implements WriteFile {

	private GregorianCalendar dateBegining;
	private GregorianCalendar dateEnd;

	/**
	 * Test constructor.
	 * @param dateBegining
	 * @param dateEnd
	 */
	public WriteKmlTime(GregorianCalendar dateBegining, GregorianCalendar dateEnd) {
		this.dateBegining = dateBegining;
		this.dateEnd = dateEnd;
	}

	/**
	 * Constructor.
	 * @param array
	 * @param dateBegining
	 * @param dateEnd
	 */
	protected WriteKmlTime(ArrayList<Wifi> array, GregorianCalendar dateBegining, GregorianCalendar dateEnd) {
		this.dateBegining = dateBegining;
		this.dateEnd = dateEnd;
		initialize();
		checkData(array);
	}

	/**
	 * The method check the data, by the time.
	 * @see {@link GregorianCalendar}.
	 * @exception InputException : printStackTrace.
	 */
	public void checkData(ArrayList<Wifi> array) {
		for (Wifi wifi : array)
			if(wifi.getTime().after(dateBegining) && wifi.getTime().before(dateEnd) && sameMac(array, wifi)) 
				addNetwork(wifi);
		try {
			createFile();
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}

}
