package filter;

import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;

import read.SampleScan;
import write.WriteFile;
import write.WriteKmlWithoutFilter;

/**
 * This class extends @see {@link FilteringKml} and implements @see {@link Filtering}.
 * This class do not filter anything.
 * @author Orel and Samuel.
 */
public class FilteringKmlEmpty extends FilteringKml implements Filtering<SampleScan, SampleScan> {
	 
	/**
	 * @param array.
	 * @return {@link WriteKmlWithoutFilter}.
	 */
	@Override
	public WriteFile<SampleScan> filteringBy(ArrayList<SampleScan> array) {
		String fileName = getFileName();
		Document document = new Document();
		return new WriteKmlWithoutFilter(fileName, document);
	}

	

}
