package factory;

import Utils.*;
import model.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static factory.DeveloperFactory.createDeveloper;

public class EmployeeFactory {

    public static Employee createEmployee(List<Employee> employees, AtomicInteger idGen) throws IllegalArgumentException {
        EmployeePayload payload = new EmployeePayload(idGen);

        payload.setNameAndSurname();
        payload.setYearsOfExperience();
        payload.setClassType(Utils.getEmployeeTypesMap());

        if (Utils.employeeTypeInDB(employees, "Admin") && payload.classType.equals("Admin")) {
            System.out.println("You can't have two admins!");
            return null;
        }

        if (Utils.employeeTypeInDB(employees, "Manager") && payload.classType.equals("Manager")) {
            System.out.println("You can't have two managers!");
            return null;
        }

        switch (payload.classType) {
            case "java_dev":
            case "py_dev":
            case "go_dev":
                return createDeveloper(payload);
            case "manager":
                return createManager(payload, employees);
            case "admin":
                return createAdmin(payload);
        }
        return null;
    }

    private static Admin createAdmin(EmployeePayload payload) throws IllegalArgumentException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please provide secret key bit size:");
        int keyBitSize = Utils.getIntFromStringInput(scan.nextLine());
        System.out.println("Please provide encryption algorithm name:");
        String algName = scan.nextLine();
        Admin admin;
        try {
            admin = new Admin(payload, keyBitSize, algName);
            admin.setPassword();
            return admin;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("You have provided wrong encryption algorithm name!");
        }
    }

    private static Manager createManager(EmployeePayload payload, List<Employee> employees) {
        Scanner scan = new Scanner(System.in);
        Manager manager = new Manager(payload);
        System.out.printf("Please provide initial employees IDs under %s,\n" +
                "If you wish not to, simply press [ENTER].\n", manager.getName());
        String input;
        while (true) {
            input = scan.nextLine();
            if (input.isBlank()) {
                break;
            }
            try {
                int id = Utils.getIntFromStringInput(input);
                manager.appendOneToManagedEmployees(employees, id);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return manager;
    }
}
