import java.util.*;

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