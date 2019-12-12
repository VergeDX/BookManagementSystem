import com.j256.ormlite.logger.Log;

import java.util.List;
import java.util.Scanner;

public class Example {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.setProperty("com.j256.ormlite.logger.level", Log.Level.WARNING.toString());

        // Init database first!
        Database.initDatabase();
        System.out.println("Inserting Books \"Think in Java\"...");
        Database.insertBooks(Books.EXAMPLE_BOOKs);

        // Insert Books
        System.out.print("Please input ISBN you want to insert: ");
        String ISBN = scanner.nextLine();
        if (Database.hasISBN(ISBN)) {
            System.out.println("The ISBN already exist!");
        } else {
            System.out.println("Can't found this ISBN!");
        }

        // Get all Books
        List<Books> allBooks = Database.getAllBooks();
        System.out.println("All Books information: ");
        for (Books books : allBooks) {
            System.out.println(books.getAllInformation());
        }

        // Query for a Books
        System.out.print("Please input the Books name you want to query: ");
        String name = scanner.nextLine();
        List<Books> resultBooks = Database.queryBooks("name", name);
        if (resultBooks.isEmpty()) {
            System.out.println("Can't found this Books name!");
        } else {
            System.out.println("This book's ISBN is: " + resultBooks.get(0).getISBN());
        }

        // Insert a Books
        System.out.println("Inserting the example Books...");
        Books books =
                new Books("9787111612728",
                        "Effective Java（第 3 版）",
                        "机械工业 出版社",
                        "Joshua Bloch",
                        806,
                        77.00);
        if (Database.insertBooks(books)) {
            System.out.println("Insert successfully!");
        } else {
            System.out.println("Can't insert this book!");
        }
    }
}
