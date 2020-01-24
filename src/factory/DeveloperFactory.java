package factory;

import Utils.EmployeePayload;
import model.*;

public class DeveloperFactory {

    public static Developer createDeveloper(EmployeePayload payload) throws IllegalArgumentException {
        switch (payload.classType) {
            case "java_dev":
                return createJavaDev(payload);
            case "py_dev":
                return createPythonDev(payload);
            case "go_dev":
                return createGoDev(payload);
        }
        return null;
    }

    private static JavaDeveloper createJavaDev(EmployeePayload payload) throws IllegalArgumentException {
        JavaDeveloper jDev = new JavaDeveloper(payload);
        jDev.setDevType();
        jDev.setKnowsSpring();
        return jDev;
    }

    private static PythonDeveloper createPythonDev(EmployeePayload payload) throws IllegalArgumentException {
        PythonDeveloper pyDev = new PythonDeveloper(payload);
        pyDev.setDevType();
        pyDev.setKnowsFlask();
        return pyDev;
    }

    private static GoDeveloper createGoDev(EmployeePayload payload) throws IllegalArgumentException {
        GoDeveloper goDev = new GoDeveloper(payload);
        goDev.setDevType();
        goDev.setKnowsRabbitMQ();
        return goDev;
    }
}
