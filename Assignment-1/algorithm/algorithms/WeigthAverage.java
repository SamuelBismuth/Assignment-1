package algorithms;

import java.util.ArrayList;
import java.util.Comparator;

import read.SampleScan;
import read.Wifi;

/**
 * This class represents a weigth average object.
 * @author Orel and Samuel.
 */
public class WeigthAverage implements Comparable<WeigthAverage> {

	private SampleScan sampleScan;
	private double relevantNumber;
	private double pi;
	private ArrayList<Wifi> arrayWifi = new ArrayList<Wifi>();

	/**
	 * Constructor.
	 * @param sampleScan
	 */
	public WeigthAverage(SampleScan sampleScan) {
		this.sampleScan = sampleScan;
	}

	//Getters and setters.
	
	/**
	 * @return the sampleScan.
	 */
	public SampleScan getSampleScan() {
		return sampleScan;
	}

	/**
	 * @return the relevantNumber.
	 */
	public double getRelevantNumber() {
		return relevantNumber;
	}

	/**
	 * @param relevantNumber the relevantNumber to set.
	 */
	public void setRelevantNumber(double relevantNumber) {
		this.relevantNumber = relevantNumber;
	}

	/**
	 * @return the pi.
	 */
	public double getPi() {
		return pi;
	}

	/**
	 * @param pi the pi to set.
	 */
	public void setPi(double pi) {
		this.pi = pi;
	}

	/**
	 * @return the arrayWifi.
	 */
	public ArrayList<Wifi> getArrayWifi() {
		return arrayWifi;
	}

	/**
	 * @param arrayWifi the arrayWifi to set.
	 */
	public void addWifi(Wifi wifi) {
		this.arrayWifi.add(wifi);
	}
	
	//Comparator.
	
	/**
	 * Compare the relevant number.
	 */
	public int compareTo(WeigthAverage scan) {
		return Double.compare(scan.getRelevantNumber(), this.relevantNumber);
	}

	public static class Comparators {
	    public static final Comparator<WeigthAverage> PI = (WeigthAverage o1, WeigthAverage o2) -> Double.compare(o1.getPi(), o2.getPi());
	}
	
}