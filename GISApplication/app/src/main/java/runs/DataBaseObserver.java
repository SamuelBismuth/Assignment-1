package runs;

import com.gis.gisapplication.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cast.Cast;
import cast.CastFromLineDataBaseToSampleScan;
import libraries.DataBase;
import objects.LineDataBase;
import objects.SampleScan;
import sql.MySql;

/**
 * This class is run by a thread which check a possibly modification of the data in the mysql database.
 *
 * @author Orel and Samuel.
 */
public class DataBaseObserver {

    private String password;
    private String user;
    private String server;
    private String table;
    private String url;
    private String ip;
    private String port;
    private String currentDate = "null";
    private static Connection _con = null;

    /**
     * Constructor.
     *
     * @param port
     * @param password
     * @param ip
     * @param user
     * @param table
     * @param server
     */
    public DataBaseObserver(String port, String password, String ip, String user, String table, String server) {
        this.password = password;
        this.user = user;
        this.server = server;
        this.table = table;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + server + "?useSSL=false";
        this.ip = ip;
        this.port = port;
    }

    /**
     * This function start the service of the watch.
     */
    public void startService() {
        while (true) {
            try {
                Statement st = null;
                ResultSet rs = null;
                Class.forName("com.mysql.jdbc.Driver");
                _con = DriverManager.getConnection(url, user, password);
                st = _con.createStatement();
                rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '" + server + "' AND TABLE_NAME = '" + table + "'");
                if (rs.next()) {
                    if (currentDate.equals("null"))
                        currentDate = rs.getString(1);
                    String newDate =  rs.getString(1);
                    if (!currentDate.equals(newDate))
                        modifDetected();
                }
                Thread.sleep(5000);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This function is called if the data have been modified.
     */
    private void modifDetected() {
        DataBase.removeArraySampleScan(DataBase.getMapSql().get(table));
        DataBase.removeMapSql(table);
        ArrayList<LineDataBase> array = null;
        try {
            array = MySql.pickFromDataBase(
                    port,
                    password,
                    ip,
                    user,
                    table,
                    server
            );
        Cast cast = new CastFromLineDataBaseToSampleScan();
        ArrayList<SampleScan> arraySampleScan = cast.cast(array);
        DataBase.addMapSql(table, arraySampleScan);
        DataBase.addAllArraySampleScan(arraySampleScan);
        MainActivity.refreshDataBase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new DataBaseObserver(
                        port,
                        password,
                        ip,
                        user,
                        table,
                        server
                ).startService();
            }
        }).start();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
