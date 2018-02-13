package objects;

/**
 * This class represents a wifi of the database.
 *
 * @author Orel and Samuel.
 */
public class LineDataBaseWifi {

    private String mac;
    private String signal;

    /**
     * Constructor.
     *
     * @param mac
     * @param signal
     */
    public LineDataBaseWifi(String mac, String signal) {
        this.mac = mac;
        this.signal = signal;
    }

    /**
     * @return mac.
     */
    public String getMac() {
        return mac;
    }

    /**
     * @return signal.
     */
    public String getSignal() {
        return signal;
    }

}
