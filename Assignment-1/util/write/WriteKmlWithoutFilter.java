package write;

import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;

import libraries.Filter;
import libraries.InputException;
import libraries.KmlUtil;
import read.SampleScan;

/**
 * This class write the kml file without filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel.
 */
public class WriteKmlWithoutFilter extends WriteKml implements WriteFile<SampleScan> {

	private Document document = new Document();

	/**
	 * Constructor.
	 * @param array
	 */
	public WriteKmlWithoutFilter(String fileName, Document document){
		super(fileName, document);
		this.document = document;
	}

	/**
	 * The method check the data, but without filter.
	 * @exception InputException : printStackTrace.
	 */
	@Override
	public void receiveData(ArrayList<SampleScan> array) {
		Filter.removeDuplicateMac(array);
		writeHeader();
		for (SampleScan scan : array)
			KmlUtil.addPlacemark(scan, document);
		try {
			writeFile();
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}

}
