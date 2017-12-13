package assignment;

/**
 * This class implements @see {@link Comparable} with @see {@link Line} as a parameter.
 * This class represents a non modificated object line from the csv file brut.
 * @author Orel and Samuel.
 */
public class Line implements Comparable<Line> {
	
	private String mac;
	private String ssid;
	private String authMode;
	private String firstseen;
	private String channel;
	private String rssi;
	private String currentLatitude;
	private String currentLongitude;
	private String altitudeMeters;
	private String accuracyMeters;
	private String type;
	private String id;

	/**
	 * Constructor
	 * @param mac
	 * @param ssid
	 * @param authMode
	 * @param firstseen
	 * @param channel
	 * @param rssi
	 * @param currentLatitude
	 * @param currentLongitude
	 * @param altitudeMeters
	 * @param accuracyMeters
	 * @param type
	 */
	public Line(String mac, String ssid, String authMode, String firstseen, String channel, String rssi,
			String currentLatitude, String currentLongitude, String altitudeMeters, String accuracyMeters,
			String type, String id) {
		this.mac = mac;
		this.ssid = ssid;
		this.authMode = authMode;
		this.firstseen = firstseen;
		this.channel = channel;
		this.rssi = rssi;
		this.currentLatitude = currentLatitude;
		this.currentLongitude = currentLongitude;
		this.altitudeMeters = altitudeMeters;
		this.accuracyMeters = accuracyMeters;
		this.type = type;
		this.id = id;
	}

	/** 
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}

	/** 
	 * @return ssid
	 */
	public String getSsid() {
		return ssid;
	}

	/** 
	 * @return authMode
	 */
	public String getAuthMode() {
		return authMode;
	}

	/** 
	 * @return firstseen
	 */
	public String getFirstseen() {
		return firstseen;
	}

	/** 
	 * @return channel
	 */
	public String getChannel() {
		return channel;
	}

	/** 
	 * @return rssi
	 */
	public String getRssi() {
		return rssi;
	}

	/** 
	 * @return currentLatitude
	 */
	public String getCurrentLatitude() {
		return currentLatitude;
	}

	/** 
	 * @return currentLongitude
	 */
	public String getCurrentLongitude() {
		return currentLongitude;
	}

	/** 
	 * @return altitudeMeters
	 */
	public String getAltitudeMeters() {
		return altitudeMeters;
	}

	/** 
	 * @return accuracyMeters
	 */
	public String getAccuracyMeters() {
		return accuracyMeters;
	}

	/** 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * This method compare the mac address.
	 * @param line.
	 */
	public int compareTo(Line line) {
		return this.mac.compareTo(line.getMac());
	}

	@Override
	public String toString() {
		return "Line [mac=" + mac + ", ssid=" + ssid + ", authMode=" + authMode + ", firstseen=" + firstseen
				+ ", channel=" + channel + ", rssi=" + rssi + ", currentLatitude=" + currentLatitude
				+ ", currentLongitude=" + currentLongitude + ", altitudeMeters=" + altitudeMeters + ", accuracyMeters="
				+ accuracyMeters + ", type=" + type + ", id=" + id + "]";
	}

}
