package filter;

import java.util.ArrayList;

import libraries.InputException;
import objects.Logic;
import objects.SampleScan;

/**
 * This class extends @see {@link Filtering}.
 * This class filtering the data by the id : only the scan with the id that the user choosed will appear in the kml place.
 * @author Orel and Samuel.
 * @param SampleScan.
 */
public class FilteringKmlId extends Filtering<SampleScan> {

	private String id;
	
	/**
	 * Constructor.
	 * @param id.
	 */
	public FilteringKmlId(String id) {
		this.id = id;
	}
	
	/**
	 * This method filter by the id.
	 * @param array.
	 * @return array.
	 */
	@Override
	public ArrayList<SampleScan> filteringBy(ArrayList<SampleScan> array) throws InputException {
		removeDuplicateMac(array);
		//array.removeIf(SampleScan -> !SampleScan.getId().equals(id));
		ArrayList<SampleScan> arrayClone = (ArrayList<SampleScan>) array.clone();
		for (SampleScan sampleScan : array) {
			if (!sampleScan.getId().equals(id))
				arrayClone.remove(sampleScan);
		}
		if (array.size() == 0) throw new InputException("There array is empty.");
		return arrayClone;
	}

	@Override
	public String toString() {
		return "FilteringKmlId{" +
				"id='" + id + '\'' +
				'}';
	}
}
