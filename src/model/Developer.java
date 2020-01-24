package model;

import Utils.EmployeePayload;
import interfaces.DevelopersCheck;

import java.util.Scanner;

public abstract class Developer extends Employee implements DevelopersCheck {
    private DevType devType;

    private String getDevTypeString(DevType devType) {
        return devType.toString().substring(0,1) + devType.toString().substring(1).toLowerCase();
    }

    public Developer(EmployeePayload payload) {
        super(payload);
    }

    protected void printBasicInfo() {
        super.printBasicInfo();
        System.out.printf(" - Developer type: %s\n", this.getDevTypeString(this.devType));
    }

    public void setDevType() throws IllegalArgumentException {
        Scanner scan = new Scanner(System.in);
        showDevTypes();
        String input = scan.nextLine();
        for (DevType devType : DevType.values()) {
            if (devType.toString().equalsIgnoreCase(input)) {
                this.devType = devType;
                return;
            }
        }
        throw new IllegalArgumentException("Wrong type provided!");
    }

    protected boolean performDevCheck(Manager manager, boolean knowsTech, String langName) {
        if (manager.isEmployeeManaged(this.getId())) {
            if (knowsTech) {
                System.out.printf("Nr. %d is a competent %s developer.\n", this.getId(), langName);
                return true;
            }
            System.out.printf("Nr. %s is useless, that employee doesn't know anything..\n", this.getId());
            return false;
        }
        System.out.printf("Nr. %d is not managed at all, he can do whatever he wishes!.\n", this.getId());
        return false;
    }

    public enum DevType {
        FRONTEND,
        BACKEND,
        FULLSTACK,
    }

    private void showDevTypes() {
        System.out.println("Choose one of the following types:");
        for (DevType devType : DevType.values()) {
            System.out.println(" - " + this.getDevTypeString(devType));
        }
    }
}
