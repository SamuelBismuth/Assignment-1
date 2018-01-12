package runs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 * Created by Samuel on 11/01/2018.
 */

public class DataBaseObserver extends Observable {

    private String password;
    private String user;
    private String server;
    private String table;
    private String url;

    private static Connection _con = null;

    private String observableDate;

    public DataBaseObserver(String port, String password, String ip, String user, String server, String table) {
        this.password = password;
        this.user = user;
        this.server = server;
        this.table = table;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + server + "?autoReconnect=true&useSSL=false";
    }

    public void startService() {
        while (true) {
            try {
                Statement st = null;
                ResultSet rs = null;
                Class.forName("com.mysql.jdbc.Driver");
                _con = DriverManager.getConnection(url, user, password);
                st = _con.createStatement();
                rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '" + server + "' AND TABLE_NAME = '" + table + "'");
                if (rs.next())
                    System.out.println("**** Update: " + rs.getString(1));
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
}
