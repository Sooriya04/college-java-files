import java.util.*;
import java.io.*;

// Class to hold Computer Details
class ComputerDetails {
    private int serialNo;
    private String company;
    private String processorDetails;
    private int ramSize;
    private int hddSize;
    private int monitorSize;
    private double cost;
    private int yearOfManufacture;
    private int warrantyYears;

    // Constructor
    public ComputerDetails(int serialNo, String company, String processorDetails, int ramSize, int hddSize, int monitorSize, double cost, int yearOfManufacture, int warrantyYears) {
        this.serialNo = serialNo;
        this.company = company;
        this.processorDetails = processorDetails;
        this.ramSize = ramSize;
        this.hddSize = hddSize;
        this.monitorSize = monitorSize;
        this.cost = cost;
        this.yearOfManufacture = yearOfManufacture;
        this.warrantyYears = warrantyYears;
    }

    // Getters
    public int getSerialNo() {
        return serialNo;
    }

    public String getCompany() {
        return company;
    }

    public String getProcessorDetails() {
        return processorDetails;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getMonitorSize() {
        return monitorSize;
    }

    public double getCost() {
        return cost;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    @Override
    public String toString() {
        return "Serial No: " + serialNo + ", Company: " + company + ", Processor: " + processorDetails +
               ", RAM: " + ramSize + "GB, HDD: " + hddSize + "GB, Monitor: " + monitorSize + " inches, Cost: " +
               cost + ", Year: " + yearOfManufacture + ", Warranty: " + warrantyYears + " years";
    }
}

// Manager Class to Handle Operations
class ComputerManager {
    private List<ComputerDetails> computerList;

    // Constructor
    public ComputerManager() {
        computerList = new ArrayList<>();
    }

    // Load data from file
    public void loadComputersFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int serialNo = Integer.parseInt(parts[0].trim());
                String company = parts[1].trim();
                String processorDetails = parts[2].trim();
                int ramSize = Integer.parseInt(parts[3].trim());
                int hddSize = Integer.parseInt(parts[4].trim());
                int monitorSize = Integer.parseInt(parts[5].trim());
                double cost = Double.parseDouble(parts[6].trim());
                int yearOfManufacture = Integer.parseInt(parts[7].trim());
                int warrantyYears = Integer.parseInt(parts[8].trim());
                computerList.add(new ComputerDetails(serialNo, company, processorDetails, ramSize, hddSize, monitorSize, cost, yearOfManufacture, warrantyYears));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Find computer by serial number
    public void findComputerBySerialNo(int serialNo) {
        for (ComputerDetails computer : computerList) {
            if (computer.getSerialNo() == serialNo) {
                System.out.println(computer);
                return;
            }
        }
        System.out.println("No computer found with serial number " + serialNo);
    }

    // List all computers of a particular brand
    public void listComputersByBrand(String brand) {
        boolean found = false;
        for (ComputerDetails computer : computerList) {
            if (computer.getCompany().equalsIgnoreCase(brand)) {
                System.out.println(computer);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No computers found for brand " + brand);
        }
    }

    // List processor and RAM details greater than a given cost
    public void listProcessorAndRAMByCost(double cost) {
        for (ComputerDetails computer : computerList) {
            if (computer.getCost() > cost) {
                System.out.println("Processor: " + computer.getProcessorDetails() + ", RAM: " + computer.getRamSize() + "GB");
            }
        }
    }

    // List serial number and processor details for warranty period between 2 and 8 years
    public void listByWarrantyPeriod() {
        for (ComputerDetails computer : computerList) {
            if (computer.getWarrantyYears() >= 2 && computer.getWarrantyYears() <= 8) {
                System.out.println("Serial No: " + computer.getSerialNo() + ", Processor: " + computer.getProcessorDetails());
            }
        }
    }

    // Display computers with RAM and monitor size greater than given values
    public void displayByRamAndMonitorSize(int ramSize, int monitorSize) {
        for (ComputerDetails computer : computerList) {
            if (computer.getRamSize() > ramSize && computer.getMonitorSize() > monitorSize) {
                System.out.println(computer);
            }
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        ComputerManager manager = new ComputerManager();
        manager.loadComputersFromFile("computers.txt"); // Sample file

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Computer Management ---");
            System.out.println("1. Find computer by serial number");
            System.out.println("2. List all computers of a brand");
            System.out.println("3. List processor and RAM details by cost");
            System.out.println("4. List serial number and processor by warranty period");
            System.out.println("5. Display computers by RAM and monitor size");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter serial number: ");
                    int serialNo = sc.nextInt();
                    manager.findComputerBySerialNo(serialNo);
                    break;
                case 2:
                    System.out.print("Enter brand: ");
                    String brand = sc.next();
                    manager.listComputersByBrand(brand);
                    break;
                case 3:
                    System.out.print("Enter cost: ");
                    double cost = sc.nextDouble();
                    manager.listProcessorAndRAMByCost(cost);
                    break;
                case 4:
                    manager.listByWarrantyPeriod();
                    break;
                case 5:
                    System.out.print("Enter minimum RAM size: ");
                    int ramSize = sc.nextInt();
                    System.out.print("Enter minimum monitor size: ");
                    int monitorSize = sc.nextInt();
                    manager.displayByRamAndMonitorSize(ramSize, monitorSize);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        sc.close();
        }
    }
}
