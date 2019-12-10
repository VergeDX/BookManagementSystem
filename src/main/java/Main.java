import Database.Database;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (!Login.login()) {
            System.exit(-1);
        }

        Database.initDatabase();
        Database.insertTestData();

        System.out.print("Please input ISBN: ");
        String ISBN = scanner.nextLine();
        if (!Database.checkISBN(ISBN)) {
            System.out.println("This ISBN is already exist.");
            System.exit(-3);
        }
    }
}
