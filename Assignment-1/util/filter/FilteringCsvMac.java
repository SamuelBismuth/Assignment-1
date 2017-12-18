package filter;

import java.util.ArrayList;

import algorithms.LineAlgo1;
import algorithms.Mac;
import libraries.UserChoice;
import write.WriteComboAlgo1;
import write.WriteFile;

/**
 * This class implements @see {@link Filtering}.
 * This class filter the {@link ArrayList} of {@link Mac} to give the new coordinates by the algorithm 1.
 * @author Orel and Samuel.
 * @param <Mac>.
 */
public class FilteringCsvMac implements Filtering<Mac> {

	/**
	 * Empty constructor.
	 */
	public FilteringCsvMac() {}

	/**
	 * This method create an {@link ArrayList} of {@link LineAlgo1} from an ArrayList of {@link Mac}.
	 * In the object {@link LineAlgo1} is found all the information needed to create the new coordinates.
	 * @param arrayMac.
	 * @return {@link WriteComboAlgo1}.
	 */
	@Override
	public WriteFile<Mac> filteringBy(ArrayList<Mac> arrayMac)  {
		int index = 0;
		for (Mac mac : arrayMac) {
			mac.setLineAlgo1(
					new LineAlgo1(
							index++,
							mac.getMacName(),
							mac.getArrayMacLocation().get(0).getWifi().getName(),
							mac.getArrayMacLocation().get(0).getWifi().getFrequency(), 
							mac.getArrayMacLocation().get(0).getWifi().getSignal(),
							mac.getWeightCenter(),
							mac.getDate()
							)
					);
		}
		
		String fileName = UserChoice.getFileName("csv");
		return new WriteComboAlgo1(fileName);
	}

}
