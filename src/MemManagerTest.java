import student.TestCase;

/**
 * test case for Memory Manager
 * 
 * @author Junjie Liang
 * @version 2018 AUG
 *
 */
public class MemManagerTest extends TestCase {

    private MemManager testMM;
    private Handle testHandle;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        testHandle = new Handle(8, 10);
        testMM = new MemManager(32);
    }

    /**
     *  this method test Insert
     */
    public void testInsert() {
        testMM.insert("test".getBytes(), "test".length());
        testMM.insert("test".getBytes(), "test".length());
        testMM.insert("test".getBytes(), "test".length());
        testMM.insert("test".getBytes(), "test".length());
        testMM.printblock();
        assertTrue(systemOut().getHistory().contains("16: 16"));
        String byte64String = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        testMM.insert(byte64String.getBytes(), byte64String.length());
        assertTrue(systemOut().getHistory()
                .contains("Memory pool expanded to be 64 bytes."));
        assertTrue(systemOut().getHistory()
                .contains("Memory pool expanded to be 128 bytes."));
    }

    /**
     * this method test printblock
     */
    public void testPrintBlock() {
        testMM.printblock();
        testMM.insert("aaaaaaaaaaaaaaaaa".getBytes(), 17);
        testMM.printblock();
        assertTrue(systemOut().getHistory()
                .contains("No free blocks are available."));

    }

    /**
     * this method test division
     */
    public void testDivision() {
        assertFalse(testMM.division(6, 6));
        assertTrue(testMM.division(3, 3));
        testMM.printblock();
        assertTrue(systemOut().getHistory().contains("8: 0 8"));
        assertTrue(systemOut().getHistory().contains("16: 16"));
    }

    /**
     * this method test arraygrow
     */
    public void testArrayGrow() {
        testMM.arrayGrow(1);
        testMM.printblock();
        assertTrue(systemOut().getHistory().contains("64: 0"));
        testMM.insert("test".getBytes(), "test".length());
        testMM.arrayGrow(1);
        testMM.printblock();
        assertTrue(systemOut().getHistory().contains("64: 64"));

    }

    /**
     * this method test length
     */
    public void testlength() {
        assertEquals(10, testMM.length(testHandle));
    }

    /**
     * this method test remove
     * 
     */
    public void testRemove() {
        testHandle = testMM.insert("testing".getBytes(), "testing".length());
        testMM.printblock();
        assertTrue(systemOut().getHistory().contains("8: 8"));
        assertTrue(systemOut().getHistory().contains("16: 16"));
        testMM.remove(testHandle);
        testMM.printblock();
        assertTrue(systemOut().getHistory().contains("32: 0"));
    }

    /**
     * this method test getString
     */
    public void testGetString() {
        testHandle = testMM.insert("testing".getBytes(), 7);
        String returnString = testMM.getString(testHandle);
        assertEquals("testing", returnString);
    }

    /**
     * this method test get
     */
    public void testGet() {
        testHandle = testMM.insert("test".getBytes(), 4);
        byte[] temparray = new byte[3];
        assertEquals(3, testMM.get(temparray, testHandle, 4));
        temparray = new byte[4];
        assertEquals(4, testMM.get(temparray, testHandle, 4));
    }

    /**
     * this method test merge
     */
    public void testMerge() {
        Handle testHandle2 = new Handle();
        testHandle2 = testMM.insert("test".getBytes(), 4);
        testHandle = testMM.insert("test".getBytes(), 4);
        testMM.insert("test".getBytes(), 4);
        // testMM.insert("test".getBytes(),4);
        testMM.remove(testHandle);
        assertFalse(testMM.merge(4, 2));
        testMM.insert("test".getBytes(), 4);
        testMM.remove(testHandle2);
        assertFalse(testMM.merge(0, 2));
    }
    
    /**
     * this method test merge
     */
    public void testMerge2() {
        Handle testHandle2 = new Handle();
        testHandle2 = testMM.insert("test".getBytes(), 4);
        testHandle = testMM.insert("test".getBytes(), 4);
        testMM.remove(testHandle2);
        testMM.remove(testHandle);
        
        testHandle2 = testMM.insert("test".getBytes(), 4);
        testHandle = testMM.insert("test".getBytes(), 4);
        testMM.remove(testHandle2);
        testMM.printblock();
        assertTrue(systemOut().getHistory()
                .contains("4: 0"));
        
    }

    /**
     * this method test insert
     */
    public void testInsert2() {
        testMM.insert("aaaaaaaaaaaaaaaaa".getBytes(), 17);
        testMM.insert("aaaaaaaaaaaaaaa".getBytes(), 15);
        assertTrue(systemOut().getHistory()
                .contains("Memory pool expanded to be 64 bytes."));
    }

}