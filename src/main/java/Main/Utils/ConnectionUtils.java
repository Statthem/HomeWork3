package Main.Utils;

import Main.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtils {
    private static Connection connection;
    private static Statement statement;

    public static void doConnection() {


        String connectionURL = "jdbc:mysql://localhost/homework3";
        String user = "root";
        String pass = "root";

        try {
            connection = DriverManager.getConnection(connectionURL, user, pass);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement() {
        return statement;
    }
}
