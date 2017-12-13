package assignment;

import java.util.ArrayList;
import java.util.Collections;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

public class LocalisationAlgo2 {
	
	private Scan input;
	private ArrayList<Scan> array;
	
	private final int NBOFMAC = 4;
	
	protected LocalisationAlgo2(Scan input, ArrayList<Scan> array) {
		this.input = input;
		this.array = array;
	}
	
	protected Scan newCoordinate(Scan input) {
		ArrayList<Scan> relevantScan = getRevelantScan();
		EarthCoordinate newLocalisation = newPointLocalisation(relevantScan);
		
		return input;
	}
	
	private EarthCoordinate newPointLocalisation(ArrayList<Scan> relevantScan) {
		
		return null;
	}

	private ArrayList<Scan> getRevelantScan() {
		ArrayList<Scan> relevantScan = new ArrayList<Scan>();
		for (Scan data : array) data.setRelevantNumber(input);
		Collections.sort(array);
		for (int i = 0; i < NBOFMAC; i++) 
			relevantScan.add(array.get(i));
		return relevantScan;
	}
	
 }
