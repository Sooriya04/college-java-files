package Files;
import java.io.*;
public class FileCodeThree {
    public void fileWriting(){
        String path = "src/source4.txt";
        try{
            FileWriter file = new FileWriter(path);
            file.write("This is used to write in the file\n");
            file.write("This is used to write in the file\n");
            file.append("Append funtions,");//insert this at the end of the file;
            file.append("It used to append at the end of the file");
            file.close();
        }catch(IOException e){
            System.out.println("Error : " + e );
        }
    }
    public void fileReadings(){
        String path = "src/source1.txt";
        try{
            FileReader file = new FileReader(path);
            int data;
            while((data = file.read()) != -1){
                System.out.print((char)data);
            }
            file.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error " + e);
        }catch(IOException e){
            System.out.println("Error " + e);
        }
    }
    public static void main(String[] args) {
        FileCodeThree fileM = new FileCodeThree();
        fileM.fileWriting();
        fileM.fileReadings();
    }
}
