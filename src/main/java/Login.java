import java.util.Scanner;

public class Login {
    // TODO: Edit default username and password here!
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    /**
     * Log in with the above username and password.
     * Users have 3 attempts.
     *
     * @return true if username and password are correct.
     */
    public static boolean login() {
        int times = 0;

        while (times != 3) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Please input your username: ");
            String username = scanner.nextLine();
            System.out.print("Please input your password: ");
            String password = scanner.nextLine();

            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                System.out.println("Welcome! " + USERNAME + ".");
                return true;
            } else {
                System.out.println("Wrong user name or password, please try again.");
                times++;
            }
        }

        System.out.println("You input 3 times error, system exit.");
        return false;
    }
}
