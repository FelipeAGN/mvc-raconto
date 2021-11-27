package DB;
import java.sql.*;

public class DataBase {

    public static Connection connection() {

        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            // create a database connection
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/identifier.sqlite");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            resultSet = statement.executeQuery("select * from product");
            while (resultSet.next()){
                System.out.println("Funciona la conexion a la base de datos");
                System.out.println("Products: " + resultSet.getString("name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.out.println("Error al conectar la base de datos");
        } /* finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }

        }
        */
        return connection;
    }
}