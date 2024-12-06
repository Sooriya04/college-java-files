package Files;
import java.io.*;
public class FileCodeOne {
    public static void main(String[] args) {
        //String path = ;
        File file = new File("D:/oops/file.txt");
        if(file.exists()){
            System.out.println("File exist");
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getPath());
            System.out.println(file.isFile());
            file.delete(); // // // //
        }else{
            System.out.println("File does not exits");
        }
    }
}