
import student.TestCase;

/**
 * this are the test cases for Handle
 * @author Junjie Liang
 * @version 2018 August
 */
public class HandleTest extends TestCase {
    private Handle testHandle; 



    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        testHandle = new Handle(100, 100);
        
    }

    /**
     * Test case for isEmpty()
     * 
     * 
     */
    public void testIsEmpty() {
        assertEquals(100, testHandle.getSP());
        assertEquals(100, testHandle.getLength());
        assertFalse(testHandle.isEmpty());
        testHandle.setEmpty();
        assertTrue(testHandle.isEmpty());
        assertEquals(-1, testHandle.getSP());
        assertEquals(-1, testHandle.getLength());
        testHandle.setLength(100);
        testHandle.setSP(100);
        assertFalse(testHandle.isEmpty());
    }
    
    /**
     * Test case for isEmpty()
     * 
     * 
     */
    public void testIsEmpty2() {
        testHandle = new Handle();
        assertEquals(-1, testHandle.getSP());
        assertEquals(-1, testHandle.getLength());
        assertTrue(testHandle.isEmpty());
        testHandle.setLength(100);
        assertFalse(testHandle.isEmpty());
        testHandle.setLength(-1);
        testHandle.setSP(100);
        assertFalse(testHandle.isEmpty());
    }
    
    /**
     * Test case for getSP()
     * 
     * 
     */
    public void testGetsp() {
        assertEquals(100, testHandle.getSP());
    } 
    
    /**
     * Test case for getLength()
     * 
     * 
     */
    public void testGetlength() {
        assertEquals(100, testHandle.getLength());
    } 
    
    /**
     * Test case for setSP()
     * 
     * 
     */
    public void testSetsp() {
        assertEquals(100, testHandle.getSP());
        testHandle.setSP(101);
        assertEquals(101, testHandle.getSP());
    }
    
    /**
     * Test case for setLength()
     * 
     * 
     */
    public void testSetLength() {
        assertEquals(100, testHandle.getLength());
        testHandle.setLength(101);
        assertEquals(101, testHandle.getLength());
    }
}