package write;

import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;

import library.Filter;
import library.InputException;
import library.KmlUtil;
import read.SampleScan;

/**
 * This class write the kml file with the id filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel @author Samuel.
 */
public class WriteKmlId extends WriteKml implements WriteFile <SampleScan>{

	private Document document = new Document();
	private String id;
	
	/**
	 * Constructor.
	 * @param fileName.
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
	 * @param fileNameExport.
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
