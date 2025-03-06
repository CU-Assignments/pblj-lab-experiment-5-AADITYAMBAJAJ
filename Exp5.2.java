import java.io.*;   // Importing required classes for serialization and file handling   //Exp 5.2
import java.util.Scanner; // Importing Scanner class for user input


// Student class implementing Serializable to enable object serialization
class Student implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended to ensure compatibility during deserialization
    // Private fields to store student details
    private int id;
    private String name;
    private double gpa;
    // Constructor to initialize Student object
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Method to display student details
    public void display() {
        System.out.println("Student ID: " + id + ", Name: " + name + ", GPA: " + gpa);
    }
}

// Main class for serialization and deserialization
public class StudentSerialization {
    private static final String FILE_NAME = "student.ser"; // File where the student object will be saved

    // Method to serialize Student object to file
    public static void serializeStudent(Student student) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(student); // Writing student object to file
            System.out.println("Student object has been serialized and saved to file.");
        } catch (IOException e) { // Handling possible I/O exceptions
            System.out.println("Error during serialization: " + e.getMessage());
        }
    }











    // Method to deserialize Student object from file
    public static Student deserializeStudent() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Student) ois.readObject(); // Reading object from file and casting it back to Student
        } catch (FileNotFoundException e) { // Handling file not found exception
            System.out.println("Error: File not found.");
        } catch (IOException e) { // Handling input/output errors
            System.out.println("Error during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) { // Handling case where class is not found
            System.out.println("Error: Class not found.");
        }
        return null; // Return null if deserialization fails
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner object to take user input

        // Taking user input for Student details
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt(); // Reading integer input for student ID
        scanner.nextLine(); // Consuming the newline left after nextInt()

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine(); // Reading student name as a string

        System.out.print("Enter Student GPA: ");
        double gpa = scanner.nextDouble(); // Reading double input for GPA

        // Creating a Student object with the provided details
        Student student = new Student(id, name, gpa);

        // Calling method to serialize (save) the Student object to a file
        serializeStudent(student);

        // Calling method to deserialize (read) the Student object from the file
        Student deserializedStudent = deserializeStudent();

        // If deserialization was successful, display the student details
        if (deserializedStudent != null) {
            System.out.println("Student object has been deserialized.");
            System.out.println("Deserialized Student Details:");
            deserializedStudent.display(); // Calling display method of the deserialized object
        }

        scanner.close(); // Closing scanner to prevent memory leaks
    }
}
