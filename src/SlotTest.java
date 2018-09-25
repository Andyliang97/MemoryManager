import student.TestCase;

/**
 * test case for slot
 * @author Junjie Liang
 * @version 2018 AUG
 *
 */
public class SlotTest extends TestCase {
    private Slot testSlot;
    
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        Handle testHandle = new Handle();
        testSlot = new Slot("Death note", testHandle);
    }


    
    /**
     * test if the slot is empty
     */
    public void testIsEmpty() {
        assertFalse(testSlot.isEmpty());
        testSlot.setEmpty();
        assertTrue(testSlot.isEmpty());
        assertEquals("", testSlot.getKey());
        assertTrue(testSlot.getHandle().isEmpty());
        testSlot.setKey("Overwatch");
        assertFalse(testSlot.isEmpty());
    }
    
    /**
     * test if the slot is empty
     */
    public void testIsEmpty2() {
        assertTrue(testSlot.getHandle().isEmpty());
        assertFalse(testSlot.getKey().equals(""));
        assertFalse(testSlot.isEmpty());
        
        testSlot.getHandle().setLength(100);
        assertFalse(testSlot.getHandle().isEmpty());
        assertFalse(testSlot.getKey().equals(""));
        assertFalse(testSlot.isEmpty());
        
        testSlot.setKey("");
        assertFalse(testSlot.getHandle().isEmpty());
        assertTrue(testSlot.getKey().equals(""));
        assertFalse(testSlot.isEmpty());
        
        testSlot.setEmpty();
        assertTrue(testSlot.getHandle().isEmpty());
        assertTrue(testSlot.getKey().equals(""));
        assertTrue(testSlot.isEmpty());
        
    }
    
    
    
    /**
     * test case for get key value
     */
    public void testGetKey() {
        assertEquals("Death note", testSlot.getKey());
    } 
    
    
    /**
     * test case for set key value
     */
    public void testSetKey() {
        assertEquals("Death note", testSlot.getKey());
        testSlot.setKey("Overwatch");
        assertEquals("Overwatch", testSlot.getKey());
    }
    
    /**
     * test case for set tombstone
     * 
     */
    public void testSetTombstone() {
        assertFalse(testSlot.getTombstone());
        testSlot.setTombstone();
        assertTrue(testSlot.getTombstone());
    }
    
    /**
     * test case for get Tombstone
     */
    public void testGetTombstone() {
        assertFalse(testSlot.getTombstone());
    }
    
    /**
     * test case for get handle
     */
    public void testGetHandle() {
        Handle tempHandle = new Handle();
        assertEquals(tempHandle.getSP(), testSlot.getHandle().getSP());
        assertEquals(tempHandle.getLength(), testSlot.getHandle().getLength());
    }
    
    /**
     * test case for set handle
     */
    public void testSetHandle() {
        Handle tempHandle1 = new Handle(100, 100);
        Handle tempHandle2 = new Handle(100, 100);
        testSlot.setHandle(tempHandle1);
        assertEquals(tempHandle2.getSP(), testSlot.getHandle().getSP());
        assertEquals(tempHandle2.getLength(), testSlot.getHandle().getLength());
    }
}    