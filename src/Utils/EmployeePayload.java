package Utils;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class EmployeePayload {
    public int id;
    public String name;
    public String surname;
    public String classType;
    public int yearsOfExperience;

    public EmployeePayload(AtomicInteger id) {
        this.id = id.incrementAndGet();
    }

    public void setNameAndSurname() throws IllegalArgumentException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Provide name and surname:");
        String credentials = scan.nextLine();
        String[] credentialsSplit = credentials.split(" ", 2);
        if (credentialsSplit.length == 2) {
            this.name = credentialsSplit[0];
            this.surname = credentialsSplit[1];
        } else {
            throw new IllegalArgumentException("You have to provide both name and surname!");
        }
    }

    public void setYearsOfExperience() throws IllegalArgumentException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Provide years of experience:");
        int input = Utils.getIntFromStringInput(scan.nextLine());
        if (input < 1 || input > 70) {
            throw new IllegalArgumentException("That's not a reasonable number for experience...");
        }
        this.yearsOfExperience = input;
    }

    public void setClassType(Map<String, String> employeeTypes) throws IllegalArgumentException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose one of the following types:");
        printClassTypes(employeeTypes);
        String classType = scan.nextLine();
        if (employeeTypes.get(classType) == null) {
            throw new IllegalArgumentException("That's not a valid employee type!");
        }
        this.classType = classType;
    }

    private static void printClassTypes(Map<String, String> employeeTypes) {
        for (Map.Entry<String, String> pair : employeeTypes.entrySet()) {
            System.out.printf(" - %s -- %s\n", pair.getKey(), pair.getValue());
        }
    }
}
