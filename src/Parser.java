import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * a Parser class to parse the command
 * @author jiez, Junjie Liang
 * 
 * @version 1.1
 *
 */
public class Parser {
    private Hash hashtable;

//    private int hashSize = 0;
//   private int mempoolSize = 0;

    /**
     * constructor
     * @param initHashsize input value for hashSize
     * @param initMemsize input value for memorypool size
     */
    public Parser(int initHashsize, int initMemsize) {
  
        int hashSize = initHashsize;
        int mempoolSize = initMemsize;
        hashtable = new Hash(hashSize, mempoolSize);
    }

    /**
     * this function is used to read from a text file
     * line by line and execute the command .
     * @param fileName the input file name
     */
    public void readinFile(String fileName) {
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            line = in.readLine();
            while (line != null) {
                String trimmedLine = (line.trim()
                    .replaceAll("\t", " ")).replaceAll(" +", " ");
                String[] splitLine = trimmedLine.split(" ", 2);
                if (!trimmedLine.isEmpty()) {
                    if (splitLine[0].equals("add")) {
                        hashtable.add(splitLine[1]);
                    } 
                    else if (splitLine[0].equals("print")) {
                        hashtable.printSelect(splitLine[1]);
                    } 
                    else if (splitLine[0].equals("delete")) {
                        hashtable.delete(splitLine[1]);
                    } 
                    else if (splitLine[0].equals("update")) {

                        hashtable.updateSelect(splitLine[1]);
                    } 
                    else {
                        System.out.println("WRONG COMMAND!");
                    }
                }

                line = in.readLine();
            }
            in.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
