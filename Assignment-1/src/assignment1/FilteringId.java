package assignment1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class filtering the data by the id : only the wifi with the id that the user choosed will appear in the kml place.
 * This class implement @see {@link Filtering}
 * @author Orel and Samuel.
 */

public class FilteringId implements Filtering {

	/**
	 * Empty Constructor.
	 */
	public FilteringId() {}
	
	/**
	 * Constructor.
	 * @param array
	 * @exception {@link InputException} : Couldn't find the id.
	 */
	protected FilteringId(ArrayList<Wifi> array) {
		try {
			filteringBy(array);
		} 
		catch (InputException ex) {
			System.out.println(ex);
			new FilteringId(array);
		}
	}

	/**
	 * This method ask the user to input the id.
	 * @param array.
	 */
	@SuppressWarnings("resource")
	public void filteringBy(ArrayList<Wifi> array) throws InputException {
		System.out.println("Input an Id please :");
		String id = new Scanner(System.in).nextLine();
		for(Wifi wifi : array) 
			if (id.equals(wifi.getId())) {
				new WriteKmlId(array, id);
				break;
			}
			else throw new InputException("Couldn't find the id :" + id);
	}

}
