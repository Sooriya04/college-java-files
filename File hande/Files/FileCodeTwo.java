package Files;
import java.io.*;
public class FileCodeTwo {
    public static void main(String[] args) {
        String f = "src/source.txt";
        try {
            FileReader file = new FileReader(f);
            int data;
            while ((data = file.read()) != -1) { 
                System.out.print((char) data);
            }
            file.close();
            //System.out.println(data);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e);
        }
    }
}
