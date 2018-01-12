package objects;

/**
 * Created by Samuel on 11/01/2018.
 */

public class LineDataBaseWifi {

    private String mac;
    private String signal;

    public LineDataBaseWifi(String mac, String signal) {
        this.mac = mac;
        this.signal = signal;
    }

    public String getMac() {
        return mac;
    }

    public String getSignal() {
        return signal;
    }

}
