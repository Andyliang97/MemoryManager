/**
 * This is a handle class. 
 * Handle is something that 
 * returned from memory manager
 * after the insertion. 
 */

/**
 * The class containing starting 
 * position of the informantion 
 * and the length of it.
 *
 * @author Junjie Liang
 * @version 1.0
 */
public class Handle {
    private int starPosition;
    private int length;

/**
 * This is the default constructor. 
 * It set the starting position and length to -1. 
 */
    public Handle() {
        starPosition = -1;
        length = -1;
    }

/**
 * This is a constructor that allows 
 * user to set starting position and length.
 * @param sp it is an integer and the starting position
 * @param size it is an integer and the length
 */
    public Handle(int sp, int size) {
        starPosition = sp;
        length = size;
    }

/**
 * This is used to set starting position.
 * @param sp it is an integer and the starting position
 */
    public void setSP(int sp) {
        starPosition = sp;
    }

/**
 * this is used to get the starting position.
 * @return the start position
 */
    public int getSP() {
        return starPosition;
    }
    
/**
 * this is used to set the length
 * @param size it is an integer and the length
 */
    public void setLength(int size) {
        length = size;
    }

/**
 * this is used to get the length
 * @return the size of the record
 */
    public int getLength() {
        return length;
    }
    
/**
 * this is used to check if the handle is empty
 * @return true if it is empty, otherwise return false
 */
    public boolean isEmpty() {
        return ((starPosition == -1) && (length == -1));
    }
    
/**
 * this is used to set the handle as a empty handle
 */
    public void setEmpty() {
        starPosition = -1;
        length = -1;
    }
}
