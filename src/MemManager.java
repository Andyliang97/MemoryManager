/**
 * This is the Memory manager class
 * It contains a memory pool and memory manager
 * The memory manager will keep track of the free
 * blocks in the memory pool and the memory pool is
 * used for storing bytes.
 * 
 * @author Junjie Liang
 * @version 2018 Sep
 *
 */
public class MemManager {
    private byte[] mempool;
    private SubMemManager[] memArray;
    private int mempoolsize;

    /**
     * Constructor. poolsize defines the size of the memory pool in bytes
     * @param poolsize the memory pool size
     */
    MemManager(int poolsize) {
        mempool = new byte[poolsize];
        mempoolsize = poolsize;
        memArray = new SubMemManager[Integer.toBinaryString(poolsize).length()];
        int j = 0;
        for (int i = 1; i <= poolsize; i *= 2) {
            memArray[j] = new SubMemManager(i);
            j++;
        }
        memArray[j - 1].insertFreeSpace(0);
    }

    /**
     * Insert a record to memory pool and return 
     * its start position and length to the handle.
     * space contains the record to be inserted, of length size.
     * @param space the byte array
     * @param size size of the byte array
     * @return Handle that stores the length and start
     * position
     */
    public Handle insert(byte[] space, int size) {
        double log2size = logOfBase2(size);
        int memArraylength = 0;
        int intlog2size = (int) log2size;
        int previousArraylength = memArray.length;
        Handle returnedHandle;
        if (log2size > intlog2size) {
            intlog2size = intlog2size + 1;
        }

        if (!memArray[memArray.length - 1].isEmpty()) {
            memArraylength = memArray.length;
        } 
        else {
            memArraylength = memArray.length - 1;
        }
        if (intlog2size >= memArraylength) {
            for (int i = 0; i < intlog2size - (memArraylength - 1); i++) {
                arrayGrow(1);
            }
            merge(0, previousArraylength - 1);
            System.arraycopy(space, 0, mempool, 
                    memArray[intlog2size].getFreePosition(), size);
            returnedHandle = new Handle(
                    memArray[intlog2size].getFreePosition(), size);
            memArray[intlog2size].removeFreeSpace();
            return returnedHandle;
        } 
        else if (!memArray[intlog2size].isEmpty()) {
            System.arraycopy(space, 0, mempool, 
                    memArray[intlog2size].getFreePosition(), size);
            returnedHandle = new 
                    Handle(memArray[intlog2size].getFreePosition(), size);
            memArray[intlog2size].removeFreeSpace();
            return returnedHandle;
        } 
        else {

            if (division(intlog2size, intlog2size)) {
                System.arraycopy(space, 0, mempool, 
                        memArray[intlog2size].getFreePosition(), size);
                returnedHandle = new 
                        Handle(memArray[intlog2size].getFreePosition(), size);
                memArray[intlog2size].removeFreeSpace();
                return returnedHandle;
            } 
            else {
                arrayGrow(1);
                division(intlog2size, intlog2size);
                System.arraycopy(space, 0, mempool, 
                        memArray[intlog2size].getFreePosition(), size);
                returnedHandle = new 
                        Handle(memArray[intlog2size].getFreePosition(), size);
                memArray[intlog2size].removeFreeSpace();
                return returnedHandle;

            }
        }
    }

    /**
     * Dump a printout of the freeblock list
     */
    public void printblock() {
        boolean memArrayempty = false;
        for (int i = 0; i < memArray.length; i++) {
            if (!memArray[i].isEmpty()) {
                System.out.print(memArray[i].getSpaceNum() + ": ");
                memArray[i].print();
                memArrayempty = true;
            }
        }
        if (!memArrayempty) {
            System.out.println("No free blocks are available.");
        }
        // System.out.println("");
    }
    /**
     * this methos will split the free block into two smaller blocks.
     * this process will keep going until nothing can be split
     * @param spaceNum  the space num, cant be change
     * @param trackSpaceNum this help to track the space num
     * @return division happened, return true and execute the split process
     */
    public boolean division(int spaceNum, int trackSpaceNum) {
        if (trackSpaceNum >= memArray.length) {
            return false;
        }

        else if (!memArray[trackSpaceNum].isEmpty()) {
            return true;
        } 
        else {
            boolean divisionIsSuccessful = 
                    division(spaceNum, trackSpaceNum + 1);
            if (divisionIsSuccessful) {
                memArray[trackSpaceNum]
                        .insertFreeSpace(memArray[trackSpaceNum + 1]
                                .getFreePosition());
                memArray[trackSpaceNum].insertFreeSpace(
                        memArray[trackSpaceNum + 1].getFreePosition() + 
                        memArray[trackSpaceNum + 1].getSpaceNum() / 2);
                memArray[trackSpaceNum + 1].removeFreeSpace();
            }
            return (divisionIsSuccessful);

        }
    }

