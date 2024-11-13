import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AttendanceSystem attendanceSystem = new AttendanceSystem();

        Employee emp1 = new Employee("E001", "Alice", "alice@example.com", 5000.0, "Developer", "Full-time");
        Employee emp2 = new Employee("E002", "Bob", "bob@example.com", 4500.0, "Designer", "Part-time");
        Employee emp3 = new Employee("E003", "Charlie", "charlie@example.com", 6000.0, "Manager", "Full-time");

        attendanceSystem.addEmployee(emp1);
        attendanceSystem.addEmployee(emp2);
        attendanceSystem.addEmployee(emp3);

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