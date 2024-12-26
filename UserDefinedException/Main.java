package UserDefinedException;
import java.util.*;
import java.util.Scanner;
@SuppressWarnings("unused")
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the age here : ");
        int age = input.nextInt();
        try{
            checkAge(age);
        }catch(Exception e){
            System.out.println("A problrm occured " + e);
        }
        input.close();
    }
    static void checkAge(int age) throws AgeException{
        if(age < 18){
            throw new AgeException("You are not old enough");
        }else{
            System.out.println("You are signed up..");
        }
    }
}
