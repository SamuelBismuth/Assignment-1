package assignment1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.ExtendedData;
import org.boehn.kmlframework.kml.IconStyle;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.KmlException;
import org.boehn.kmlframework.kml.Placemark;
import org.boehn.kmlframework.kml.SimpleData;
import org.boehn.kmlframework.kml.Style;
import org.boehn.kmlframework.kml.TimeStamp;

/**
 * This absract class write a kml file.
 * This class use the API kmlframework @see {@link https://code.google.com/archive/p/kmlframework/source/default/source}.
 * This class implements @see {@link WriteFile}.
 * @see NOTICE for more informations about how to run with the api.
 * @author Orel and Samuel.
 */
public abstract class WriteKml implements WriteFile {

	private Document document = new Document();
	private String fileNameExport;

	/**
	 * Constructor.
	 */
	@SuppressWarnings("resource")
	public WriteKml() {
		System.out.println("Input a name for the kml file you want to create : ");
		this.fileNameExport = new Scanner(System.in).nextLine() + ".kml";
	}

	/**
	 * Initialisation of the kml file, we need to write the links with the icons.
	 */
	public void initialize() {
		addIcon("red");
		addIcon("ylw");
		addIcon("grn");
	}

	/**
	 * abstract method, we define it in the other classes.
	 */
	public abstract void checkData(ArrayList<Wifi> array);

	/**
	 * 
	 * @param array.
	 * @param wifi.
	 * @return false if the mac already appears.
	 * @return true otherwise.
	 */
	public boolean sameMac(ArrayList<Wifi> array, Wifi wifi) {
		try {
			for (int i = 0; !array.get(i).equals(wifi); i++)
				if(array.get(i).getMac().equals(wifi.getMac())) 
					return false;
			return true;
		}
		catch (IndexOutOfBoundsException ex) {
			//There is only one wifi in the array
			return true;
		}
	}

	/**
	 * This method construcs the placemark.
	 */
	public void addNetwork(Wifi wifi) {
		if(wifi.getName().contains("&")) wifi.setName(wifi.getName().replaceAll("&", "and"));
		Placemark placemark = new Placemark(wifi.getName());
		TimeStamp time = new TimeStamp(timeInput(wifi.getTime()));
		placemark.setTimePrimitive(time);
		placemark.setExtendedData(extendedData(wifi));
		placemark.setLocation(wifi.getPointLocation().getLongitude(), wifi.getPointLocation().getLatitude());
		placemark.setStyleUrl(color(wifi.getSignal()));
		document.addFeature(placemark);
	}

	/**
	 * This method crete the kml file.
	 * @exception IOException | {@link KmlException} : Error writing the file.
	 */
	public void createFile() throws InputException {
		Kml kml = new Kml();
		try {
			document.getFeatures().equals(null);
		}
		catch (NullPointerException ex ) {
			throw new InputException("There is no placemark in the document.");
		}
		kml.setFeature(document);
		try {
			kml.createKml(fileNameExport);
			new OpenFile(fileNameExport);
		}
		catch(IOException | KmlException ex) {
			System.out.println("Error writing the file." + ex);
		}
	}

	// unimplemented private methods.

	/**
	 * This method generate the data to input a new icon.
	 * @param color
	 */
	private void addIcon(String color) {
		Style style = new Style();
		style.setId(color);
		IconStyle iconStyle = new IconStyle();
		iconStyle.setColor(color);
		iconStyle.setIconHref("http://maps.google.com/mapfiles/kml/paddle/" + color + "-stars.png");
		style.setIconStyle(iconStyle);
		document.addStyleSelector(style);
	}

	/**
	 * This method generate the data.
	 * @param wifi.
	 * @return the extended data.
	 */
	private ExtendedData extendedData(Wifi wifi) {
		ArrayList<SimpleData> array = new ArrayList<SimpleData>();
		array.add(simpleData("Mac", wifi.getMac()));
		array.add(simpleData("Frequency", Integer.toString(wifi.getFrequency())));
		array.add(simpleData("Date", wifi.getTime().getTime().toString()));
		array.add(simpleData("Signal", Integer.toString(wifi.getSignal())));
		array.add(simpleData("Id", wifi.getId()));
		ExtendedData extendedData = new ExtendedData();
		extendedData.setSimpleDataElements(array);
		return extendedData;
	}

	/**
	 * @param name.
	 * @param value.
	 * @return simple data.
	 */
	private SimpleData simpleData(String name, String value) {
		SimpleData data = new SimpleData();
		data.setName(name);
		data.setValue(value);
		return data;
	}

	/**
	 * if signal > - 70 green icon, if signal > -90 yellow icon, else red icon.
	 * @param signal.
	 * @return the color.
	 */
	private String color(int signal) {
		if (signal > - 70) return "#grn";
		else if (signal > -90) return "#ylw";
		else return "#red";
	}

	/**
	 * @param time.
	 * @return yyyy-mm-ddThh:mm:ssZ.
	 */
	private String timeInput(GregorianCalendar time) {
		return Integer.toString(time.get(Calendar.YEAR)) + "-" + dataChange((time.get(Calendar.MONTH))) + "-" + 
				dataChange((time.get(Calendar.DATE))) + "T" + dataChange((time.get(Calendar.HOUR_OF_DAY))) 
				+ ":" + dataChange((time.get(Calendar.MINUTE))) + ":" + dataChange((time.get(Calendar.SECOND))) + "Z";
	}

	/**
	 * @param data.
	 * @return data if the data is already with two digits.
	 * @return 0 + data if the data got only one digit.
	 */
	private String dataChange(int data) {
		if (data / 10 >= 1) return Integer.toString(data);
		else return "0" + Integer.toString(data);
	}
}
