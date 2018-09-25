import student.TestCase;

/**
 * test case for slot
 * 
 * @author Junjie Liang
 * @version 2018 AUG
 *
 */
public class SubMemManagerTest extends TestCase {
    private SubMemManager testsubmm;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        testsubmm = new SubMemManager(4);
    }

    /**
     * test the method removeFreeSpace
     */
    public void testremoveFreeSpace() {
        assertFalse(testsubmm.removeFreeSpace());
        testsubmm.insertFreeSpace(4);
        assertTrue(testsubmm.removeFreeSpace());
    }
    /**
     * test the method remove
     */
    public void testremove() {
        assertFalse(testsubmm.remove(0));
        testsubmm.insertFreeSpace(4);
        assertTrue(testsubmm.remove(4));
    }
    /**
     * test the method getfreePosition
     */
    public void testgetFreePosition() {
        assertEquals(-1, testsubmm.getFreePosition());
        testsubmm.insertFreeSpace(4);
        assertEquals(4, testsubmm.getFreePosition());
    }

    /**
     * test the method isEmpty
     */
    public void testisEmpty() {
        assertTrue(testsubmm.isEmpty());
        testsubmm.insertFreeSpace(4);
        assertFalse(testsubmm.isEmpty());
    }

    /**
     * test the method getSpace and setSpace
     */
    public void testgetsetSpace() {
        testsubmm.setSpaceNum(8);
        assertEquals(8, testsubmm.getSpaceNum());
    }

    /**
     * test the method print
     */
    public void testprint() {
        testsubmm.insertFreeSpace(4);
        testsubmm.insertFreeSpace(8);
        testsubmm.print();
        assertTrue(systemOut().getHistory().contains("4 8"));
    }

    /**
     * test the method insertSmallest
     */
    public void testinsertSmallest() {
        testsubmm.insertSmallest(4);
        testsubmm.insertSmallest(8);
        testsubmm.insertSmallest(0);
        testsubmm.print();
        assertTrue(systemOut().getHistory().contains("0 4 8"));

    }

}