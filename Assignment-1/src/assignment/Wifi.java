package assignment;

/**
 * This class defines a wifi network.
 * This class implement the interface {@link Comparable}.
 * @author Orel and Samuel.
 */

public class Wifi implements Comparable<Wifi> {
	
	private String name;
	private String mac;
	private int frequency;
	private int signal;
	
	/**
	 * Constructor
	 * @param name
	 * @param mac
	 * @param frequency
	 * @param signal
	 */
	public Wifi(String name, String mac, int frequency, int signal) {
		this.name = noName(name);
		this.mac = mac;
		this.frequency = frequency;
		this.signal = signal;
	}
	
	/**
	 * @return name
	 */
		protected String getName() {
		return name;
	}
	
	/**
	 * @return mac
	 */
	protected String getMac() {
		return mac;
	}
	
	/**
	 * @return frequence
	 */
	protected int getFrequency() {
		return frequency;
	}

	/**
	 * @return signal
	 */
	public int getSignal() {
		return signal;
	}

	/**
	 * @param name
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * This method compare the signal.
	 * @param wifi.
	 */
	public int compareTo(Wifi wifi) {
		return Integer.compare(this.signal, wifi.getSignal());
	}
	
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