    /**
     * this function will grow both the memory pool 
     * array and the memArray. for this project,
     * i set the grow size always equals to 1
     * @param growsize the grow size you want to grow.
     */
    public void arrayGrow(int growsize) {
        mempoolsize = mempoolsize * 2 * growsize;
        System.out.println("Memory pool "
                + "expanded to be " + mempoolsize + " bytes.");
        
        int originalSize = memArray.length;
        int lastItemNum = originalSize - 1;
        byte[] tempArray = new byte[mempoolsize];
        System.arraycopy(mempool, 0, tempArray, 0, mempool.length);
        mempool = tempArray;

        SubMemManager[] tempmemArray = new 
                SubMemManager[memArray.length + growsize];
        System.arraycopy(memArray, 0, tempmemArray, 0, memArray.length);
        memArray = tempmemArray;
        for (int i = 1; i <= growsize; i++) {
            memArray[lastItemNum + i] = new 
                    SubMemManager(memArray[lastItemNum]
                            .getSpaceNum() * 2 * i); // manger array
            memArray[lastItemNum]
                    .insertFreeSpace(memArray[lastItemNum]
                            .getSpaceNum()); // memory pool
        }
        merge(memArray[lastItemNum].getSpaceNum(), lastItemNum);
    }

    /**
     * Return the length of the record associated with theHandle
     * @param theHandle the handle you give to the program
     * @return the length of this record
     */
    public int length(Handle theHandle) {
        return theHandle.getLength(); // test
    }

    /**
     * Free a block at the position specified by theHandle.
     * Merge adjacent free blocks.
     * @param theHandle the handle you give to the program
     */
    public void remove(Handle theHandle) {
        int startPos = theHandle.getSP();
        int size = theHandle.getLength();

        double log2size = logOfBase2(size);
        int intlog2size = (int) log2size;
        if (log2size > intlog2size) {
            intlog2size = intlog2size + 1;
        }

        for (int i = startPos; i < startPos + size; i++) {
            mempool[i] = 0;
        }

        memArray[intlog2size].insertSmallest(startPos);
        merge(startPos, intlog2size);

    }

    /**
     * Return the record with handle posHandle, up to size bytes, by
     * copying it into space.
     * Return the number of bytes actually copied into space.
     * @param space the byte array
     * @param theHandle the target handle
     * @param size the size of the record
     * @return the size of the record
     */
    public int get(byte[] space, Handle theHandle, int size) {
        int sp = theHandle.getSP();
        int length = theHandle.getLength();
        int spacelength = space.length;
        if (length <= spacelength) {
            System.arraycopy(mempool, sp, space, 0, length);
            return length;
        } 
        else {
            System.arraycopy(mempool, sp, space, 0, spacelength);
            return spacelength;
        }

    }

    /**
     * this method will take a handle and return
     * the record in the memory pool based on 
     * the handle you give 
     * @param theHandle the target handle
     * @return record
     */
    public String getString(Handle theHandle) {
        int sp = theHandle.getSP();
        int length = theHandle.getLength();
        byte[] space = new byte[length];
        System.arraycopy(mempool, sp, space, 0, length);
        String returnString = new String(space);
        return returnString;
    }

    /**
     * this method is used to merge two buddy free blocks
     * the function will keep running until no blocks can
     * merge
     * @param startpos starting position in the memory pool
     * @param startspace the starting space size
     * @return true if merge happens, otherwise return false
     */
    public boolean merge(int startpos, int startspace) {
        int combineItem = 0;
        int upperspace = 0;
        int modresult = 0;
        boolean isMerged = false;
        for (int i = startspace; i < memArray.length - 1; i++) {
            upperspace = memArray[startspace].getSpaceNum() * 2;
            modresult = startpos % upperspace; 
            // if modresult is equal to 0, it will look to the 
            //right side. otherwise,
           // look to the left side

            if (memArray[startspace].getSize() < 2) {
                return isMerged;
            } 
            else if (modresult == 0) {
                combineItem = memArray[startspace].peakright(startpos);
                if ((combineItem - startpos) == 
                        memArray[startspace].getSpaceNum()) {
                    memArray[startspace].remove(startpos);
                    memArray[startspace].remove(combineItem);
                    startspace = startspace + 1;
                    memArray[startspace].insertSmallest(startpos);
                    isMerged = true;
                } 
                else {
                    return isMerged;
                }
            } 
            else {
                combineItem = memArray[startspace].peakleft(startpos);
                if ((startpos - combineItem) == 
                        memArray[startspace].getSpaceNum()) {
                    memArray[startspace].remove(startpos);
                    memArray[startspace].remove(combineItem);
                    startspace = startspace + 1;
                    startpos = combineItem;
                    memArray[startspace].insertSmallest(combineItem);
                    isMerged = true;
                } 
                else {
                    return isMerged;
                }
            }
        }
        return isMerged;

    }

    /**
     *  this method is use to calculate the log
     *  base 2
     * @param num 
     * @return the math result
     */
    public double logOfBase2(int num) {
        return Math.log(num) / Math.log(2);
    }



}
