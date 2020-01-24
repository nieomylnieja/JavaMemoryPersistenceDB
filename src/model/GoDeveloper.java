package model;

import utils.*;

public class GoDeveloper extends Developer {
    private boolean knowsRabbitMQ;

    public GoDeveloper(EmployeePayload payload) {
        super(payload);
    }

    public void printEmployeeData() {
        this.printBasicInfo();
        if (this.ifKnowsRabbitMQ()) {
            System.out.println(" - Knows RabbitMQ");
        }
        System.out.println("");
    }

    public void checkDeveloper(Manager manager) {
        this.performDevCheck(manager, this.knowsRabbitMQ, "GoLang");
    }

    public boolean ifKnowsRabbitMQ() {
        return knowsRabbitMQ;
    }

    public void setKnowsRabbitMQ() {
        System.out.println("Is the employee familiar with RabbitMQ? [yes/no]");
        this.knowsRabbitMQ = Utils.getBoolFromInput();
    }
}
