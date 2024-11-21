import java.util.*;
import java.util.logging.*;
import java.io.*;
class Patient {
    int id;
    String name, address, phone, disease, gender;
    int age;
    public Patient(int id, String name, String address, String phone, String disease, String gender, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.disease = disease;
        this.gender = gender;
        this.age = age;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Address: " + address + ", Phone: " + phone +
                ", Disease: " + disease + ", Gender: " + gender + ", Age: " + age;
    }
}
public class PatientManagement {
    private static LinkedList<Patient> patients = new LinkedList<>();
    private static Logger logger = Logger.getLogger("PatientLog");
    private static FileHandler fileHandler;
    static {
        try {
            fileHandler = new FileHandler("patient_log.log", true);
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            System.out.println("Error initializing log file: " + e.getMessage());
        }
    }
    private static void logActivity(String activity) {
        logger.info(activity);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Login as:\n1. Receptionist\n2. Nurse\n3. Exit");
            System.out.println("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                logActivity("Receptionist logged in");
                receptionistMenu(sc);
            } else if (choice == 2) {
                logActivity("Nurse logged in");
                nurseMenu();
            } else if (choice == 3) {
                logActivity("Program exited");
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private static void receptionistMenu(Scanner sc) {
        while (true) {
            System.out.println("Receptionist Menu:\n1. Add Patient\n2. Delete Patient\n3. Logout");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                addPatient(sc);
            } else if (choice == 2) {
                deletePatient(sc);
            } else if (choice == 3) {
                logActivity("Receptionist logged out");
                System.out.println("Logged out.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private static void addPatient(Scanner sc) {
        System.out.println("Enter Patient ID:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Name:");
        String name = sc.nextLine();
        System.out.println("Enter Address:");
        String address = sc.nextLine();
        System.out.println("Enter Phone:");
        String phone = sc.nextLine();
        System.out.println("Enter Disease:");
        String disease = sc.nextLine();
        System.out.println("Enter Gender:");
        String gender = sc.nextLine();
        System.out.println("Enter Age:");
        int age = sc.nextInt();

        Patient patient = new Patient(id, name, address, phone, disease, gender, age);
        patients.add(patient);
        logActivity("Added patient: " + patient);
        System.out.println("Patient added successfully.");
    }
    private static void deletePatient(Scanner sc) {
        System.out.println("Enter Patient ID to delete:");
        int id = sc.nextInt();
        Patient toRemove = null;
        for (Patient patient : patients) {
            if (patient.id == id) {
                toRemove = patient;
                break;
            }
        }
        if (toRemove != null) {
            patients.remove(toRemove);
            logActivity("Deleted patient: " + toRemove);
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }
    private static void nurseMenu() {
        System.out.println("Nurse Menu: Viewing all patients");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        logActivity("Nurse viewed patient details");
    }
}
