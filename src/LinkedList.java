/**
 * This is a linkedList class.
 * it is used for memory
 */
/**
 * A linkedList class
 * 
 * @author jiez
 *
 * 
 * @version 1.1
 */
public class LinkedList {
    private class Node {
        private int item;
        private Node pre = null;
        private Node next = null;


        Node(int item) {
            this.item = item;
        }
    }

    private Node head = null;
    private Node tail = null;


    /**
     * insert value at the head of the linkedList
     * 
     * @param value
     *            value to be inserted to the linkedList
     */
    public void insertAtHead(int value) {
        Node aNode = new Node(value);
        if (head == null) {
            head = aNode;
            tail = aNode;
        }
        else {
            aNode.next = head;
            head.pre = aNode;
            head = aNode;
        }
    }


    /**
     * insert function
     * insert a value at the tail of the linked list
     * 
     * @param value
     *            insert a value at the tail of the linked list
     */
    public void insertAtTail(int value) {
        Node aNode = new Node(value);
        if (head == null) {
            head = aNode;
            tail = aNode;
        }
        else {
            tail.next = aNode;
            aNode.pre = tail;
            tail = aNode;
        }
    }


    /**
     * get a value from given position
     * 
     * @param pos
     *            given position
     * @return the position node's value
     */
    public int get(int pos) {
        int position = 0;
        Node temp = head;
        if (position == pos) {
            return temp.item;
        }
        while (position != pos) {
            temp = temp.next;
            position++;
            if (temp == null) {
                return -1;
            }
        }
        return temp.item;
    }


    /**
     * insert function
     * insert a value in ascending order
     * 
     * @param value
     *            insert a value at its right position
     */
    public void insertAtSmallest(int value) {
        Node aNode = new Node(value);
        Node temp = head;
        if (head == null) {
            insertAtHead(value);
        }
        else {
            while (temp.next != null && temp.item < value) {
                temp = temp.next;
            }
            if (temp.item > value) {
                if (temp == head) {
                    insertAtHead(value);
                }
                else {
                    Node preToTemp = temp.pre;
                    preToTemp.next = aNode;
                    aNode.pre = preToTemp;
                    aNode.next = temp;
                    temp.pre = aNode;
                }
            }
            else {
                insertAtTail(value);
            }
        }

    }


    /**
     * delete function
     * delete from head function
     */
    public void removeFirst() {
        if (head.next == null) {
            tail = null;
        }
        else {
            head.next.pre = null;
        }
        head = head.next;

    }


    /**
     * delete function
     * delete from tail function
     */
    public void deleteFromTail() {
        if (head.next == null) {
            head = null;
        }
        else {
            tail.pre.next = null;
        }
        tail = tail.pre;

    }


    /**
     * get first node's value
     * 
     * @return the first node's value
     */
    public int getFirst() {
        Node temp = head;
        return temp.item;
    }


    /**
     * get last node's value
     * 
     * @return the last node's value
     */
    public int getLast() {
        Node temp = tail;
        return temp.item;
    }


    /**
     * get position function
     * 
     * @param data
     *            pass in a value that is searching for
     * @return return the position if the data is found
     */

    public int getPos(int data) {
        Node temp = head;
        int pos = 0;
        while (temp.item != data) {
            temp = temp.next;
            pos++;
            if (temp == null) {
                return -1;
            }
        }
        return pos;
    }

    /**
     * remove a node from the linked list by passing in a
     * value
     * @param value the value used for searching node
     * @return return -1 if the node is not found
     * otherwise the node is removed successfully
     */
    public int removeByValue(int value) {
        Node cur = head;
        while (cur.item != value) {
            cur = cur.next;
            if (cur == null) {
                return -1;
            }
        }
        if (cur == head) {
            head = cur.next;
        }
        else {
            cur.pre.next = cur.next;
        }

        if (cur == tail) {
            tail = cur.pre;
        }
        else {
            cur.next.pre = cur.pre;
        }
        return cur.item;
    }


    /**
     * get next value function
     * 
     * @param data
     *            pass in a data and search for its position
     * @return return the item of data's next position
     */
    public int getNext(int data) {
        Node temp = head;
        while (temp.item != data) {
            temp = temp.next;
            if (temp == null) {
                return -1;
            }
        }
        if (temp.next == null) {
            return -1;
        }
        else {
            temp = temp.next;
            return temp.item;
        }
    }


    /**
     * get previous value function
     * 
     * @param data
     *            data pass in a data and search for its position
     * @return return the item of data's previous position
     */
    public int getPre(int data) {
        Node temp = head;
        while (temp.item != data) {
            temp = temp.next;
            if (temp == null) {
                return -1;
            }
        }
        if (temp.pre == null) {
            return -1;
        }
        else {
            temp = temp.pre;
            return temp.item;
        }
    }


    /**
     * determine if the linkedList is empty function
     * 
     * @return return if the linkedList is empty
     */

    public boolean isEmpty() {
        return head == null;
    }


    /**
     * output function
     */

    public void print() {
        Node aNode = head;
        System.out.print(getFirst());
        aNode = aNode.next;
        while (aNode != null) {
            System.out.print(" " + aNode.item);
            aNode = aNode.next;
        }
        System.out.println("");
    }

    /**
     * get the size of the linked list
     * @return the size of the linked list
     */
    public int getSize() {
        int size = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }
}
