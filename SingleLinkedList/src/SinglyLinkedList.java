import java.util.Iterator;

public class SinglyLinkedList<E> {
/**
 * Node of a singly linked list, which stores a reference to its element and to
 * the subsequent node in the list (or null if this is the last node).
 */
private class Node {

    /** The element stored at this node */
    private E element; // reference to the element stored at this node

    /** A reference to the subsequent node in the list */
    private Node next; // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e: the element to be stored
     * @param n: reference to a node that should follow the new node
     */
    public Node(E e, Node n) {
        this.element = e;
        this.next = n;
    }

    /**
     * Returns the element stored at the node.
     *
     * @return the element stored at the node
     */
    public E getElement() {
        return this.element;
    }

    /**
     * Returns the node that follows this one (or null if no such node).
     *
     * @return the following node
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Sets the node's next reference to point to Node n.
     *
     * @param n
     *            the node that should follow this one
     */
    public void setNext(Node n) {
        this.next = n;
    }
}

    // instance variables of the SinglyLinkedList
    /** The head node of the list */
    private Node head = null; // head node of the list (or null if empty)

    /** The last node of the list */
    private Node tail = null; // last node of the list (or null if empty)

    /** Number of nodes in the list */
    private int size = 0; // number of nodes in the list

    /** Constructs an initially empty list. */
    public SinglyLinkedList() {

    }

    // access methods
    /**
     * Returns the number of elements in the linked list.
     *
     * @return number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Returns (but does not remove) the first element of the list
     *
     * @return element at the front of the list (or null if empty)
     */
    public E first() { // returns (but does not remove) the first element
        return head.element;
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the end of the list (or null if empty)
     */
    public E last() { // returns (but does not remove) the last element
        return tail.element;
    }

    // update methods
    /**
     * Adds an element to the front of the list.
     *
     * @param e
     *            the new element to add
     */
    public void addFirst(E e) { // adds element e to the front of the list
        head = new Node(e, head);
        if(isEmpty())
            tail = head;
        size++;
    }


    /**
     * Adds an element to the end of the list.
     *
     * @param e
     *            the new element to add
     */
    public void addLast(E e) { // adds element e to the end of the list
        Node last = new Node(e,null);
        if(isEmpty())
            head = last;
        else
            tail.setNext(last);
        tail = last;
        size++;
    }

    public void insertAfter(E key, E s)
    {
        Node tmp = head;
        while(tmp != null && !tmp.getElement().equals(key))
        {
            tmp = tmp.next;
        }
        if(tmp != null)
        {
            tmp.setNext(new Node(s, tmp.getNext()));
        }
    }

    public void insertBefore(E key, E s) {
        if(head == null)
            return;
        if(head.getElement().equals(key))
        {
            addFirst(s);
        }

        Node prev = null;
        Node cur = head;

        while(cur != null && !cur.getElement().equals(key))
        {
            prev = cur;
            cur = cur.getNext();
        }

        if(cur != null)
        {
            prev.setNext(new Node(s, cur));
        }

    }

    public void remove(E key) {
        if(head == null)
        {
            new RuntimeException("Cannot delete");
            return;
        }
        if(head.getElement().equals(key))
        {
            head = head.next;
            return;
        }

        Node prev = null;
        Node cur = head;

        while(cur != null && !cur.getElement().equals(key))
        {
            prev = cur;
            cur = cur.getNext();
        }

        prev.next = cur.next;
    }
    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeFirst() { // removes and returns the first element
        if(isEmpty())
        {
            new RuntimeException("Cannot delete");
            return null;
        }

        E element = head.getElement();
        head = head.getNext();
        size--;
        if(size == 0)
        {
            tail = null;
        }

        return element;
    }

    public Object copy() {
        SinglyLinkedList copy = new SinglyLinkedList();
        Node tmp = head;
        while(tmp!=null)
        {
            copy.addLast(tmp.getElement());
            tmp = tmp.next;
        }

        return copy;
    }

    @SuppressWarnings({ "unchecked" })
    public boolean equals(Object o) {

        SinglyLinkedList other = (SinglyLinkedList) o;
        if(o==null) return false;
        if(getClass() != o.getClass()) return false;
        if(size != other.size) return false;

        Node A = head;
        Node B = other.head;

        while(A != null)
        {
            if(!A.getElement().equals(B.getElement())) return false;

            A = A.getNext();
            B = B.getNext();
        }

        return true;
    }

    public Iterator<E> iterator() {
        return new ListIterator();
    }

private class ListIterator implements Iterator<E> {

    Node curr;
    public ListIterator()
    {
        head = curr;
    }

    @Override
    public boolean hasNext() {
        return curr!=null;
    }

    @Override
    public E next() {
        E res = (E) curr.getElement();
        curr = curr.getNext();
        return res;
    }
    // TODO
}
    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        String result = "";
        Node curr = head;
        while(curr!=null)
        {
            result += curr.element + " ";
            curr = curr.getNext();
        }

        return "List: " + result;
    }

    public static void main(String[] args) {

        SinglyLinkedList LinkedList = new SinglyLinkedList();
        String [] data = {"one", "two", "three", "four", "five"};

        for(String s: data)
        {
            LinkedList.addLast(s);
        }

        System.out.println(LinkedList);


    }
}