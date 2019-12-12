import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (!Login.login()) {
            System.exit(-1);
        }

        Database.initDatabase();
        Database.insertBooks(Books.EXAMPLE_BOOKs);

        List<Books> allBooks = Database.getAllBooks();
        for (Books books : allBooks) {
            System.out.println(books.getISBN());
        }

        /*
        System.out.print("Please input ISBN you want to insert: ");
        String ISBN = scanner.nextLine();
        if (Database.hasISBN(ISBN)) {
            System.out.println("The ISBN already exist!");
        }
        */
    }
}
