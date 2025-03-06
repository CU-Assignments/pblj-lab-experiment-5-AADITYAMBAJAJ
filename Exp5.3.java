import java.io.*;  // Importing necessary classes for file handling and serialization  //Exp 5.3
import java.util.*; // Importing utility classes, including List and Scanner

// Employee class implementing Serializable to allow object serialization
class Employee implements Serializable {
    private static final long serialVersionUID = 1L; // Ensures compatibility during deserialization

    // Private fields to store employee details
    private int id;
    private String name;
    private String designation;
    private double salary;








    // Constructor to initialize Employee object
    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    // Method to display employee details
    public void display() {
        System.out.println("Employee ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary);
    }
}

// Main class for employee management system
public class EmployeeManagement {
    private static final String FILE_NAME = "employees.ser"; // File where employee objects will be stored
    private static final Scanner scanner = new Scanner(System.in); // Scanner object for user input

    // Method to add a new employee and save it to file
    public static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt(); // Read employee ID
        scanner.nextLine(); // Consume the newline character left by nextInt()



        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine(); // Read employee name

        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine(); // Read employee designation

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble(); // Read employee salary

        // Creating an Employee object with user input
        Employee employee = new Employee(id, name, designation, salary);

        // Save the employee object to the file
        saveEmployeeToFile(employee);

        System.out.println("Employee added successfully!");
    }

    // Method to save an employee object to file using serialization
    public static void saveEmployeeToFile(Employee employee) {
        List<Employee> employees = readEmployeesFromFile(); // Read existing employees from file
        employees.add(employee); // Add the new employee to the list

        // Serialize the updated list of employees and save it to the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees); // Writing the list of employees to the file
        } catch (IOException e) {
            e.printStackTrace(); // Print error details for debugging
        }
    }

    // Method to display all employees stored in the file
    public static void displayAllEmployees() {
        List<Employee> employees = readEmployeesFromFile(); // Retrieve list of employees from file

        if (employees.isEmpty()) { // Check if there are no employees
            System.out.println("No employees found.");
        } else {
            for (Employee emp : employees) { // Loop through the list and display each employee
                emp.display();
            }
        }
    }

    // Method to read the list of employees from the file
    @SuppressWarnings("unchecked") // Suppresses unchecked cast warning
    public static List<Employee> readEmployeesFromFile() {
        List<Employee> employees = new ArrayList<>(); // Initialize an empty list to store employees

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) ois.readObject(); // Read and cast object from file
        } catch (FileNotFoundException e) {
            // If file not found, no action needed (first run scenario)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Print error details for debugging
        }

        return employees; // Return the list of employees
    }

    // Main method to display menu and handle user choices
    public static void main(String[] args) {
        while (true) { // Infinite loop for menu until user chooses to exit
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt(); // Read user choice




            switch (choice) {
                case 1:
                    addEmployee(); // Call method to add employee
                    break;
                case 2:
                    displayAllEmployees(); // Call method to display employees
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    scanner.close(); // Close scanner before exiting
                    return; // Exit program
                default:
                    System.out.println("Invalid choice. Please try again."); // Handle invalid input
            }
        }
    }

