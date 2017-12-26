package threads;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import filter.Filtering;
import filter.FilteringCsvMac;
import libraries.User;
import objects.Mac;
import objects.WeigthAverage;
import read.ReadCombo;
import read.ReadCsv;
import read.SampleScan;
import read.SortWigleWifiMac;
import write.WriteCombo;
import write.WriteFile;

public class MainAlgo1Algo2 {

	public static void main(String[] args) {

		//Get workspace path
		String folderPathWorkspace = new File(".").getAbsolutePath();

		//Initialize the array

		//Algo1
		ArrayList<SampleScan> arrayScan =  new ArrayList<SampleScan>();
		ArrayList<Mac> arrayMac =  new ArrayList<Mac>();


		//Algo2
		ArrayList<SampleScan> arrayInput =  new ArrayList<SampleScan>();
		ArrayList<SampleScan> arrayDataScan =  new ArrayList<SampleScan>();
		ArrayList<WeigthAverage> arrayData =  new ArrayList<WeigthAverage>();
		ArrayList<WeigthAverage> arrayTemp = new ArrayList<WeigthAverage>();

		//  ==============================
		//  = BEGINNING OF THE ALGORITHM =
		//  ==============================

		/////////
		//READ.//
		/////////

		String path = folderPathWorkspace.substring(0, folderPathWorkspace.length() - 1);

		//Get file combo path Algo 1
		String fileComboName = User.getFileName("combo");

		//Get file input path Algo 2
		String fileInput = User.getFileName("combo input");

		//Get file data path Algo 2
		String fileData = User.getFileName("combo data");

		//Read the combo file Algo 1
		File combo = new File(fileComboName + ".csv");
		ReadCsv<SampleScan> readCombo = new ReadCombo(path, arrayScan, combo);

		//Read the input file Algo 2
		File inputFile = new File(fileInput + ".csv");
		ReadCsv<SampleScan> readInput = new ReadCombo(path, arrayInput, inputFile);

		//Read the database file
		File dataFile = new File(fileData + ".csv");
		ReadCsv<SampleScan> readData = new ReadCombo(path, arrayDataScan, dataFile);

		Thread threadAlgo1 = new Thread(new RunRead(readCombo, combo.toString()));
		Thread threadAlgo2Input = new Thread(new RunRead(readInput, inputFile.toString())); 
		Thread threadAlgo2Data = new Thread(new RunRead(readData, dataFile.toString())); 

		threadAlgo1.start();
		threadAlgo2Input.start();
		threadAlgo2Data.start();

		try {
			threadAlgo1.join();
			threadAlgo2Input.join();
			threadAlgo2Data.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		//////////////
		//Filtering.//
		//////////////

		//Algo 1

		//Sort combo (mac)
		SortWigleWifiMac sortMac = new SortWigleWifiMac();
		ExecutorService execut = (ExecutorService) Executors.newSingleThreadExecutor();
		Future<ArrayList<Mac>> future = execut.submit(new CallableSort<Mac, SampleScan>(sortMac, arrayScan, arrayMac));
				
		//Filtering the combo
		Filtering<Mac> filter = new FilteringCsvMac();	
		while (!future.isDone());
		try {
			arrayMac = future.get();
		} 
		catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		ExecutorService executFiltering = (ExecutorService) Executors.newSingleThreadExecutor();
		Future<WriteArray> futureWriteAlgo1 = executFiltering.submit(new CallableFiltering(filter, arrayMac));

		//Algo 2
		for (SampleScan data : arrayDataScan) 
			arrayData.add(new WeigthAverage(data));

		for (SampleScan input : arrayInput) {
			threadAlgo2Input = new Thread(new RunAlgorithm2(input, arrayData, arrayTemp));
			threadAlgo2Input.start();
			try {
				threadAlgo2Input.join();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Getter
		WriteFile<Mac> writeAlgo1 = null;
        try {
        	while (!futureWriteAlgo1.isDone());
			writeAlgo1 = futureWriteAlgo1.get().getWrite();
			arrayMac = futureWriteAlgo1.get().getArrayMac();
		} 
        catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
        
		//////////
		//Write.//
		//////////

		//Algo 2
		WriteFile<SampleScan> writeAlgo2 = new WriteCombo("Algo 2");

		threadAlgo1 = new Thread(new RunWriteCombo<Mac>(writeAlgo1, writeAlgo1.getFileName(), arrayMac));
		threadAlgo2Input = new Thread(new RunWriteCombo<SampleScan>(writeAlgo2, writeAlgo2.getFileName(), arrayInput));	
		
		threadAlgo1.start();
		threadAlgo2Input.start();
		
		try {
			threadAlgo1.join();
			threadAlgo2Input.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
}
