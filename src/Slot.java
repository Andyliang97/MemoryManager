/**
 * Slot is the spot in hash table
 * @author Junjie Liang and Jie Zhang
 * @version 2018 Aug
 *
 */
public class Slot {
    private String key;
    private Handle newHandle;
    private Boolean tombstone;

    /**
     * default constructor
     */
    public Slot() {
        key = "";
        newHandle = new Handle();
        tombstone = false;
    }
    
    /**
     * constructor that allows you to enter keyvalue, data and handle
     * @param keyvalue key
     * 
     * @param returnedHandle the handle
     */
    public Slot(String keyvalue,  Handle returnedHandle) {
        key = keyvalue;
        newHandle = returnedHandle;
        tombstone = false;
    }
    
    /**
     * check if the slot is empty
     * @return true if it is empty, otherwise return false
     */
    public boolean isEmpty() {
        return ((getKey().equals("")) && (newHandle.isEmpty()));
    }
    
    /**
     * method used to set key value in the slot
     * @param keyvalue key
     */
    public void setKey(String keyvalue) {
        key = keyvalue;
    }

    /**
     * method used to get key value in the slot
     * @return the key value
     */
    public String getKey() {
        return key;
    }
    /**
     * method used to set handle
     * @param returnHandle handle
     */
    public void setHandle(Handle returnHandle) {
        newHandle = returnHandle;
    }
    
    /**
     * method used to get handle
     * @return the Handle
     */
    public Handle getHandle() {
        return newHandle;
    }
    
    /**
     * method used to set tombstone
     * 
     */
    public void setTombstone() {
        tombstone = true;
    }
    
    /**
     * method used to get tombstone
     * @return true if it is a tombstone
     */
    public boolean getTombstone() {
        return tombstone;
    }
    
    /**
     * method used to set slot to empty
     */
    public void setEmpty() {
        key = "";
        newHandle.setEmpty();
    }
}
