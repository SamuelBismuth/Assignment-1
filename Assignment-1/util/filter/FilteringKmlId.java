package filter;

import java.util.ArrayList;
import java.util.Scanner;

import org.boehn.kmlframework.kml.Document;

import libraries.InputException;
import libraries.UserChoice;
import read.SampleScan;
import write.WriteFile;
import write.WriteKmlId;

/**
 * This class extends implements @see {@link Filtering}.
 * This class filtering the data by the id : only the scan with the id that the user choosed will appear in the kml place.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 */
public class FilteringKmlId implements Filtering<SampleScan> {

	/**
	 * This method ask the user to input the id.
	 * @param arrayObject.
	 * @return {@link WriteKmlId}.
	 * We define our own exception if the id it's not found.
	 */
	@SuppressWarnings("resource")
	@Override
	public WriteFile<SampleScan> filteringBy(ArrayList<SampleScan> array) throws InputException {
		System.out.println("Input an Id please :");
		String id = new Scanner(System.in).nextLine();
		for(SampleScan scan : array) 
			if (id.equals(scan.getId())) {
				String fileName = UserChoice.getFileName("kml");
				Document document = new Document();
				return new WriteKmlId(fileName, document, id);
			}
		throw new InputException("Couldn't find the id :" + id);
	}

}
