import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Custom Exception
class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

public class Ques2 {
    private HashMap<Integer, String> employeeMap;

    public Ques2() {
        employeeMap = new HashMap<>();
    }

    // Add an employee
    public void addEmployee(int id, String name) {
        employeeMap.put(id, name);
        System.out.println("Employee added successfully!");
    }

    // Retrieve employee by ID
    public String getEmployee(int id) throws EmployeeNotFoundException {
        if (!employeeMap.containsKey(id)) {
            throw new EmployeeNotFoundException("Error: Employee ID not found!");
        }
        return employeeMap.get(id);
    }

    // Display all employees
    public void displayEmployees() {
        if (employeeMap.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("All Employees:");
            for (Map.Entry<Integer, String> entry : employeeMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        Ques2 em = new Ques2();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Employee Management Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee by ID");
            System.out.println("3. Display All Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();
                    em.addEmployee(id, name);
                    break;

                case 2:
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = sc.nextInt();
                    try {
                        String empName = em.getEmployee(searchId);
                        System.out.println("Name for ID " + searchId + ": " + empName);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    em.displayEmployees();
                    break;

                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
