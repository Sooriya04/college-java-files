package Files;
import java.io.*;
import java.util.*;
//getting input from the user and store it in the files;
public class FileCodeFour {
    String path = "src/source3.txt";
    void FileWritings(){
        try{
            Scanner scanner = new Scanner(System.in);
            FileWriter file = new FileWriter(path);
            boolean isRun = true;
            while(isRun){
                String input = scanner.nextLine();
                if(input.equalsIgnoreCase("exit")){
                    break;
                }
                file.write(input + System.lineSeparator());
            }
            scanner.close();
            file.close();
        }catch(IOException e){
            System.out.println("Error" + e);
        }
    }
    void fileReadings(){
        try{
            FileReader file = new FileReader(path);
            int data;
            while((data = file.read())!= -1){
                System.out.print((char)data);
            }
            file.close();
        }catch(IOException e){
            System.out.println("Error " + e);
        }
    }
    public static void main(String[] args) {
        FileCodeFour files  = new FileCodeFour();
        files.FileWritings();
        files.fileReadings();
    }
}
