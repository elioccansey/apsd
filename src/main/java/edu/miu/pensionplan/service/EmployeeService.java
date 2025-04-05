package edu.miu.pensionplan.service;


import edu.miu.pensionplan.model.Employee;
import edu.miu.pensionplan.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployeesSorted() {
        return repository.getAllEmployees().stream()
                .filter(e -> e.getPensionPlan() != null)
                .sorted(Comparator
                        .comparing(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());
    }

    public List<Employee> getQuarterlyUpcomingEnrollees(LocalDate currentDate) {
        LocalDate startOfNextQuarter = getStartOfNextQuarter(currentDate);
        LocalDate endOfNextQuarter = startOfNextQuarter.plusMonths(3).minusDays(1);

        return repository.getAllEmployees().stream()
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> {
                    LocalDate qualifiedDate = e.getEmploymentDate().plusYears(3);
                    return !qualifiedDate.isBefore(startOfNextQuarter) &&
                            !qualifiedDate.isAfter(endOfNextQuarter);
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());
    }

    private LocalDate getStartOfNextQuarter(LocalDate current) {
        int year = current.getYear();
        Month month = current.getMonth();

        if (month.getValue() <= 3) {
            return LocalDate.of(year, 4, 1);
        } else if (month.getValue() <= 6) {
            return LocalDate.of(year, 7, 1);
        } else if (month.getValue() <= 9) {
            return LocalDate.of(year, 10, 1);
        } else
            return LocalDate.of(year + 1, 1, 1);

    }
}
