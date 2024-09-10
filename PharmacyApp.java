import java.util.Scanner;

class PharmacyBilling {
    private String patientName;
    private String address;
    private String phoneNumber;
    private String doctorName;
    private String[] medicines;
    private double[] costs;

    public PharmacyBilling(String patientName, String address, String phoneNumber, String doctorName, String[] medicines, double[] costs) {
        this.patientName = patientName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.doctorName = doctorName;
        this.medicines = medicines;
        this.costs = costs;
    }

    public void generateBill() {
        double totalCost = 0;
        System.out.println("\nThe Bill");
        System.out.println("Patient Name: " + patientName);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Doctor Name: " + doctorName);
        System.out.println("\nMedicines and Costs:");
        for (int i = 0; i < medicines.length; i++) {
            System.out.println(medicines[i] + ": $" + costs[i]);
            totalCost += costs[i];
        }
        System.out.println("\nTotal Cost: $" + totalCost);
    }
}

public class PharmacyApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter doctor name: ");
        String doctorName = scanner.nextLine();

        System.out.print("Enter number of medicines: ");
        int numMedicines = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String[] medicines = new String[numMedicines];
        double[] costs = new double[numMedicines];

        for (int i = 0; i < numMedicines; i++) {
            System.out.print("Enter medicine " + (i + 1) + " name: ");
            medicines[i] = scanner.nextLine();

            System.out.print("Enter cost of " + medicines[i] + ": $");
            costs[i] = scanner.nextDouble();
            scanner.nextLine();
        }

        PharmacyBilling billing = new PharmacyBilling(patientName, address, phoneNumber, doctorName, medicines, costs);
        billing.generateBill();

        scanner.close();
    }
}
