package org.hydev;

import com.j256.ormlite.logger.Log;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        // Mute debug message, insert example data, and init database.
        System.setProperty("com.j256.ormlite.logger.level", Log.Level.WARNING.toString());
        Database.initDatabase();

        Operate.Operation operation = Operate.selectOperation(scanner);
        System.out.println();
        switch (operation) {
            case INSERT:
                Operate.insertData(scanner);
                break;
            case DELETE:
                Operate.deleteData(scanner);
                break;
            case QUERY:
                Operate.queryData(scanner);
                break;
            case MODIFY:
                Operate.modifyData(scanner);
                break;
            case LIST_ALL:
                List<Books> allBooks = Database.getAllBooks();
                if (allBooks.isEmpty()) {
                    System.out.println("There is no data.");
                } else {
                    System.out.println("| ISBN | name | press | author | inventory | price |");
                    allBooks.forEach(System.out::println);
                }

                break;
            case EXIT:
                System.out.println("See ya, admin!");
                System.exit(0);
        }
    }
}
