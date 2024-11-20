import java.util.*;
class Patient {
    int patientId;
    String name;
    String address;
    String phoneNo;
    String disease;
    String dateOfBirth;
    int age;
    String gender;

    public Patient(int patientId, String name, String address, String phoneNo, String disease, String dateOfBirth, int age, String gender) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.disease = disease;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
    }

    public String toString() {
        return "Patient ID: " + patientId + ", Name: " + name + ", Address: " + address +
                ", Phone No: " + phoneNo + ", Disease: " + disease + ", DOB: " + dateOfBirth +
                ", Age: " + age + ", Gender: " + gender;
    }
}

public class PatientManagement {
    public static void main(String[] args) {
        LinkedList<Patient> patients = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        patients.add(new Patient(101, "John Doe", "New York", "1234567890", "Flu", "15-05-1985", 38, "Male"));
        patients.add(new Patient(102, "Jane Smith", "California", "0987654321", "Fever", "20-10-1990", 33, "Female"));
        patients.add(new Patient(103, "Sam Wilson", "Texas", "1122334455", "Cold", "05-12-1995", 28, "Male"));
        patients.add(new Patient(104, "Emma Brown", "Florida", "6677889900", "Cough", "12-07-1988", 36, "Female"));

        while (true) {
            System.out.println("\n--- Patient Management System ---");
            System.out.println("1. List patient details by gender");
            System.out.println("2. List patient names and diseases above a certain age");
            System.out.println("3. Display patient details by patient ID");
            System.out.println("4. Sort and display all patients alphabetically by name");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: 
                    System.out.print("Enter gender (Male/Female): ");
                    String gender = scanner.next();
                    for (Patient p : patients) {
                        if (p.gender.equalsIgnoreCase(gender)) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter minimum age: ");
                    int ageLimit = scanner.nextInt();
                    for (Patient p : patients) {
                        if (p.age > ageLimit) {
                            System.out.println("Name: " + p.name + ", Disease: " + p.disease);
                        }
                    }
                    break;

                case 3: 
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    boolean found = false;
                    for (Patient p : patients) {
                        if (p.patientId == patientId) {
                            System.out.println("Name: " + p.name + ", Phone No: " + p.phoneNo + ", Address: " + p.address);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Patient with ID " + patientId + " not found.");
                    }
                    break;

                case 4:
                    patients.sort(Comparator.comparing(p -> p.name));
                    System.out.println("--- Patients Sorted by Name ---");
                    for (Patient p : patients) {
                        System.out.println(p);
                    }
                    break;

                case 5:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
