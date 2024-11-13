public class Employee {
    private String id;
    private String name;
    private String email;
    private double salary;
    private String job;
    private String role;

    public Employee(String id, String name, String email, double salary, String job, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.job = job;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public String getJob() {
        return job;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Email: " + email + ", Salary: " + salary + ", Job: " + job + ", Role: " + role;
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceSystem {
    private Map<String, Employee> employees;
    private List<Attendance> attendanceLog;

    public AttendanceSystem() {
        employees = new HashMap<>();
        attendanceLog = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    public void recordAttendance(String employeeId) {
        Employee employee = employees.get(employeeId);
        if (employee != null) {
            Attendance attendance = new Attendance(employee);
            attendanceLog.add(attendance);
            System.out.println("Attendance recorded: " + attendance);
        } else {
            System.out.println("Employee not found!");
        }
    }

    public void showAttendanceLog() {
        for (Attendance attendance : attendanceLog) {
            System.out.println(attendance);
        }
    }
}


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AttendanceSystem attendanceSystem = new AttendanceSystem();

        // Menambahkan beberapa karyawan
        Employee emp1 = new Employee("E001", "Alice", "alice@example.com", 5000.0, "Developer", "Full-time");
        Employee emp2 = new Employee("E002", "Bob", "bob@example.com", 4500.0, "Designer", "Part-time");
        Employee emp3 = new Employee("E003", "Charlie", "charlie@example.com", 6000.0, "Manager", "Full-time");

        attendanceSystem.addEmployee(emp1);
        attendanceSystem.addEmployee(emp2);
        attendanceSystem.addEmployee(emp3);

        // Scanner untuk input dari pengguna
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("\n1. Record Attendance");
            System.out.println("2. Show Attendance Log");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Enter employee ID: ");
                    String employeeId = scanner.nextLine();
                    attendanceSystem.recordAttendance(employeeId);
                    break;
                case "2":
                    attendanceSystem.showAttendanceLog();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
