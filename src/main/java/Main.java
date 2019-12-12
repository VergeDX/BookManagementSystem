import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (!Login.login()) {
            System.exit(-1);
        }

        Database.initDatabase();
        Database.insertBooks(Books.EXAMPLE_BOOKs);
    }
}
