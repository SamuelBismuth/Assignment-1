package objects;

import java.util.ArrayList;

/**
 * This class represents the object csv file.
 *
 * @author Orel and Samuel.
 */
public class CsvFile {

    private String id;
    private ArrayList<WigleWifiLine> arrayWigleWifiLine;

    /**
     * Constructor.
     *
     * @param id
     * @param arrayWigleWifiLine
     */
    public CsvFile(String id, ArrayList<WigleWifiLine> arrayWigleWifiLine) {
        this.id = id;
        this.arrayWigleWifiLine = arrayWigleWifiLine;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @return arrayLine
     */
    public ArrayList<WigleWifiLine> getWigleWifiLine() {
        return arrayWigleWifiLine;
    }

}
