package cast;

import java.util.ArrayList;

import objects.CsvFile;
import objects.SampleScan;
import objects.Wifi;
import objects.WigleWifiLine;

/**
 * This class implements @see {@link Cast}.
 * This class cast the {@link ArrayList} of {@link SampleScan} to an {@link ArrayList} of {@link CsvFile}.
 * @author Orel and Samuel.
 */
public class CastFromSampleScanToCsvFile implements Cast<SampleScan, CsvFile> {
	
	/**
	 * Empty constructor.
	 */
	public CastFromSampleScanToCsvFile() {}

	/**
	 * This method cast a {@link SampleScan} object into a {@link CsvFile} object.
	 * @param arrayScan
	 * @return {@link ArrayList} of {@link CsvFile}.
	 */
	@Override
	public ArrayList<CsvFile> cast(ArrayList<SampleScan> arrayScan) {
		ArrayList<CsvFile> arrayCsv = new ArrayList<CsvFile>();
		for (SampleScan sampleScan : arrayScan) {
			ArrayList<WigleWifiLine> arrayWigleWifi = new ArrayList<WigleWifiLine>();
			for (Wifi wifi : sampleScan.getArrayStrongerWifi()) 
				arrayWigleWifi.add(newWigleWifiLine(sampleScan, wifi));
			arrayCsv.add(newCsvFile(sampleScan, arrayWigleWifi));
		}
		return arrayCsv;
	}
	
	//Private unimplementeds methods.

	/**
	 * Create a new {@link WigleWifiLine}.
	 * @param sampleScan.
	 * @param wifi.
	 * @return {@link WigleWifiLine}.
	 */
	private WigleWifiLine newWigleWifiLine(SampleScan sampleScan, Wifi wifi) {
		return new WigleWifiLine(
				wifi.getMac(), 
				wifi.getName(), 
				"authMode", 
				sampleScan.getTime().getTime().toString(), 
				Integer.toString(wifi.getFrequency()), 
				Double.toString(wifi.getSignal()), 
				Double.toString(sampleScan.getPointLocation().getLatitude()), 
				Double.toString(sampleScan.getPointLocation().getLongitude()), 
				Double.toString(sampleScan.getPointLocation().getAltitude()), 
				"Accuracy metter", 
				"WIFI", 
				sampleScan.getId()
				);
	}
	
	/**
	 * Create a new {@link CsvFile}.
	 * @param sampleScan.
	 * @param arrayWigleWifi.
	 * @return {@link CsvFile}.
	 */
	private CsvFile newCsvFile(SampleScan sampleScan, ArrayList<WigleWifiLine> arrayWigleWifi) {
		return new CsvFile(
				sampleScan.getId(),
				arrayWigleWifi
				);
	}
	
}