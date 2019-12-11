import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {
    private static Dao<Books, String> ISBNDao = null;

    public static final Books EXAMPLE_BOOKs = new Books("ISBN", "name", "press", "author", 0, 0.00);

    /**
     * Init Database and Table Books if not exist.
     * If an exception occurs, Print stack trace and exit with -2.
     */
    public static void initDatabase() {
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:Books.db");
            TableUtils.createTableIfNotExists(connectionSource, Books.class);
            ISBNDao = DaoManager.createDao(connectionSource, Books.class);
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
            resultBooks = ISBNDao.queryForId(ISBN);
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
                ISBNDao.create(books);
            } catch (SQLException e) {
                System.out.println("Exception in insert new Books, message: " + e.getMessage());

                return false;
            }
        }

        return true;
    }
}
