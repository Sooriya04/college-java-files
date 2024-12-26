package BankLoan;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            System.out.print("Enter your age : ");
            int age = input.nextInt();
            System.out.print("Enter your income");
            double income = input.nextDouble();
            System.out.print("Enter your Credit score : ");
            double score = input.nextDouble();
            checkAvailabilty(age, income, score);
        }catch(LowCreditScoreException | LowIncomeException | UnderageException e){
            System.err.println("Error" + e.getMessage());
        }
    }
    static void checkAvailabilty(int age, double income, double score) throws LowCreditScoreException, LowIncomeException, UnderageException{
        if(age < 21 && age > 60){
            throw new UnderageException("Invalid age. You must be between 21 and 60 years old to apply for a loan.");
        }else if(income < 300000){
            throw new LowCreditScoreException("Low income. Your annual income must be at least ₹3,00,000.");
        }else if(score < 700){
            throw new LowCreditScoreException("Low credit score. Your credit score must be at least 700.");
        }else{
            System.out.println("You can get loan");
        }
    }
}
