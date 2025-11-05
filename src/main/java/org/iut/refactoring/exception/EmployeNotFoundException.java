package org.iut.refactoring.exception;

public class EmployeNotFoundException extends RuntimeException {
    private static final String MESSAGE_PREFIX = "Employé non trouvé avec l'ID: ";

    public EmployeNotFoundException(String employeId) {
        super(MESSAGE_PREFIX + employeId);
    }
}