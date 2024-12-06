package Files;
import java.io.*;
class FileCodeFive{
    public static void main(String[] args) {
    // Creates an array of character
    char[] array = new char[10000];
    try {
      FileReader file = new FileReader("src/source.txt");
      // Creates a BufferedReader
      BufferedReader input = new BufferedReader(file);
      // Reads characters
      input.read(array);
      System.out.println("Data in the file: ");
      System.out.println(array);
      // Closes the reader
      input.close();
    }
    catch(FileNotFoundException e){
        System.out.println("Error" + e);
    }
    catch(IOException e) {
        System.out.println("Error" + e);
    }
    
  }
}
