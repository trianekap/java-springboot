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