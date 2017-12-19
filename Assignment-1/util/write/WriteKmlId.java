package write;

import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;

import libraries.Filter;
import libraries.InputException;
import libraries.KmlUtil;
import read.SampleScan;

/**
 * This class write the kml file with the id filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel.
 * @param <SampleScan>.
 */
public class WriteKmlId extends WriteKml implements WriteFile <SampleScan>{

	private Document document = new Document();
	private String id;
	
	/**
	 * Constructor.
	 * @param fileName.
	 * @param document.
	 * @param id.
	 */
	public WriteKmlId(String fileName, Document document, String id) {
		super(fileName, document);
		this.document = document;
		this.id = id;
	}

	/**
	 * The method check the data, by the id.
	 * @param array.
	 * @exception InputException : printStackTrace.
	 */
	@Override
	public void receiveData(ArrayList<SampleScan> array) {
		Filter.removeDuplicateMac(array);
		writeHeader();
		for (SampleScan scan : array)
			if(scan.getId().equals(id)) 
				KmlUtil.addPlacemark(scan, document);
			try {
				writeFile();
			} 
			catch (InputException e) {
				System.out.println(e);
			}
	}
	
}
