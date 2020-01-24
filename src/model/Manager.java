package model;

import utils.EmployeePayload;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    private List<Integer> managedEmployees;

    public Manager(EmployeePayload payload) {
        super(payload);
        this.managedEmployees = new ArrayList<>();
    }

    public void printEmployeeData() {
        this.printBasicInfo();
        System.out.printf(" - Managed employees: %s\n\n", this.getManagedEmployees().toString());
    }

    private boolean isEmployeeInDatabase(List<Employee> employees, int id) {
        return employees.stream().anyMatch(e -> e.getId() == id);
    }

    public List<Integer> getManagedEmployees() {
        return this.managedEmployees;
    }

    public void appendToManagedEmployees(List<Employee> employeesDB, List<Integer> employees) {
        for (Integer id : employees) {
           this.appendOneToManagedEmployees(employeesDB, id);
        }
    }

    public void appendOneToManagedEmployees(List<Employee> employees, Integer id) {
        if (isEmployeeInDatabase(employees, id)) {
            this.getManagedEmployees().add(id);
            System.out.printf("Employee with ID: %d added to managed!\n" +
                    "Please provide next id or press [ENTER] to end.\n", id);
            return;
        }
        System.out.printf("Employee with ID: %d was not added!\n" +
                "ID you provided didn't match with any of the current employed staff.\n", id);
    }

    public void removeOneFromManagedEmployees(Integer employeeId) {
        this.getManagedEmployees().removeIf(e -> e.equals(employeeId));
    }

    public boolean isEmployeeManaged(int id) {
        return this.managedEmployees.stream().anyMatch(e -> e == id);
    }
}
