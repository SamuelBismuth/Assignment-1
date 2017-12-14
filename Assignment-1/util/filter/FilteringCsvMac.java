package filter;

import java.util.ArrayList;
import java.util.Scanner;

import algorithm1.LineAlgo1;
import algorithm1.Mac;
import write.WriteComboAlgo1;
import write.WriteFile;

/**
 * This class implements @see {@link Filtering}.
 * This class filter the {@link ArrayList} of {@link Mac} to give the new coordinates by the algorithm 1.
 * @author Orel and Samuel.
 */
public class FilteringCsvMac implements Filtering<LineAlgo1, Mac> {

	/**
	 * Empty constructor.
	 */
	public FilteringCsvMac() {}

	/**
	 * This method create an {@link ArrayList} of {@link LineAlgo1} from an ArrayList of {@link Mac}.
	 * In the object {@link LineAlgo1} is found all the information needed to create the new coordinates.
	 * @param arrayMac.
	 */
	@Override
	public WriteFile<LineAlgo1> filteringBy(ArrayList<Mac> arrayMac)  {
		ArrayList<LineAlgo1> array = new ArrayList<LineAlgo1>();
		int index = 0;
		for (Mac mac : arrayMac) {
			array.add(
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
		String fileName = getFileName();
		return new WriteComboAlgo1(fileName);
	}

	@SuppressWarnings("resource")
	private static String getFileName() {
		System.out.println("Input a name for the csv file you want to create : ");
		return new Scanner(System.in).nextLine() + ".csv";
	}
}
