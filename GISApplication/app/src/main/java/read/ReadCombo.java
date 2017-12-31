package read;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.gis.gisapplication.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.boehn.kmlframework.coordinates.EarthCoordinate;
import org.boehn.kmlframework.kml.Data;

import libraries.DataBase;
import libraries.Format;
import libraries.InputException;
import libraries.ParseDate;
import libraries.ReadFolder;
import objects.CsvFile;
import objects.SampleScan;
import objects.WeigthAverage;
import objects.Wifi;
import runs.CallableAlgorithm2;
import runs.CallableCast;

/**
 * This class extends @see {@link ReadCsv} and implements @see {@link Read}.
 * This class read a combo.
 * @author Orel and Samuel.
 * @param SampleScan.
 * @param Wifi.
 */
public class ReadCombo extends ReadCsv<SampleScan> implements ReadFile<Wifi> {

	private static Activity activity;
	/**
	 * Constructor.
	 * @param filePath
	 * @param array
	 */
	public ReadCombo(String filePath, ArrayList<SampleScan> array, Activity activity) {
		super(filePath, array);
		this.activity = activity;
	}

	/**
	 * This method reads the file from @see {@link ReadFolder} and put into an array list the data we need.
	 * We use here the API commons-csv.
	 * Attention : tu run with the API you need to import him into the project @see README.
	 * @exception IOException : print error reading file.
	 */
	@Override
	public void readBuffer() {
		try {
			Boolean flag = false;
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(HeaderCombo.class).parse(br);
			for (CSVRecord record : records) {
				if(Format.goodLine(record)) {
					if (Format.noGps(record.toString()))
						flag = true;
					ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();
					for (int i = 0; i < Integer.parseInt(record.get(HeaderCombo.wifiNetworks)); i++) 
						arrayWifi.add(inputObject(record, Integer.toString(i)));
					array.add(newSampleScan(record, arrayWifi));
				}
			}
			if (flag) {
				algo2();
				return;
			}
			br.close();
		}
		catch(IOException ex) { 
			System.out.println("Error reading file : " + ex);
			System.exit(0);
		}
	}

	private void algo2() {
		if (DataBase.getArraySampleScan().size() == 0) {
			array.clear();
			return;
		}
		ArrayList<WeigthAverage> arrayData = new ArrayList<WeigthAverage>();
		for (SampleScan data : DataBase.getArraySampleScan())
			arrayData.add(new WeigthAverage(data));
		DataBase.setArrayWeightAverage(arrayData);
		ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
		Future<ArrayList<SampleScan>> future = execut.submit(new CallableAlgorithm2(array, activity));
		while (!future.isDone());
		try {
			array = future.get();
		}
		catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		//Toast.makeText(activity, "The coordinates have been asses by the Algorithm 2!", Toast.LENGTH_SHORT).show();
	}

	/**
	 * This method input a new {@link SampleScan} into the array.
	 * @param record
	 * @param i
	 * @return {@link Wifi}.
	 */
	@Override
	public Wifi inputObject(CSVRecord record, String i) {
		int count = Integer.parseInt(i);
		if (record.get((count * 4) + 6).equals("")) count++;
		return new Wifi(
				record.get((count * 4) + 6),
				record.get((count * 4) +  7),
				Integer.parseInt(record.get((count * 4) + 8)),
				Double.parseDouble(record.get((count * 4) + 9))
				);
	}

	//private unimplemented methods

	/**
	 * This method create a new {@link SampleScan}.
	 * @param record
	 * @param array
	 * @return sampleScan.
	 * @exception NumberFormatException | {@link InputException} : error on the date.
	 */
	private SampleScan newSampleScan(CSVRecord record, ArrayList<Wifi> array) {
		try {
			return new SampleScan (  
					ParseDate.stringToDate(record.get(HeaderCombo.time)),
					record.get(HeaderCombo.id),
					new EarthCoordinate(
							Format.checkCoordinate(record.get(HeaderCombo.latitude)),
							Format.checkCoordinate(record.get(HeaderCombo.longitude)),
							Format.checkCoordinate(record.get(HeaderCombo.altitude))
							),
					array
					);
		} 
		catch (NumberFormatException | InputException e) {
			System.out.println("Error on the date. ");
			e.printStackTrace();
			return null;
		}
	}

}