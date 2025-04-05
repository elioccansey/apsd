package edu.miu.pensionplan.repository;

import edu.miu.pensionplan.model.Employee;
import edu.miu.pensionplan.model.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                1L,
                "Daniel",
                "Agar",
                105945.50,
                LocalDate.of(2018, 1, 17),
                new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00, 1L)
        ));

        employees.add(new Employee(
                2L,
                "Benard",
                "Shaw",
                197750.00,
                LocalDate.of(2022, 9, 3),
                null
        ));

        employees.add(new Employee(
                3L,
                "Carly",
                "Agar",
                842000.75,
                LocalDate.of(2014, 5, 16),
                new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50, 3L)
        ));

        employees.add(new Employee(
                4L,
                "Wesley",
                "Schneider",
                74500.00,
                LocalDate.of(2022, 7, 21),
                null
        ));

        employees.add(new Employee(
                5L,
                "Anna",
                "Wiltord",
                85750.00,
                LocalDate.of(2022, 6, 15),
                null
        ));

        employees.add(new Employee(
                6L,
                "Yosef",
                "Tesfalem",
                100000.00,
                LocalDate.of(2022, 10, 31),
                null
        ));

        return employees;
    }
}
