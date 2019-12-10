public class Books {
    private final static String INSERT_BASE = "INSERT INTO Books values (";

    private String name, press, ISBN, author;
    private int inventory;
    private double price;

    // @AllArgsConstructor
    public Books(String name, String press, String ISBN, String author, int inventory, double price) {
        this.name = name;
        this.press = press;
        this.ISBN = ISBN;
        this.author = author;

        this.inventory = inventory;
        this.price = price;
    }

    /**
     * Use books name, press, ISBN, author, inventory and price data,
     * generate the Insert SQL statement.
     *
     * @return the generated Insert SQL statement.
     */
    public String generateInsertSQL() {
        return INSERT_BASE + apostrophe(name) + apostrophe(press) + apostrophe(ISBN) + apostrophe(author) + inventory + ", " + price + ");";
    }

    /**
     * Make [string] -> ['string', ] to generate Insert SQL statement.
     *
     * @param string need to be convert.
     * @return converted string.
     */
    private String apostrophe(String string) {
        return "'" + string + "', ";
    }
}
