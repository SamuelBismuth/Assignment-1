package sql;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

import objects.LineDataBase;
import objects.LineDataBaseWifi;

/**
 * This class read the data from mysql database and sent him into our database.
 *
 * @author Orel and Samuel.
 */
public class MySql {

    /**
     * All the data that the user need to put to be connect with the data base.
     * port = 3306
     * user = oop1
     * password = Lambda1();
     * ip = 5.29.193.52
     * table = ex4_db...
     * server = oop_course_ariel...
     */

    private static Connection _con = null;

    /**
     * This function try to connect with the mysal database, and if it's a success, enter the data into our database.
     *
     * @param port
     * @param password
     * @param ip
     * @param user
     * @param table
     * @param server
     * @return array list of line data base.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ArrayList<LineDataBase> pickFromDataBase(String port, String password, String ip, String user, String table, String server) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + server + "?useSSL=false";
        return db(password, url, user, table, server);
    }

    /**
     * The base of this function is taken from the github of our Professor Boaz.
     * Link : https://github.com/benmoshe/OOP_Exe/blob/master/src/db/MySQL_101.java
     *
     * @param _password
     * @param _url
     * @param _user
     * @param table
     * @return ArrayList of {@link LineDataBase}.
     */
    private static ArrayList<LineDataBase> db(String _password, String _url, String _user, String table, String server) throws ClassNotFoundException, SQLException {
        ArrayList<LineDataBase> array = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        Class.forName("com.mysql.jdbc.Driver");
        _con = DriverManager.getConnection(_url, _user, _password);
        st = _con.createStatement();
        rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '" + server + "' AND TABLE_NAME = '" + table + "'");
        if (rs.next())
            System.out.println("**** Update: " + rs.getString(1));
        PreparedStatement pst = _con.prepareStatement("SELECT * FROM " + table);
        rs = pst.executeQuery();
        while (rs.next())
            array.add(newLineDataBase(rs));
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (_con != null)
                _con.close();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(MySql.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
        return array;
    }

    /**
     * To read a line from the database.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private static LineDataBase newLineDataBase(ResultSet rs) throws SQLException {
        ArrayList<LineDataBaseWifi> arrayWifi = newLineDataBaseWifi(rs);
        return new LineDataBase(
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                arrayWifi
        );
    }

    /**
     * To fulfill the array of wifi.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private static ArrayList<LineDataBaseWifi> newLineDataBaseWifi(ResultSet rs) throws SQLException {
        ArrayList<LineDataBaseWifi> arrayWifi = new ArrayList<>();
        for (int i = 8; i <= 7 + 2 * rs.getInt(7); i++) {
            arrayWifi.add(new LineDataBaseWifi(
                    rs.getString(i++),
                    rs.getString(i)
            ));
        }
        return arrayWifi;
    }

}