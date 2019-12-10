import Database.SQLCommand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:Books.db")) {
            Statement statement = connection.createStatement();
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            // If Books table is not exist, create it.
            ResultSet checkExist = databaseMetaData
                    .getTables(null, null, "Books", new String[]{"TABLE"});
            if (!checkExist.next()) {
                statement.execute(SQLCommand.INIT_BOOKS);
            }

            statement.execute(new Books("name", "press", "ISBN", "author", 0, 0.00).generateInsertSQL());

            ResultSet resultSet = statement.executeQuery(SQLCommand.QUERY_ALL);
            List<String> ISBNList = new ArrayList<>();
            while (resultSet.next()) {
                ISBNList.add(resultSet.getString("ISBN"));
            }
            System.out.println(ISBNList);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
