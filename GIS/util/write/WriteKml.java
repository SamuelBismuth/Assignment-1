package write;

import java.io.IOException;
import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.KmlException;

import libraries.InputException;
import libraries.KmlUtil;
import read.SampleScan;

/**
 * This absract class write a kml file.
 * This class use the API kmlframework @see {@link https://code.google.com/archive/p/kmlframework/source/default/source}.
 * This class implements @see {@link WriteFile}.
 * @see NOTICE for more informations about how to run with the api.
 * @author Orel and Samuel.
 * @param <SampleScan>
 */
public class WriteKml implements WriteFile<SampleScan> {

	private Document document;
	private String fileName;

	/**
	 * Constructor.
	 * @param fileName
	 * @param document
	 */
	public WriteKml(String fileName, Document document) {
		this.fileName = fileName + ".kml";
		this.document = document;
	}

	/**
	 * The method check the data, by the id.
	 * @param array.
	 * @exception InputException : printStackTrace.
	 */
	@Override
	public void receiveData(ArrayList<SampleScan> array) {
		writeHeader();
		for (SampleScan sampleScan : array)
			KmlUtil.addPlacemark(sampleScan, document);
		writeFile();
	}

	/**
	 * Initialisation of the kml file, write the links with the icons.
	 */
	@Override
	public void writeHeader() {
		KmlUtil.addIcon("red", document);
		KmlUtil.addIcon("ylw", document);
		KmlUtil.addIcon("grn", document);
	}

	/**
	 * This method create the kml file.
	 * @exception IOException | {@link KmlException} : Error writing the file.
	 */
	@Override
	public void writeFile() {
		try {
			Kml kml = new Kml();
			kml.setFeature(document);
			kml.createKml(fileName);
		}
		catch(IOException | KmlException | NullPointerException ex) {
			System.out.println("Error writing the file." + ex);
		}
	}

	/**
	 * @return fileName.
	 */
	@Override
	public String getFileName() {
		return fileName;
	}
}
