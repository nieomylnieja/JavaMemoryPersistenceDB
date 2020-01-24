package model;

import Utils.*;

public class JavaDeveloper extends Developer {
    private boolean knowsSpring;

    public JavaDeveloper(EmployeePayload payload) {
        super(payload);
    }

    public void printEmployeeData() {
        this.printBasicInfo();
        if (this.ifKnowsSpring()) {
            System.out.println(" - Knows Spring");
        }
        System.out.println("");
    }

    public void checkDeveloper(Manager manager) {
        this.performDevCheck(manager, this.knowsSpring, "Java");
    }

    public boolean ifKnowsSpring() {
        return knowsSpring;
    }

    public void setKnowsSpring() throws IllegalArgumentException {
        System.out.println("Is the employee familiar with Spring? [yes/no]");
        this.knowsSpring = Utils.getBoolFromInput();
    }
}
