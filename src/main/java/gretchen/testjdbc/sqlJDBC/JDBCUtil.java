package gretchen.testjdbc.sqlJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/Online_store";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sql";

    private static Connection connection;

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()){
                try {
                    Class.forName(DB_DRIVER);
                    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    System.out.println("Connection OK");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    System.out.println("Connection ERROR");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
