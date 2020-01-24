package Utils;

import model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Utils {

    public static int getIntFromStringInput(String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please provide a number!");
        }
    }

    public static Map<String, String> getEmployeeTypesMap() {
        return Map.of(
                "admin", "Admin",
                "manager", "Manager",
                "java_dev", "Java Developer",
                "py_dev", "Python Developer",
                "go_dev", "Golang Developer"
        );
    }

    public static boolean getBoolFromInput() throws IllegalArgumentException {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextBoolean()) {
            return scan.nextBoolean();
        } else if (scan.hasNext()) {
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                return true;
            } else if (input.equalsIgnoreCase("no")) {
                return false;
            }
        }
        throw new IllegalArgumentException("Please provide either true or false!");
    }

    public static boolean employeeTypeInDB(List<Employee> employees, String className) {
        return employees.stream().anyMatch(e -> e.getClass().getSimpleName().equals(className));
    }

    public static Employee getEmployeeOfType(List<Employee> employees, String className) {
        switch (className) {
            case "Admin":
                return employees.stream().filter(e -> e.getClass().getSimpleName().equals("Admin")).findFirst().get();
            case "Manager":
                return employees.stream().filter(e -> e.getClass().getSimpleName().equals("Manager")).findFirst().get();
        }
        return null;
    }

    public static void printTextFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/static/" + filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
