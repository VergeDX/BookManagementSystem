package Database;

public class SQLCommand {
    public static final String INIT_BOOKS =
            "CREATE TABLE Books (" +
                    "name string, " +
                    "press string, " +
                    "ISBN string, " +
                    "author string, " +
                    "inventory integer, " +
                    "price double);";

    public static final String QUERY_ALL = "SELECT * FROM Books;";

    public static final String INSERT_TEST_BOOKS = "INSERT INTO Books values ('name', 'press', 'ISBN', 'author', 0, 0.0);";
}
