package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_connection {
    private static Connection connection;
    private Db_connection(){
        try {
            connection = DriverManager.getConnection(
                    System.getenv("db_url"),
                    System.getenv("db_user"),
                    System.getenv("db_password")
            );
            System.out.println("Connection will be connected successfuly");
        } catch (SQLException e) {
            System.out.println("connection with database error"+ e.getMessage());
        }
    }
    public  static Connection getConnection(){
        if(connection == null){
            new Db_connection();
        }

        return connection;
    }
}
