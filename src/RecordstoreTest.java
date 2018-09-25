import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class RecordstoreTest extends TestCase {
    /**
     * read file method 
     * @param path the path of the file
     * @return String
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }

    /**
     * Get code coverage of the class declaration.
     * @throws IOException 
     */
    public void testInit() throws IOException {
        Recordstore recstore = new Recordstore();
        assertNotNull(recstore);
        String[] args = {"32", "10", "P1sampleInput.txt"};
        Recordstore.main(args);
       // assertFuzzyEquals(readFile("P1sampleOutput.txt"), systemOut()
         //       .getHistory());
    }
    
    /**
     * test an input with wrong arguments
     */
    public void testWorstCase() {
        Recordstore recstore = new Recordstore();
        assertNotNull(recstore);
        String[] args = {"32", "10", "P1sampleInput.txt", "wrong"};
        Recordstore.main(args);
        assertTrue(systemOut().getHistory().
                contains("Something Wrong with Parameters"));
    }
}
