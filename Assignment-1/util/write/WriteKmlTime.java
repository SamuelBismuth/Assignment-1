package write;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.boehn.kmlframework.kml.Document;

import library.Filter;
import library.InputException;
import library.KmlUtil;
import read.SampleScan;

/**
 * This class write the kml file with the time filter.
 * This class implements @see {@link WriteFile}, and extends @see {@link WriteKml}.
 * @author Orel and Samuel.
 */

public class WriteKmlTime extends WriteKml implements WriteFile<SampleScan> {

	private Document document = new Document();
	private GregorianCalendar dateBegining;
	private GregorianCalendar dateEnd;

	/**
	 * Constructor.
	 * @param dateBegining.
	 * @param dateEnd.
	 */
	public WriteKmlTime(String fileName, Document document, GregorianCalendar dateBegining, GregorianCalendar dateEnd) {
		super(fileName, document);
		this.document = document;
		this.dateBegining = dateBegining;
		this.dateEnd = dateEnd;
	}

	/**
	 * The method check the data, by the time.
	 * @see {@link GregorianCalendar}.
	 * @exception InputException : printStackTrace.
	 */
	@Override
	public void receiveData(ArrayList<SampleScan> array) {
		Filter.removeDuplicateMac(array);
		writeHeader();
		for (SampleScan scan : array)
			if(scan.getTime().after(dateBegining) && scan.getTime().before(dateEnd)) 
				KmlUtil.addPlacemark(scan, document);
		try {
			writeFile();
		} 
		catch (InputException e) {
			System.out.println(e);
		}
	}

}
