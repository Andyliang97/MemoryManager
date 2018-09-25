import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import student.TestCase;
/**
 * parserTest for testing parser
 * @author jiez and Junjie Liang
 * 
 * @version 1.1
 *
 */
public class ParserTest extends TestCase {
    private Parser testParser;

    /**
     * Read contents of a file into a string
     * 
     * @param path File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
    /**
     * default constructor
     */
    public void setUp() {
        testParser = new Parser(10, 32);
    }
    /**
     * test if the parser can read in the P1sampleInput
     * file and output the same as P1sampleOutput file
     * @throws Exception throw exceptions
     */
    public void test() throws Exception {
        testParser.readinFile("P1sampleInput.txt");
        assertFuzzyEquals(readFile("P1sampleOutput.txt"), 
            systemOut().getHistory());
    }

    /**
     * a test for reading in wrong commands
     * read in a wrong command file and output
     * wrong commands string
     */
    public void testWrongCommand() {
        testParser.readinFile("WrongCommand.txt");
        assertTrue(systemOut().getHistory().contains("Wrong Print Command!"));
        assertTrue(systemOut().getHistory().contains("WRONG COMMAND!"));
    }
}
