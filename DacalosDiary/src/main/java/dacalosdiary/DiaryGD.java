/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dacalosdiary;

/**
 *
 * @author Student
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DiaryGD {

    static final String DIARY_FILE = "diary_entries.txt";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        while (choice != 4) {
            System.out.println("MENU");
            System.out.println("1. Add Diary");
            System.out.println("2. Show All Diary");
            System.out.println("3. Show Diary");
            System.out.println("4. Exit");
            System.out.print("type here: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addDiaryEntry();
                        break;
                    case 2:
                        showAllDiaries();
                        break;
                    case 3:
                        showDiaryById();
                        break;
                    case 4:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
                System.out.println();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // Consume the invalid input
            }
        }

        input.close();
    }

    static void addDiaryEntry() {
        System.out.println("\nAdding New Diary Entry:");
        System.out.print("ID: ");
        String id = input.nextLine();
        System.out.print("Date (YYYY/MM/DD): ");
        String date = input.nextLine();
        System.out.println("Diary: (type '.' on a new line to finish)");
        StringBuilder content = new StringBuilder();
        String line;
        while (true) {
            line = input.nextLine();
            if (line.equals(".")) {
                break;
            }
            content.append(line).append("\n");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(DIARY_FILE, true))) {
            writer.println(id);
            writer.println(date);
            writer.println(content.toString().trim());
            System.out.println("Diary entry added successfully!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    static void showAllDiaries() {
        System.out.println("\nDIARIES");
        try (BufferedReader reader = new BufferedReader(new FileReader(DIARY_FILE))) {
            String id;
            while ((id = reader.readLine()) != null) {
                String date = reader.readLine();
                String content = reader.readLine();
                System.out.println("Diary " + id + " - " + date);
                System.out.println(content);
                System.out.println("----------");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    static void showDiaryById() {
        System.out.print("\nEnter Diary ID: ");
        String searchId = input.nextLine();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(DIARY_FILE))) {
            String id;
            while ((id = reader.readLine()) != null) {
                String date = reader.readLine();
                String content = reader.readLine();
                if (id.equals(searchId)) {
                    System.out.println("Diary " + id + " - " + date);
                    System.out.println(content);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Diary with ID " + searchId + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
