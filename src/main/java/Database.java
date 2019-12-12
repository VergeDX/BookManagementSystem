import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class Database {
    private static Dao<Books, String> BooksDao = null;

    /**
     * Init Database and Table Books if not exist.
     * If an exception occurs, Print stack trace and exit with -2.
     */
    public static void initDatabase() {
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:Books.db");
            TableUtils.createTableIfNotExists(connectionSource, Books.class);
            BooksDao = DaoManager.createDao(connectionSource, Books.class);
        } catch (SQLException e) {
            System.out.println("Exception in init database, Exiting...");
            System.out.println("Message: " + e.getMessage());

            System.exit(-2);
        }
    }

    /**
     * Check the given ISBN if is duplicate.
     *
     * @param ISBN is ready to check.
     * @return true if duplicate (should'n insert).
     */
    public static boolean hasISBN(String ISBN) {
        Books resultBooks = null;
        try {
            resultBooks = BooksDao.queryForId(ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultBooks != null;
    }

    /**
     * Insert a new Books, it will search for duplicate.
     *
     * @param books is ready to insert.
     * @return true if success.
     */
    public static boolean insertBooks(Books books) {
        if (hasISBN(books.getISBN())) {
            return false;
        } else {
            try {
                BooksDao.create(books);
            } catch (SQLException e) {
                System.out.println("Exception in insert new Books, message: " + e.getMessage());

                return false;
            }
        }

        return true;
    }

    /**
     * Query for all books, and return a List<Books>.
     *
     * @return a list contains all Books.
     */
    public static List<Books> getAllBooks() {
        try {
            return BooksDao.queryForAll();
        } catch (SQLException e) {
            System.out.println("Exception in querying all books, message: " + e.getMessage());
        }

        throw new AssertionError();
    }
}
