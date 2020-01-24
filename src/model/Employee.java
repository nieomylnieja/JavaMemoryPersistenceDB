package model;

import Utils.EmployeePayload;

public abstract class Employee {
    private int id;
    private String name;
    private String surname;
    private int yearsOfExperience;
    private final static String COMPANY_NAME = "EVIL C.O.";

    public Employee(EmployeePayload payload) {
        this.id = payload.id;
        this.name = payload.name;
        this.surname = payload.surname;
        this.yearsOfExperience = payload.yearsOfExperience;
    }

    public abstract void printEmployeeData();

    protected void printBasicInfo() {
        System.out.printf("ID: %s\n", this.getId());
        System.out.printf(" - Name: %s %s\n", this.getName(), this.getSurname());
        System.out.printf(" - Vacancy: %s\n", this.getClass().getSimpleName());
        System.out.printf(" - Experience: %s years\n", this.getYearsOfExperience());
    }

    public static void printCompanyName() {
        System.out.println(COMPANY_NAME);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
