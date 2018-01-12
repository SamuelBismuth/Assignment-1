package objects;

import java.util.ArrayList;

/**
 * Created by Samuel on 11/01/2018.
 */

public class LineDataBase {

    private String date;
    private String Id;
    private String Latitude;
    private String Longitude;
    private String altitude;
    private String wifiNetworks;
    private ArrayList<LineDataBaseWifi> arrayWifi;

    public LineDataBase(String date, String id, String latitude, String longitude, String altitude, String wifiNetworks, ArrayList<LineDataBaseWifi> arrayWifi) {
        this.date = date;
        Id = id;
        Latitude = latitude;
        Longitude = longitude;
        this.altitude = altitude;
        this.wifiNetworks = wifiNetworks;
        this.arrayWifi = arrayWifi;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return Id;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public String getWifiNetworks() {
        return wifiNetworks;
    }

    public ArrayList<LineDataBaseWifi> getArrayWifi() {
        return arrayWifi;
    }

    @Override
    public String toString() {
        return "LineDataBase{" +
                "date='" + date + '\'' +
                ", Id='" + Id + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", altitude='" + altitude + '\'' +
                ", wifiNetworks='" + wifiNetworks + '\'' +
                arrayWifi.toString();
    }
}
