
/**
 * This is what will be store in each spot of the memory manager array. 
 * I call it subMemManager. 
 * It contains a space and a double linked list.
 * The space represents how big the size of a block.
 * The double linked list will store the starting position of each free
 * block.
 * 
 * @author Junjie Liang, Jie Zhang
 * @version 2018 Sep
 */

public class SubMemManager {
    private int space;
    private LinkedList freePosition = new LinkedList();

    /**
     * Constructor
     * 
     * @param spacesize the space size of this list
     */
    public SubMemManager(int spacesize) {
        space = spacesize;
    }

    /**
     * this will append the input to the list
     * 
     * @param position the position in the memory pool
     */
    public void insertFreeSpace(int position) {
        freePosition.insertAtTail(position);
    }

    /**
     * this will remove the first item in the list
     * @return true if the remove succeed, otherwise return false
     */
    public boolean removeFreeSpace() {
        if (freePosition.isEmpty()) {
            return false;
        } 
        else {
            freePosition.removeFirst();
            return true;
        }
    }

    /**
     * this will get the first item in the list
     * 
     * @return the first item in the list
     */
    public int getFreePosition() {
        if (freePosition.isEmpty()) {
            return -1;
        } 
        else {
            return freePosition.getFirst();
        }
    }

    /**
     * this will check if the list is empty
     * 
     * @return true if the list is empty, otherwise return false
     */
    public boolean isEmpty() {
        return freePosition.isEmpty();
    }

    /**
     * this function will get the spacesize of this list
     * 
     * @return the how big a block is.
     */
    public int getSpaceNum() {
        return space;
    }

    /**
     * this will set the space size for the list
     * 
     * @param spacenum the space size you want
     */
    public void setSpaceNum(int spacenum) {
        space = spacenum;
    }

    /**
     * this will print the whole list out
     */
    public void print() {
        freePosition.print();

    }

    /**
     * this will insert the input into the list but it will keep the list sorted
     * 
     * @param freespace the position in the memory pool
     */
    public void insertSmallest(int freespace) {
        if (freePosition.isEmpty()) {
            freePosition.insertAtTail(freespace);
        } 
        else {
            freePosition.insertAtSmallest(freespace);
        }
    }

    

    /**
     * this method will return the item on its right. 
     * if the conditions is not fit,
     * it will return -1
     * 
     * @param startpos the target position
     * @return the start position of the free block on 
     * the given value's right side
     */
    public int peakright(int startpos) {
        int isFound = -1;
        isFound = freePosition.getNext(startpos);
        return isFound;
    }

    /**
     * this method will return the item on its left. 
     * if the conditions is not fit,
     * it will return -1
     * 
     * @param startpos the target position
     * @return the start position of the free block on 
     * the given value's left side
     */
    public int peakleft(int startpos) {
        int isFound = -1;
        isFound = freePosition.getPre(startpos);
        return isFound;
    }

    /**
     * this method will remove the target position from the list
     * 
     * @param startpos the target position
     * @return true if a remove happen, otherwise return false
     */
    public boolean remove(int startpos) {
        if (!isEmpty()) {
            freePosition.removeByValue(startpos);
            return true;
        }
        return false;
    }

    /**
     * this method will get the size of the list, 
     * which means the total numbers of
     * item
     * 
     * @return the size of this list
     */
    public int getSize() {
        return freePosition.getSize();
    }
}
