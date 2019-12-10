package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Connection connection = null;

    /**
     * Init Database.Database and Table Books if not exist.
     * If an exception occurs, Print stack trace and exit with -2.
     */
    public static void initDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Books.db");
            Statement statement = connection.createStatement();
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            // If Books table is not exist, create it.
            ResultSet checkExist = databaseMetaData
                    .getTables(null, null, "Books", new String[]{"TABLE"});
            if (!checkExist.next()) {
                statement.execute(SQLCommand.INIT_BOOKS);
            }

        } catch (SQLException e) {
            System.out.println("Error initializing database, Exiting...");
            System.out.println("Exception message is: " + e.getMessage());

            System.exit(-2);
        }
    }

    /**
     * Get database connection for query data.
     * If connection is null, throw an AssertException.
     * So it must call after call initDatabase().
     *
     * @return database connection.
     */
    private static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        throw new AssertionError("Database connection is not be initialized.");
    }

    /**
     * Check if the ISBN to be inserted is duplicate.
     *
     * @param ISBN is ready to check.
     * @return false if duplicate (should'n insert).
     */
    public static boolean checkISBN(String ISBN) {
        Connection connection = getConnection();
        List<String> ISBNList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLCommand.QUERY_ALL);

            while (resultSet.next()) {
                ISBNList.add(resultSet.getString("ISBN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String ISBNs : ISBNList) {
            if (ISBNs.equals(ISBN)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Insert a test data.
     */
    public static void insertTestData() {
        connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(SQLCommand.INSERT_TEST_BOOKS);
        } catch (SQLException e) {
            System.out.println("Insert Test Data Error!");
            e.printStackTrace();
        }

    }
}
