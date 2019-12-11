import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (!Login.login()) {
            System.exit(-1);
        }

        Database.initDatabase();
        Database.insertBooks(Database.EXAMPLE_BOOKs);

        System.out.print("Please input ISBN you want to insert: ");
        String ISBN = scanner.nextLine();
        if (Database.hasISBN(ISBN)) {
            System.out.println("The ISBN already exist!");
        }
    }
}
