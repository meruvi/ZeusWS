package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DAO {
    public static Connection open(Connection con, DataSource dsc) throws SQLException {
        if (con != null) {
            if (con.isClosed()) {
              con = dsc.getConnection();
            }
        }
        else {
            con = dsc.getConnection();
        }
        System.out.println("connection______:" + con);
        return con;
    }
  
    public static void close(Connection con) throws SQLException
    {
        if (con != null) {
            con.close();
        }
    }
}
