package assignment1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class filtering the data by the id : only the scan with the id that the user choosed will appear in the kml place.
 * This class implement @see {@link FilteringKml}
 * @author Orel and Samuel.
 */

public class FilteringKmlId extends FilteringKml implements Filtering {

	/**
	 * This method ask the user to input the id.
	 * @param array.
	 * @return {@link WriteKmlId}.
	 */
	@SuppressWarnings("resource")
	public WriteFile filteringBy(ArrayList<Scan> array) throws InputException {
		System.out.println("Input an Id please :");
		String id = new Scanner(System.in).nextLine();
		for(Scan scan : array) 
			if (id.equals(scan.getId())) 
				return new WriteKmlId(id);
			else throw new InputException("Couldn't find the id :" + id);
		return filteringBy(array);
	}

}
