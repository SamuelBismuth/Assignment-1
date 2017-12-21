package filter;

import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;

import libraries.UserChoice;
import read.SampleScan;
import write.WriteFile;
import write.WriteKmlWithoutFilter;

/**
 * This class implements @see {@link Filtering}.
 * This class do not filter anything.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 */
public class FilteringKmlEmpty implements Filtering<SampleScan> {
	 
	/**
	 * @param array.
	 * @return {@link WriteKmlWithoutFilter}.
	 */
	@Override
	public WriteFile<SampleScan> filteringBy(ArrayList<SampleScan> array) {
		String fileName = UserChoice.getFileName("kml");
		Document document = new Document();
		return new WriteKmlWithoutFilter(fileName, document);
	}

	

}
