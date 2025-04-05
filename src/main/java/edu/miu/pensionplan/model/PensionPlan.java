package edu.miu.pensionplan.model;

import java.time.LocalDate;
import java.util.Objects;

public class PensionPlan {
    private final String planReferenceNumber;
    private final LocalDate enrollmentDate;
    private final double monthlyContribution;
    private final long employeeId;

    public PensionPlan(String planReferenceNumber, LocalDate enrollmentDate, double monthlyContribution, long employeeId) {
        if (employeeId <= 0) {
            throw new IllegalArgumentException("A valid employeeId is required for PensionPlan");
        }
        this.planReferenceNumber = Objects.requireNonNull(planReferenceNumber);
        this.enrollmentDate = Objects.requireNonNull(enrollmentDate);
        this.monthlyContribution = monthlyContribution;
        this.employeeId = employeeId;
    }

    public String getPlanReferenceNumber() {
        return planReferenceNumber;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public double getMonthlyContribution() {
        return monthlyContribution;
    }

    public long getEmployeeId() {
        return employeeId;
    }
}
