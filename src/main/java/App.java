import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.sql.DriverManager.getConnection;

public class App {
    public static String SELECT_ALL_QUERY = "select * from tst_table1"; //" where number1 - number2 > 16497780";
    public static String TRUNCATE_QUERY = "truncate tst_table1";
    public static String UPDATE_QUERY = "update tst_table1 set number2 = ";

    public static void run() {
        //showInterface();


        try (Connection con = getNewConnection()) {
            //write();
            //read();

            int n = 10000;
            //executeInsertRandomNumber(con, n);
            //for (int i = 0; i < 1122545; i++) {
            //    executeUpdate(con, UPDATE_QUERY + "'" + (int) (Math.random() * 50000000) + "' where id = '" + i + "'");
            //}
            ArrayList<Entry> entries = new ArrayList<>();
            ResultSet resultSet = executeSelect(con, SELECT_ALL_QUERY);
            while (resultSet.next()) {
                Entry entry = new Entry(resultSet.getInt("id"), resultSet.getInt("number1"), resultSet.getInt("number2"));
                entries.add(entry);
                //System.out.println("Выборка: ");
                //System.out.println(resultSet.getInt(1));
            }
            entries.forEach(entry -> System.out.println(entry.printEntry()));
            //Stream<Entry> stream = entries.stream();
            //stream.forEach(x -> x % 2 == 1) System.out.println(x.printEntry()));

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

    private static void executeInsertRandomNumber(Connection con, int n) throws SQLException {
        //executeUpdate(con, TRUNCATE_QUERY);
        String query = "insert into tst_table1 values(?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        for (int i = 0; i < n; i++) {
            ps.setInt(1, i);
            ps.setInt(2, (int) (Math.random() * n / 2));
            ps.setInt(3, (int) (Math.random() * n / 2));
            ps.addBatch();
        }
        ps.executeBatch();
    }
}
