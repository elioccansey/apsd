package edu.miu.pensionplan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.pensionplan.model.Employee;
import edu.miu.pensionplan.repository.EmployeeRepository;
import edu.miu.pensionplan.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

public class PensionPlanApp {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService(new EmployeeRepository());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println("=== All Employees (Sorted) ===");
        try {
            List<Employee> sortedEmployees = service.getAllEmployeesSorted();
            String json = mapper.writeValueAsString(sortedEmployees);
            System.out.println(json);
        } catch (Exception e) {
            System.err.println("Error serializing employee list: " + e.getMessage());
        }

        System.out.println("\n=== Quarterly Upcoming Enrollees ===");
        try {
            LocalDate now = LocalDate.now();
            List<Employee> enrollees = service.getQuarterlyUpcomingEnrollees(now);
            String json = mapper.writeValueAsString(enrollees);
            System.out.println(json);
        } catch (Exception e) {
            System.err.println("Error serializing enrollees list: " + e.getMessage());
        }
    }
}
