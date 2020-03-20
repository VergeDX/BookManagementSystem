package org.hydev;

import java.util.List;
import java.util.Scanner;

import static org.hydev.Database.QueryKey.inventory;
import static org.hydev.Database.QueryKey.price;

public class Operate {
    public static Operation selectOperation(Scanner scanner) {
        Operation operation = Operation.ERROR;
        while (operation == Operation.ERROR) {
            System.out.println("1. Insert data. \t 2. Delete data. ");
            System.out.println("3. Query data. \t\t 4. Modify data.");
            System.out.println("5. List all data. \t 0. Exit system.");
            System.out.print("Select an operation: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    operation = Operation.INSERT;
                    break;
                case "2":
                    operation = Operation.DELETE;
                    break;
                case "3":
                    operation = Operation.QUERY;
                    break;
                case "4":
                    operation = Operation.MODIFY;
                    break;
                case "5":
                    operation = Operation.LIST_ALL;
                    break;
                case "0":
                    return Operation.EXIT;

                default:
                    operation = Operation.ERROR;
                    System.out.println("\n! Input error, just input operation code and press \"Enter\". \n");
            }
        }

        return operation;
    }

    public static void insertData(Scanner scanner) {
        System.out.print("Input ISBN to insert: ");
        String inputISBN = scanner.nextLine();

        if (Database.hasISBN(inputISBN)) {
            System.out.println("\n ! This ISBN already in the database, here is the book's information: ");
            System.out.println(" " + Database.queryBooks(Database.QueryKey.ISBN.toString(), inputISBN).get(0));
            return;
        }

        System.out.println("\n Now input the book full information. \n");
        System.out.print("This book's name? ");
        String inputName = scanner.nextLine();
        System.out.print("This book's press? ");
        String inputPress = scanner.nextLine();
        System.out.print("This book's author? ");
        String inputAuthor = scanner.nextLine();
        try {
            System.out.print("This book's inventory? ");
            int inputInventory = Integer.parseInt(scanner.nextLine());
            System.out.print("This book's price? ");
            double inputPrice = Double.parseDouble(scanner.nextLine());

            Books books = new Books(inputISBN, inputName, inputPress, inputAuthor, inputInventory, inputPrice);

            System.out.println("\n This is book's information, enter \"YES\" to insert: ");
            System.out.println(" " + books);
            if (scanner.nextLine().equals("YES")) {
                Database.insertBooks(books);
                System.out.println(" Insert successfully. ");
            } else {
                System.out.println(" ! Will not insert data, cancel. ");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("\n ! inventory or price should be a number! ");
        }
    }

    public static void deleteData(Scanner scanner) {
        System.out.print("Only support delete by ISBN: ");
        String inputISBN = scanner.nextLine();

        List<Books> booksList = Database.queryBooks(Database.QueryKey.ISBN.toString(), inputISBN);
        if (booksList.isEmpty()) {
            System.out.println(" ! Cannot find any books via ISBN \"" + inputISBN + "\". ");
            return;
        }

        Books books = booksList.get(0);
        System.out.println(" ? Do you want delete this book? Input \"YES\" to delete: ");
        System.out.println(" " + books);

        String answer = scanner.nextLine();
        if (answer.equals("YES")) {
            Database.deleteBooks(books);
            System.out.println(" Delete successfully. ");
        } else {
            System.out.print(" Will not delete data, cancel.");
        }
    }

    public static void queryData(Scanner scanner) {
        System.out.print("Query by ISBN, name, press, author, inventory or price? ");

        String inputFieldName = scanner.nextLine();
        Database.QueryKey queryKey = Database.fieldStringToKey(inputFieldName);

        if (queryKey == Database.QueryKey.ERROR) {
            System.out.println(" ! \"" + inputFieldName + "\" is not a field name, exit. ");
            return;
        }

        System.out.print("Query field \"" + inputFieldName + "\" value: ");
        String value = scanner.nextLine();
        List<Books> queryResult = Database.queryBooks(queryKey.toString(), value);

        if (queryResult.isEmpty()) {
            System.out.println("\n ! Query result return nothing. ");
            return;
        }

        System.out.println("\n Result(s): ");
        queryResult.forEach(System.out::println);
    }

    public static void modifyData(Scanner scanner) {
        System.out.print("Insert book's ISBN to modify: ");
        String inputISBN = scanner.nextLine();
        if (Database.queryBooks(Database.QueryKey.ISBN.toString(), inputISBN).isEmpty()) {
            System.out.println("\n ! Cannot query any books, exit. ");
            return;
        }

        Books books = Database.queryBooks(Database.QueryKey.ISBN.toString(), inputISBN).get(0);
        System.out.println("Will modify " + books);

        System.out.print("Modify which field? ISBN, name, press, author, inventory or price? ");
        String inputFieldName = scanner.nextLine();
        Database.QueryKey queryKey = Database.fieldStringToKey(inputFieldName);

        if (queryKey == Database.QueryKey.ERROR) {
            System.out.println("\n ! \"" + inputFieldName + "\" is not a field name, exit. ");
            return;
        }

        System.out.print("Change it to: ");
        String value = scanner.nextLine();

        try {
            if (queryKey == inventory) {
                Integer inventory = Integer.parseInt(value);
                books.modify(queryKey, null, inventory, null);
            }
            if (queryKey == price) {
                Double price = Double.parseDouble(value);
                books.modify(queryKey, null, null, price);
            }

            return;
        } catch (NumberFormatException nfe) {
            System.out.println("");
        }

        books.modify(queryKey, value, null, null);
        System.out.println("Operation execute successfully. ");
    }

    enum Operation {INSERT, DELETE, QUERY, MODIFY, LIST_ALL, EXIT, ERROR}
}
