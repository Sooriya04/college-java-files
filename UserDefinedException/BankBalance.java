package UserDefinedException;

import java.util.Scanner;

class ATM {
    String name;
    String AccNo;
    double money;

    ATM(String name, String AccNo, double money) {
        this.name = name;
        this.AccNo = AccNo;
        this.money = money;
    }

    public String toString() {
        return "Name: " + name + "\nAccount Number: " + AccNo + "\nBalance: " + money;
    }
}

public class BankBalance {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name:");
        String name = input.nextLine(); // Read the name

        System.out.println("Enter the account number:");
        String AccNo = input.nextLine(); // Read the account number

        System.out.println("Enter the balance:");
        double money = input.nextDouble(); // Read the balance

        try {
            checkBalance(name, AccNo, money);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            input.close();
        }
    }

    static void checkBalance(String name, String AccNo, double money) throws InsufficientBalanceException {
        if (money < 1000.0) {
            throw new InsufficientBalanceException("Insufficient bank balance."); // Throw the custom exception
        } else {
            System.out.println("You can withdraw.");
            ATM atm = new ATM(name, AccNo, money);
            System.out.println(atm.toString());
        }
    }
}
