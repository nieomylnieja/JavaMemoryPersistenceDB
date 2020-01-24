import utils.Utils;
import interfaces.DevelopersCheck;
import model.*;
import factory.EmployeeFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        AtomicInteger idGen = new AtomicInteger();
        Scanner scan = new Scanner(System.in);

        Utils.printTextFile("intro.txt");
        Utils.printTextFile("help.txt");

        while (true) {
            System.out.println("Provide a command:");
            String s = scan.nextLine();
            switch (s) {
                case "do":
                    performSpecialMethod(employees);
                    break;
                case "view_by_str":
                    viewByString(employees);
                    break;
                case "view_by_type":
                    viewEmployeesByType(employees);
                    break;
                case "view_all":
                    viewAll(employees);
                    break;
                case "delete":
                    deleteEmployee(employees);
                    break;
                case "new":
                    newEmployee(employees, idGen);
                    break;
                case "help":
                    Utils.printTextFile("help.txt");
                    break;
                case "exit":
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Wrong command name!");
                    break;
            }
        }
    }

    private static void performSpecialMethod(List<Employee> employees) {
        System.out.println("Executing employees check...");
        if (Utils.employeeTypeInDB(employees, "Manager")) {
            Manager manager = (Manager) Utils.getEmployeeOfType(employees, "Manager");
            employees.stream().filter(e -> DevelopersCheck.class.isAssignableFrom(e.getClass())).
                    forEach(e -> ((DevelopersCheck) e).checkDeveloper(manager));
            return;
        }
        System.out.println("No manager to perform the check!");
    }

    private static void viewByString(List<Employee> employees) throws SecurityException {
        if (Utils.employeeTypeInDB(employees, "Admin")) {
            adminVerification(employees);
        }
        System.out.println("Please provide a string or integer to search in the DB for:");
        Scanner scan = new Scanner(System.in);
        String searched = scan.nextLine();
        employees.stream().filter(e -> e.getName().contains(searched)).
                forEach(Employee::printEmployeeData);
    }

    private static void viewAll(List<Employee> employees) throws SecurityException {
        if (Utils.employeeTypeInDB(employees, "Admin")) {
            adminVerification(employees);
        }
        employees.forEach(Employee::printEmployeeData);
    }

    private static void adminVerification(List<Employee> employees) {
        Admin admin = (Admin) Utils.getEmployeeOfType(employees, "Admin");
        System.out.println("Since there's an admin on board, please provide password to access employee data:");
        admin.getPassword();
    }

    private static void viewEmployeesByType(List<Employee> employees) {
        System.out.println("Provide employee type:");
        Scanner scan = new Scanner(System.in);
        String className = scan.nextLine();
        try {
            Class selectedType = Class.forName("model." + className);
            employees.stream().filter(e -> selectedType.isAssignableFrom(e.getClass())).
                    forEach(Employee::printEmployeeData);
        } catch (ClassNotFoundException e) {
            System.out.printf("%s doesn't exist.\n", className);
        }
    }

    private static void deleteEmployee(List<Employee> employees) {
        System.out.println("Provide employee id:");
        Scanner scan = new Scanner(System.in);
        int id = Utils.getIntFromStringInput(scan.nextLine());
        if (employees.isEmpty()) {
            System.out.println("There are no employees to delete.");
            return;
        }
        try {
            if (employees.stream().anyMatch(e -> e.getId() == id)) {
                if (Utils.employeeTypeInDB(employees, "Manager")) {
                    Manager manager = (Manager) Utils.getEmployeeOfType(employees, "Manager");
                    manager.removeOneFromManagedEmployees(id);
                }
                employees.removeIf(employee -> employee.getId() == id);
                System.out.printf("Employee with id: %d deleted.\n", id);
            } else {
                System.out.printf("Employee with id: %d doesn't exist.\n", id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Id is supposed to be an integer!");
        }
    }

    private static void newEmployee(List<Employee> employees, AtomicInteger idGen) {
        try {
            Employee employee = EmployeeFactory.createEmployee(employees, idGen);
            assert employee != null;
            employees.add(employee);
            System.out.printf("Employee with id: %d added.\n", employee.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
