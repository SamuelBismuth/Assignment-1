package algorithms;

import org.boehn.kmlframework.coordinates.EarthCoordinate;

public class Difference {

	private EarthCoordinate diff;
	String macName;
	
	public Difference(EarthCoordinate diff, String macName) {
		this.diff = diff;
		this.macName = macName;
	}

	/**
	 * @return the diff
	 */
	public EarthCoordinate getDiff() {
		return diff;
	}

	/**
	 * @param diff the diff to set
	 */
	public void setDiff(EarthCoordinate diff) {
		this.diff = diff;
	}

	/**
	 * @return the macName
	 */
	public String getMacName() {
		return macName;
	}

	/**
	 * @param macName the macName to set
	 */
	public void setMacName(String macName) {
		this.macName = macName;
	}
	
}
