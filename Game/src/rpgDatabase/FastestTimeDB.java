package rpgDatabase;

import java.sql.*;
import java.util.logging.*;

/**
 * Creates, reads and writes to a database table where names and times are
 * stored
 *
 * @author Liam O'Connor, Daniel Willis, Aidan Lacey ID: 18048495, 19079994,
 * 17994308
 */
public class FastestTimeDB {

    //Sets connections attributes
    String url = "jdbc:derby://localhost:1527/TimesDB;create=true";
    String username = "rpg";
    String password = "rpg";
    Connection conn;

    //Sets core functionality attributes
    private String FastestPlayerName;
    private int fastestTime = 1000000;
    final String TableName = "TIMES";

    /**
     * Establishes a connection to the database
     */
    public void establishConnection() {
        try {
            //Connect to the Database
            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException ex) {
            Logger.getLogger(FastestTimeDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Creates the database table 'TIMES' if it doesn't already exist
     */
    public void createTable() {
        try {
            //Get a resultset of tables with the name TIMES to see if the table already exists
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "TIMES", null);

            //If the resultset is empty (The table doesn't exist), create the table
            if (!rs.next()) {
                Statement statement = conn.createStatement();
                //Create the TIMES table
                String sqlCreate = "create table " + TableName + " (Name varchar(20), Seconds int)";
                statement.executeUpdate(sqlCreate);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FastestTimeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the data from the TIMES table and compares the fastest time and the
     * gathered time, if the gathered time is faster, store the name and time as
     * the fastest name and time
     */
    public void transferFastestTimeData() {
        try {

            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = statement.executeQuery("SELECT NAME, SECONDS FROM TIMES");

            rs.beforeFirst();
            //While the datatable has more results to check
            while (rs.next()) {
                //Get the name and time from the table
                String name = rs.getString("name");
                int seconds = rs.getInt(2);
                /*If the gathered time is faster than the stored fastest time, 
                  overwrite the fastest time with the gathered time*/
                if (seconds < getFastestTime()) {
                    setFastestTime(seconds);
                    setFastestPlayerName(name);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(FastestTimeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inserts the players data into the TIMES table
     *
     * @param name The players name as a String
     * @param seconds The players time in seconds as an int
     */
    public void insertPlayerData(String name, int seconds) {
        try {

            Statement statement = conn.createStatement();
            //Inserts the players data into the table
            String sqlInsert = "insert into " + TableName + " values('" + name + "', " + seconds + ")";
            statement.executeUpdate(sqlInsert);

        } catch (SQLException ex) {
            Logger.getLogger(FastestTimeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Close the connection to the database
     */
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(FastestTimeDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Get the name of the player with the fastest time
     *
     * @return Name of the player as a String
     */
    public String getFastestPlayerName() {
        return FastestPlayerName;
    }

    /**
     * Set the name of the player with the fastest time
     *
     * @param name Name of the player as a String
     */
    public void setFastestPlayerName(String name) {
        this.FastestPlayerName = name;
    }

    /**
     * Get the fastest time
     *
     * @return Fastest time as an int
     */
    public int getFastestTime() {
        return fastestTime;
    }

    /**
     * Set the fastest time
     *
     * @param fastestTime Fastest time as an int
     */
    public void setFastestTime(int fastestTime) {
        this.fastestTime = fastestTime;
    }
}
