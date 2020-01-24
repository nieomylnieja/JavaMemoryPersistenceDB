package model;

import Utils.*;

public class PythonDeveloper extends Developer {
    private boolean knowsFlask;

    public PythonDeveloper(EmployeePayload payload) {
        super(payload);
    }

    public void printEmployeeData() {
        this.printBasicInfo();
        if (this.ifKnowsFlask()) {
            System.out.println(" - Knows Flask");
        }
        System.out.println("");
    }

    public void checkDeveloper(Manager manager){
        this.performDevCheck(manager, this.knowsFlask, "Python");
    }

    public boolean ifKnowsFlask() {
        return knowsFlask;
    }

    public void setKnowsFlask() {
        System.out.println("Is the employee familiar with Flask? [yes/no]");
        this.knowsFlask = Utils.getBoolFromInput();
    }
}
