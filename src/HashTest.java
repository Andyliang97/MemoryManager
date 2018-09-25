
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test the hash function (you should throw this away for your project)
 *
 *  @author CS3114 staff
 *  @version August, 2018
 */
public class HashTest extends TestCase {
    /**
     * Sets up the tests that follow.
     */
    private Hash testHash;
    
    /**
     * set up
     */
    public void setUp() {
        testHash = new Hash(10, 64);
    }
   

    /**
     * test hash
     */
    public void testh() {
        assertEquals(testHash.h("aaaabbbb", 101), 75);
        assertEquals(testHash.h("aaaabbb", 101), 1640219587 % 101);
        assertEquals(testHash.h("a", 10), 7);
        assertEquals(testHash.h("b", 10), 8);
        assertEquals(testHash.h("c", 10), 9);
        assertEquals(testHash.h("d", 10), 0);
        assertEquals(testHash.h("a", 20), 17);
        assertEquals(testHash.h("u", 20), 17);
        
    }
    
    /**
     * test quadratic probing 
     */
    public void testp() {
        assertEquals(testHash.p(5, 2, 8), 1);
        assertEquals(testHash.p(10, 4, 16), 10);
    }
    
  
    
    /**
     * test add command
     */
    public void testAdd() {
        assertTrue(testHash.add("a"));
        assertFalse(testHash.add("a"));
        assertTrue(testHash.add("b"));
        assertTrue(testHash.add("c"));
        assertTrue(testHash.add("d"));
        assertTrue(testHash.add("e"));
        assertTrue(testHash.add("f"));
        assertFalse(testHash.add("b"));
        assertFalse(testHash.add("c"));
        
    }
    
    /**
     * test search method
     */
    public void testSearch() {
        assertTrue(testHash.add("a"));
        assertTrue(testHash.add("b"));
        assertTrue(testHash.add("c"));
        assertTrue(testHash.add("d"));
        assertEquals(-1, testHash.search("e"));
        assertEquals(-1, testHash.search("f"));
        assertEquals(7, testHash.search("a"));
        assertEquals(8, testHash.search("b"));
        assertEquals(9, testHash.search("c"));
        assertEquals(0, testHash.search("d"));
    }
    
    /**
     * test delete command
     */
    public void testDelete() {
        assertTrue(testHash.add("a"));
        assertTrue(testHash.add("b"));
        assertTrue(testHash.add("c"));
        assertTrue(testHash.add("d"));
        assertTrue(testHash.delete("a"));
        assertFalse(testHash.delete("a"));
        assertFalse(testHash.delete("e"));
        assertTrue(testHash.delete("b"));
    }
    
    /**
     * test PrintHashtable method
     */
    public void testPrintHashtable() {
        testHash.printHashtable();
        assertTrue(systemOut().getHistory().contains("Total records: 0"));
        assertTrue(testHash.add("a"));
        assertTrue(testHash.add("b"));
        assertTrue(testHash.add("c"));
        assertTrue(testHash.add("d"));
        assertTrue(testHash.delete("a"));
        assertFalse(testHash.delete("a"));
        assertFalse(testHash.delete("e"));
        assertTrue(testHash.delete("b"));
        assertEquals(testHash.getCount(), 2);
        testHash.printHashtable();
        assertTrue(systemOut().getHistory().contains("Total records: 2"));
        assertTrue(systemOut().getHistory().contains("|c| 9"));
        assertTrue(systemOut().getHistory().contains("|d| 0"));
        
    }
    /**
     * test printSelect method
     */
    public void testprintCommand() {
        String print1 = "blocks";
        testHash.printSelect(print1);
        String print = "hashtable";
        testHash.printSelect(print);
        String newPrint = "blablabla";
        testHash.printSelect(newPrint);
        assertTrue(systemOut().getHistory().contains("Wrong Print Command!"));
    }
    /**
     * test printSelct method
     */
    public void testupdateSelect() {
        testHash.add("Overwatch");
        testHash.add("Dota2");
        testHash.updateSelect("add Overwatch<SEP>Tank<SEP>Wrecking Ball");
        assertTrue(systemOut().getHistory().contains("Updated Record:"
                + " |Overwatch<SEP>Tank<SEP>Wrecking Ball|"));
        testHash.updateSelect("add LOL<SEP>Tank<SEP>Wrecking Ball");
        assertTrue(systemOut().getHistory().contains("|LOL| not updated "
                + "because it does not exist in the Name database."));
        testHash.updateSelect("delete Overwatch<SEP>Tank");
        assertTrue(systemOut().getHistory().contains("Updated Record: "
                + "|Overwatch|"));
        testHash.updateSelect("delete LOL<SEP>Tank<SEP>Wrecking Ball");
        assertTrue(systemOut().getHistory().contains("|LOL| not updated "
                + "because it does not exist in the Name database."));
        testHash.updateSelect("delete LOL<SEP>Tank");
        testHash.updateSelect("wrongCommand");
        assertTrue(systemOut().getHistory().contains("Wrong Update Command!"));
        testHash.updateSelect("add Dota2<SEP>range<SEP>Lina<SEP>meele<SEP>Axe");
        testHash.updateSelect("delete Dota2<SEP>meele");
        assertTrue(systemOut().getHistory().contains("Updated Record:"
            + " |Dota2<SEP>range<SEP>Lina|"));
        testHash.updateSelect("add Dota2<SEP>meele<SEP>Pudge");
        assertTrue(systemOut().getHistory().contains("Updated Record:"
            + " |Dota2<SEP>range<SEP>Lina<SEP>meele<SEP>Pudge|"));
        testHash.updateSelect("add Dota2<SEP>meele<SEP>Slark");
        assertTrue(systemOut().getHistory().contains("Updated Record:"
            + " |Dota2<SEP>range<SEP>Lina<SEP>meele<SEP>Slark|"));
        testHash.updateSelect("delete Dota2<SEP>interesting");
    }
    
    
    /**
     * test grow method
     */
    public void testGrow() {
        testHash.add("a");
        testHash.add("u");
        testHash.grow();
        assertEquals(testHash.gethashSize(), 20);
        assertEquals(17, testHash.search("a"));
        assertEquals(18, testHash.search("u"));
    }
    
    /**
     * test for method add
     */
    public void testAdd2() {
        testHash.add("a");
        testHash.add("b");
        testHash.add("c");
        testHash.add("d");
        testHash.add("e");
        testHash.add("u");
        assertEquals(testHash.gethashSize(), 20);
        assertEquals(17, testHash.search("a"));
        System.out.println(testHash.search("u"));
        assertEquals(6, testHash.search("u"));
    }
    
}
