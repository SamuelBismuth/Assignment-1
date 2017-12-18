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
 */
public abstract class WriteKml implements WriteFile<SampleScan> {

	private Document document;
	private String fileName;

	public WriteKml(String fileName, Document document) {
		this.fileName = fileName + ".kml";
		this.document = document;
	}

	/**
	 * abstract method, we define it in the other classes.
	 */
	public abstract void receiveData(ArrayList<SampleScan> array);

	/**
	 * Initialisation of the kml file, we need to write the links with the icons.
	 */
	public void writeHeader() {
		KmlUtil.addIcon("red", document);
		KmlUtil.addIcon("ylw", document);
		KmlUtil.addIcon("grn", document);
	}

	/**
	 * This method create the kml file.
	 * @param fileNameExport.
	 * @exception IOException | {@link KmlException} : Error writing the file.
	 */
	public void writeFile() throws InputException {
		try {
			Kml kml = new Kml();
			kml.setFeature(document);
			kml.createKml(fileName);
		}
		catch(IOException | KmlException | NullPointerException ex) {
			System.out.println("Error writing the file." + ex);
			throw new InputException("There is no placemark in the document.");
		}
	}

	/**
	 * @return fileName.
	 */
	public String getFileName() {
		return fileName;
	}
}
