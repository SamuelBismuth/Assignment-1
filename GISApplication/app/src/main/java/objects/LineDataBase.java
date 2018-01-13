package objects;

import java.util.ArrayList;

/**
 * This class represents a line of the database.
 *
 * @author Orel and Samuel.
 */
public class LineDataBase {

    private String date;
    private String Id;
    private String Latitude;
    private String Longitude;
    private String altitude;
    private String wifiNetworks;
    private ArrayList<LineDataBaseWifi> arrayWifi;

    /**
     * Constructor.
     *
     * @param date
     * @param id
     * @param latitude
     * @param longitude
     * @param altitude
     * @param wifiNetworks
     * @param arrayWifi
     */
    public LineDataBase(String date, String id, String latitude, String longitude, String altitude, String wifiNetworks, ArrayList<LineDataBaseWifi> arrayWifi) {
        this.date = date;
        Id = id;
        Latitude = latitude;
        Longitude = longitude;
        this.altitude = altitude;
        this.wifiNetworks = wifiNetworks;
        this.arrayWifi = arrayWifi;
    }

    /**
     * @return date.
     */
    public String getDate() {
        return date;
    }

    /**
     * @return id.
     */
    public String getId() {
        return Id;
    }

    /**
     * @return latitude.
     */
    public String getLatitude() {
        return Latitude;
    }

    /**
     * @return longitude.
     */
    public String getLongitude() {
        return Longitude;
    }

    /**
     * @return altitude.
     */
    public String getAltitude() {
        return altitude;
    }

    /**
     * @return wifi networks.
     */
    public String getWifiNetworks() {
        return wifiNetworks;
    }

    /**
     * @return arrayWifi.
     */
    public ArrayList<LineDataBaseWifi> getArrayWifi() {
        return arrayWifi;
    }

}
