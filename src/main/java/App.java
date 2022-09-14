import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.sql.DriverManager.getConnection;

public class App {
    //static Connection con;

    public static void run() {
        try (Connection con = getNewConnection()) {

            write();
            read();

            int result = executeUpdate(con, "insert into tst_table1 values (2, 5654)");
            ResultSet resultSet = executeSelect(con, "select * from tst_table1");
            if (resultSet.next()) {
                //Stream stream = Arrays.stream(resultSet.getArray(2));
                //stream.forEach(x -> System.out.println(x));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void write() {
        System.out.println("Записываем в БД...");
    }

    private static void read() {
        System.out.println("Читаем из БД...");
        //return rs;
    }

    private static Connection getNewConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "root";
        return getConnection(url, user, password);
    }

    private static int executeUpdate(Connection con, String query) throws SQLException {
        Statement statement = con.createStatement();
        //Insert, Update, Delete
        int result = statement.executeUpdate(query);
        return result;
    }

    private static ResultSet executeSelect(Connection con, String query) throws SQLException {
        Statement statement = con.createStatement();
        //Select
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    private static void createCustomerTable() {

    }
}
