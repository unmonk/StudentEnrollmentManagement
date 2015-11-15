package pkg430test;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionConfig
{

    public static Connection getConnection()
    {
        Connection connection = null;
        String URL = "xxx";
        String USER = "xxx";
        String PASS = "xxx";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

}