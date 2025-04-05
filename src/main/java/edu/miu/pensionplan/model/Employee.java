package edu.miu.pensionplan.model;

import java.time.LocalDate;

public class Employee {
    private final long employeeId;
    private final String firstName;
    private final String lastName;
    private final double yearlySalary;
    private final LocalDate employmentDate;
    private PensionPlan pensionPlan;

    public Employee(long employeeId, String firstName, String lastName, double yearlySalary, LocalDate employmentDate) {
        this(employeeId, firstName, lastName, yearlySalary, employmentDate, null);
    }

    public Employee(long employeeId, String firstName, String lastName, double yearlySalary, LocalDate employmentDate, PensionPlan pensionPlan) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearlySalary = yearlySalary;
        this.employmentDate = employmentDate;
        if (pensionPlan != null && pensionPlan.getEmployeeId() != employeeId) {
            throw new IllegalArgumentException("PensionPlan's employeeId must match this employee's id");
        }
        this.pensionPlan = pensionPlan;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public PensionPlan getPensionPlan() {
        return pensionPlan;
    }

    public void setPensionPlan(PensionPlan pensionPlan) {
        if (pensionPlan != null && pensionPlan.getEmployeeId() != this.employeeId) {
            throw new IllegalArgumentException("PensionPlan's employeeId must match this employee's id");
        }
        this.pensionPlan = pensionPlan;
    }

    public boolean isEligibleForPension(LocalDate asOfDate) {
        return employmentDate.plusYears(3).isBefore(asOfDate) || employmentDate.plusYears(3).isEqual(asOfDate);
    }
}
