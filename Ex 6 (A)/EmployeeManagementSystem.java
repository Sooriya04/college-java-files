import java.util.ArrayList;
import java.util.Scanner;
class NegativeValueException extends Exception {
    public NegativeValueException(String message) {
        super(message);
    }
}
class InvalidIDException extends Exception {
    public InvalidIDException(String message) {
        super(message);
    }
}
class Employee {
    private String empID;
    private double salary;
    public void setEmpID(String empID) throws InvalidIDException {
        if (!empID.matches("EMP\\d+")) {
            throw new InvalidIDException("Invalid ID! Employee ID must start with 'EMP' followed by numbers.");
        }
        this.empID = empID;
    }
    public void setSalary(String salaryInput) throws NegativeValueException, NumberFormatException {
        double salary;
        try {
            salary = Double.parseDouble(salaryInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid salary input! Please enter a valid numeric value.");
        }
        if (salary < 0) {
            throw new NegativeValueException("Salary cannot be negative!");
        }
        this.salary = salary;
    }
    public void displayEmployeeDetails() {
        System.out.println("Employee ID: " + empID);
        System.out.println("Employee Salary: " + salary);
    }
}
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of employees to add: ");
        int numEmployees = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numEmployees; i++) {
            Employee emp = new Employee();
            System.out.println("\nAdding Employee " + (i + 1) + ":");
            try {
                System.out.print("Enter Employee ID: ");
                String empID = scanner.nextLine();
                emp.setEmpID(empID);
                System.out.print("Enter Salary: ");
                String salary = scanner.nextLine();
                emp.setSalary(salary);
                employees.add(emp);
            } catch (InvalidIDException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NegativeValueException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("\nAll Employee Details:");
        for (Employee emp : employees) {
            emp.displayEmployeeDetails();
            System.out.println("-------------------");
        }
        scanner.close();
    }
}