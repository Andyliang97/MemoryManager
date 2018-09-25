import java.util.Arrays;

/**
 * Stub for hash table class
 *
 * @author Junjie Liang and Jie Zhang
 * @version August 2018
 */

public class Hash {
    private Slot[] hashTable;
    private int hashSize = 0;
    private int count = 0;
    private MemManager memmanager;


    /**
     * get count
     * 
     * @return integer
     */
    public int getCount() {
        return count;
    }


    /**
     * get hash table size
     * 
     * @return integer
     */
    public int gethashSize() {
        return hashSize;
    }


    /**
     * constructor
     * 
     * @param initHashSize the initial hash size
     * @param initMemSize the initial memory size
     */
    public Hash(int initHashSize, int initMemSize) {
        hashTable = new Slot[initHashSize];
        memmanager = new MemManager(initMemSize);
        hashSize = initHashSize;
        for (int i = 0; i < hashSize; i++) {
            hashTable[i] = new Slot();
        }
    }


    /**
     * Compute the hash function. Uses the "sfold" method from the OpenDSA
     * module on hash functions
     *
     * @param s
     *            The string that we are hashing
     * @param m
     *            The size of the hash table
     * @return The home slot for that string
     */
    public int h(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % m);
    }


    /**
     * this is the function used for quadratic probing
     * 
     * @param homeSlot
     *            the home number
     * @param i
     *            value i
     * @param m
     *            hash table size
     * @return calculated slot number
     */
    public int p(int homeSlot, int i, int m) {
        int newSlot = (homeSlot + i * i) % m;
        return newSlot;
    }
    
    /**
     * based on the print command, we choose the print method
     * 
     * @param printCommand
     *            the print command
     */
    public void printSelect(String printCommand) {
        if (printCommand.equals("blocks")) {
            memmanager.printblock();
        }
        else if (printCommand.equals("hashtable")) {
            printHashtable();
        }
        else {
            System.out.println("Wrong Print Command!");
        }
    }
    
    /**
     * add key record to hash table
     * 
     * @param name
     *            key value
     * @return true if a addition happen, otherwise return false
     */
    public boolean add(String name) {

        int hashNumber = h(name, hashSize);
        int homeNumber = hashNumber;
        int place = search(name);
        if (place != -1) {
            System.out.println("|" + name
                + "| duplicates a record already in the Name database.");
            return false;
        }

        for (int i = 1; !hashTable[hashNumber].isEmpty(); i++) {

            /*
             * if (name.equals(hashTable[hashNumber].getKey())) {
             * System.out.println("|" + name +
             * "| duplicates a record already in the Name database.");
             * return false;
             * }
             */
            hashNumber = p(homeNumber, i, hashSize);
        }
        Handle theHandle = new Handle();
        theHandle = memmanager.insert(name.getBytes(), name.length());
        if (count == hashSize / 2) {
            grow();
            hashNumber = h(name, hashSize);
            homeNumber = hashNumber;
            for (int i = 1; !hashTable[hashNumber].isEmpty(); i++) {
                /*
                 * if (name.equals(hashTable[hashNumber].getKey())) {
                 * System.out.println("|" + name +
                 * "| duplicates a record already "
                 * + "in the Name database.");
                 * return false;
                 * }
                 */
                hashNumber = p(homeNumber, i, hashSize);
            }
        }

        hashTable[hashNumber].setKey(name);
        //Handle theHandle = new Handle();
        //theHandle = memmanager.insert(name.getBytes(), name.length());
        hashTable[hashNumber].setHandle(theHandle);
        System.out.println("|" + name
            + "| has been added to the Name database.");

        count++;
        return true;
    }


    /**
     * this method is to grow the hash table size
     * when the record number exceed half of the
     * hash table size
     */
    public void grow() {
        int hashNumber = 0;
        int homeNumber = 0;
        int j = 0;
        hashSize = 2 * hashSize;
        Slot[] newArray = new Slot[hashSize];
        for (int i = 0; i < hashSize; i++) {
            newArray[i] = new Slot();
        }
        for (int i = 0; i < hashTable.length; i++) {
            if (!hashTable[i].isEmpty()) {
                hashNumber = h(hashTable[i].getKey(), hashSize);
                homeNumber = hashNumber;
                while (!newArray[hashNumber].isEmpty()) {
                    hashNumber = p(homeNumber, j, hashSize);
                    j++;
                }
                newArray[hashNumber] = new Slot(hashTable[i].getKey(),
                     hashTable[i].getHandle());

            }
        }
        hashTable = newArray;
        System.out.println("Name hash table " + "size doubled to " + hashSize
            + " slots.");
    }

    /**
     * this method will print every thing in the Hash table
     */
    public void printHashtable() {
        for (int i = 0; i < hashSize; i++) {
            if (!hashTable[i].isEmpty()) {
                System.out.println("|" + hashTable[i].getKey() + "| " + i);
            }
        }
        System.out.println("Total records: " + count);
    }


    /**
     * this method is used to delete record from hash table
     * 
     * @param name
     *            the key value
     * @return boolean
     */
    public boolean delete(String name) {
        int place = search(name);
        if (place != -1) {
            memmanager.remove(hashTable[place].getHandle());
            hashTable[place].setEmpty();
            hashTable[place].setTombstone();
            count--;
            System.out.println("|" + name
                + "| has been deleted from the Name database.");
            return true;
        }
        else {
            System.out.println("|" + name + "| not deleted because it does "
                + "not exist in the Name database.");
            return false;
        }

    }


    /**
     * this method is used to search where the record is
     * 
     * @param name
     *            key value
     * @return the key
     */
    public int search(String name) {
        int hashNumber = h(name, hashSize);
        int homeNumber = hashNumber;
        for (int i = 1; !hashTable[hashNumber].isEmpty()
            || hashTable[hashNumber].getTombstone(); i++) {

            if (name.equals(hashTable[hashNumber].getKey())) {
                return hashNumber;
            }
            else {
                hashNumber = p(homeNumber, i, hashSize);
            }
        }
        return -1;
    }


    /**
     * this method is used to select update command
     * 
     * @param updateCommand
     *            the update Command
     */
    public void updateSelect(String updateCommand) {
        String[] updateArray = updateCommand.split(" ", 2);
        if (updateArray[0].equals("add")) {
            updateadd(updateArray[1]);
        }
        else if (updateArray[0].equals("delete")) {
            updatedelete(updateArray[1]);
        }
        else {
            System.out.println("Wrong Update Command!");
        }
    }


    /**
     * this method is used for update add command
     * 
     * @param info
     *            all the information you need to update
     * @return true if a update add command executed successfully
     */
    public boolean updateadd(String info) {
        String[] infoArray = info.split("<SEP>", 2);
        Handle aHandle = new Handle();
        String out = "Updated Record: |";
        int place = search(infoArray[0].trim().replaceAll("\t", " ").replaceAll(
            " +", " "));
        if (place == -1) {
            System.out.println("|" + infoArray[0]
                + "| not updated because it does "
                + "not exist in the Name database.");
            return false;
        }
        else {
            String[] strArray = infoArray[1].split("<SEP>");
            for (int x = 0; x < infoArray.length; x++) {
                strArray[x] = strArray[x].trim();
            }
            aHandle = hashTable[place].getHandle();
            String str = memmanager.getString(aHandle);
            //memmanager.remove(aHandle);
            
            String[] checkArray = strArray;

            for (int i = 0; i < checkArray.length; i = i + 2) {
                if (!str.contains(checkArray[i])) {
                    str = str + "<SEP>" + checkArray[i] + "<SEP>" + checkArray[i
                        + 1];
                }
                else {
                    String check = checkArray[i];
                    String[] strArray2 = str.split("<SEP>");
                    int index =  Arrays.asList(strArray2).indexOf(check);
                    
                    /*for (int j = 0; j < strArray2.length; j++) {
                        if (strArray2[j].equals(check)) {
                            index = j;
                            break;
                        }
                    }*/
                    strArray2[index] = "";
                    strArray2[index + 1] = "";
                    //strArray2[index + 1] = checkArray[i + 1];
                    /**
                     * str = infoArray[0];
                     * for (int j = 1; j < strArray2.length; j++) {
                     * if (!strArray2[j].equals("")) {
                     * str = str + "<SEP>" + strArray2[j];
                     * }
                     * }
                     **/
                    str = strArray2[0];
                    for (int j = 1; j < strArray2.length; j++) {
                        if (!strArray2[j].equals("")) {
                            str = str + "<SEP>" + strArray2[j];
                        }
                        
                    }
                    str = str + "<SEP>" + check + "<SEP>" + checkArray[i + 1];

                }
            }
            memmanager.remove(aHandle);
            aHandle = memmanager.insert(str.getBytes(), str.length());
            // memmanager.remove(aHandle);
            hashTable[place].setHandle(aHandle);

            System.out.println(out + str + "|");
            return true;
        }
    }


    /**
     * this method is used for update delete command
     * 
     * @param info
     *            all the information you need to update
     * @return true if a update delete command executed successfully
     */
    public boolean updatedelete(String info) {
        String[] str = info.split("<SEP>", 2);
        String[] infoArray = str[1].trim().replaceAll("\t", "").replaceAll(" +",
            "").split("<SEP>");

        int place = search(str[0].trim().replaceAll("\t", " ").replaceAll(
                " +", " "));
        if (place == -1) {
            System.out.println("|" + str[0] + "| not updated because it does "
                + "not exist in the Name database.");
            return false;
        }
        else {
            Handle aHandle = hashTable[place].getHandle();
            String data = memmanager.getString(aHandle);
            //memmanager.remove(aHandle);
            String[] dataArray = data.split("<SEP>");
            int index = -1;
            for (int i = 0; i < infoArray.length; i++) {
                for (int j = 1; j < dataArray.length; j = j + 2) {
                    if (dataArray[j].equals(infoArray[i])) {
                        index = j;
                        break;
                    }
                }
                if (index == -1) {
                    System.out.println("|" + str[0] + "|"
                        + " not updated because the field |" + infoArray[0]
                        + "| does not exist");
                    return false;
                }
                else {
                    dataArray[index] = "";
                    dataArray[index + 1] = "";

                }
            }
            data = dataArray[0];
            for (int j = 1; j < dataArray.length; j++) {
                if (!dataArray[j].equals("")) {
                    data = data + "<SEP>" + dataArray[j];
                }
            }
            memmanager.remove(aHandle);
            aHandle = memmanager.insert(data.getBytes(), data.length());
            //memmanager.remove(aHandle);
            hashTable[place].setHandle(aHandle);
            System.out.println("Updated Record: |" + data + "|");
            return true;
        }
    }    
}
