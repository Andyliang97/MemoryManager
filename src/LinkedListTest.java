import student.TestCase;

/**
 * linkedList test file
 * 
 * @author jiez
 *
 * @version 2.0
 */
public class LinkedListTest extends TestCase {
    private LinkedList aList = new LinkedList();


    /**
     * this function test insert and
     * get position method
     */
    public void test() {
        assertTrue(aList.isEmpty());
        aList.insertAtTail(5);
        assertEquals(-1, aList.get(3));
        assertEquals(1, aList.getSize());
        // aList.removeFirst();
        // assertFalse(aList.isEmpty());
        aList.insertAtSmallest(4);
        aList.insertAtHead(2);
        assertEquals(3, aList.getSize());
        aList.removeFirst();
        aList.print();
        assertTrue(systemOut().getHistory().contains("4 5"));
        aList.insertAtHead(2);

        // aList.insertAtPos(4);
        aList.insertAtSmallest(6);
        aList.insertAtSmallest(1);
        assertEquals(0, aList.getPos(1));
        assertEquals(1, aList.getPos(2));
        assertEquals(2, aList.getPos(4));
        assertEquals(5, aList.getPre(6));
        assertEquals(3, aList.getPos(5));
        assertEquals(-1, aList.getPos(8));
        assertEquals(-1, aList.getPre(7));
        assertEquals(-1, aList.getNext(7));

    }


    /**
     * this function test the get next,
     * get previous and remove methods
     */
    public void test2() {
        assertTrue(aList.isEmpty());
        aList.insertAtHead(5);
        aList.deleteFromTail();
        assertTrue(aList.isEmpty());
        aList.insertAtHead(5);
        assertEquals(5, aList.getFirst());
        assertEquals(5, aList.getLast());
        aList.insertAtSmallest(2);
        aList.insertAtHead(3);
        assertEquals(5, aList.getNext(2));
        assertEquals(2, aList.getPre(5));
        // aList.insertAtPos(4);
        aList.removeByValue(2);
        aList.removeFirst();

        aList.insertAtSmallest(4);
        assertEquals(5, aList.getNext(4));
        // aList.deleteFromPos(1);
        assertEquals(4, aList.getPre(5));
        // aList.deleteFromPos(1);
        assertEquals(0, aList.getPos(4));
        aList.insertAtSmallest(1);
        aList.insertAtSmallest(2);
        aList.insertAtSmallest(3);

    }


    /**
     * this function test exceptions
     */
    public void test3() {
        assertTrue(aList.isEmpty());
        aList.insertAtTail(0);
        assertFalse(aList.isEmpty());
        aList.insertAtHead(1);
        assertEquals(0, aList.getNext(1));
        assertEquals(-1, aList.getPre(1));
        // aList.insertAtSmallest(2);

    }


    /**
     * this method test both insert and delete
     * method
     */
    public void test4() {
        assertTrue(aList.isEmpty());
        // aList.deleteFromHead();
        aList.insertAtSmallest(2);
        aList.insertAtSmallest(4);
        aList.deleteFromTail();
        aList.removeFirst();
    }


    /**
     * this method test both removeByValue and getNext
     * 
     */
    public void test5() {
        aList.insertAtSmallest(3);
        assertEquals(3, aList.get(0));
        aList.insertAtTail(4);
        assertEquals(4, aList.get(1));
        aList.insertAtTail(6);
        assertEquals(6, aList.get(2));
        aList.removeByValue(5);
        aList.removeByValue(3);
        aList.removeByValue(6);
        aList.getNext(4);

    }

}
