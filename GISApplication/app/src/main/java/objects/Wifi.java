package objects;

/**
 * This class defines a wifi network.
 * This class implement the interface {@link Comparable} with {@link Wifi} as a parameter.
 * @author Orel and Samuel.
 */

public class Wifi implements Comparable<Wifi> {

	private String name;
	private String mac;
	private int frequency;
	private double signal;

	/**
	 * Constructor.
	 * @param name.
	 * @param mac.
	 * @param frequency.
	 * @param signal.
	 */
	public Wifi(String name, String mac, int frequency, double signal) {
		this.name = noName(name);
		this.mac = mac;
		this.frequency = frequency;
		this.signal = signal;
	}
	
	//Getters and setters.

	/**
	 * @return name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return mac.
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @return frequency.
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * @return signal.
	 */
	public double getSignal() {
		return signal;
	}

	/**
	 * @param signal.
	 */
	public void setSignal(double signal) {
		this.signal = signal;
	}
	
	//Comparator.

	/**
	 * This method compare the signal.
	 * @param wifi.
	 */
	public int compareTo(Wifi wifi) {
		return Double.compare(this.signal, wifi.getSignal());
	}
	
	//Helped function

	/**
	 * The methpod checks if the name is empty.
	 * @param string.
	 * @return the name of the wifi.
	 */
	protected String noName(String wifi) {
		if (wifi.equals("")) return "No name";
		return wifi;
	}

}
