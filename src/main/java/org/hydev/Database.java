package org.hydev;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class Database {
    private static Dao<Books, String> booksDao = null;

    public static void initDatabase() {
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:Books.db");
            TableUtils.createTableIfNotExists(connectionSource, Books.class);
            booksDao = DaoManager.createDao(connectionSource, Books.class);
        } catch (SQLException e) {
            System.out.println("Exception in init database, Exiting...");
            System.out.println("Message: " + e.getMessage());

            System.exit(-2);
        }
    }

    public static boolean hasISBN(String ISBN) {
        Books resultBooks = null;
        try {
            resultBooks = booksDao.queryForId(ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultBooks != null;
    }

    public static void insertBooks(Books books) {
        if (hasISBN(books.getISBN())) {
            System.out.println("org.hydev.Books \"" + books.getName() + "\" in already in the database!");
        } else {
            try {
                booksDao.create(books);
            } catch (SQLException e) {
                System.out.println("Exception in insert new org.hydev.Books, message: " + e.getMessage());
            }
        }
    }

    public static List<Books> getAllBooks() {
        try {
            return booksDao.queryForAll();
        } catch (SQLException e) {
            System.out.println("Exception in querying all books, message: " + e.getMessage());
        }

        throw new AssertionError();
    }

    public static List<Books> queryBooks(String fieldName, String value) {
        try {
            return booksDao.queryForEq(fieldName, value);
        } catch (SQLException e) {
            System.out.println("Exception in query " + fieldName + " field, message: " + e.getMessage());
        }

        throw new AssertionError();
    }

    public static void deleteBooks(Books books) {
        try {
            booksDao.delete(books);
        } catch (SQLException e) {
            System.out.println(" ! Delete error, SQLException message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static QueryKey fieldStringToKey(String string) {
        Database.QueryKey queryKey = Database.QueryKey.ERROR;
        switch (string) {
            case "ISBN":
                queryKey = Database.QueryKey.ISBN;
                break;
            case "name":
                queryKey = Database.QueryKey.name;
                break;
            case "press":
                queryKey = Database.QueryKey.press;
                break;
            case "author":
                queryKey = Database.QueryKey.author;
                break;
            case "inventory":
                queryKey = Database.QueryKey.inventory;
                break;
            case "price":
                queryKey = Database.QueryKey.price;
                break;
        }
        return queryKey;
    }

    public static void updateData(Books books) {
        try {
            booksDao.update(books);
        } catch (SQLException e) {
            System.out.println(" ! Update error, SQLException message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    enum QueryKey {
        ISBN, name, press, author, inventory, price, ERROR
    }
}
