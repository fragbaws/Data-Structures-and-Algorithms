import java.util.Iterator;
import java.util.Random;

public class DoubleLinkedList<E> {
    /**
     * Node of a singly linked list, which stores a reference to its element and to
     * the subsequent node in the list (or null if this is the last node).
     */
    private static class Node<E> {

        /** The element stored at this node */
        private E element; // reference to the element stored at this node

        /** A reference to the subsequent node in the list */
        private Node<E> next; // reference to the subsequent node in the list
        private Node<E> previous;

        /**
         * Creates a node with the given element and next node.
         *
         * @param e: the element to be stored
         * @param n: reference to a node that should follow the new node
         */
        public Node(E e, Node<E> p, Node<E> n) {
            this.element = e;
            this.next = n;
            this.previous = p;
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
        public void setNext(Node<E> n) {
            this.next = n;
        }

        public void setPrevious(Node<E> p) {
            this.previous = p;
        }

        public Node getPrevious()
        {
            return this.previous;
        }
    }

    // instance variables of the DoublyLinkedList

    /** Number of nodes in the list */
    private int size = 0; // number of nodes in the list

    /** Sentinel node beginning of list*/
    private Node<E> header;

    /**Sentinel node end of list*/
    private Node<E> trailer;

    /** Constructs an initially empty list. */
    public DoubleLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
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
        if(isEmpty()) return null;
        else
            return (E) header.getNext().getElement();
    }

    /**
     * Returns (but does not remove) the last element of the list.
     *
     * @return element at the end of the list (or null if empty)
     */
    public E last() { // returns (but does not remove) the last element
        return trailer.element;
    }

    // update methods

    /** Adds an elements between two elements*/
    public void addBetween(E e, Node<E> predecessor, Node<E> successor)
    {
        Node<E> newest = new Node<E>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrevious(newest);
        size++;
    }

    public void addBefore(E key, E e)
    {
        for(Node<E> curr = header.getNext(); curr != trailer; curr = curr.getNext())
        {
            if(key.equals(curr.getElement()))
            {
                addBetween(e, curr.getPrevious(), curr);
            }
        }
    }
    
    public void addFirst(E e)
    {
        addBetween(e, header, header.getNext());
    }

    public void addLast(E e)
    {
        addBetween(e, trailer.getPrevious(), trailer);
    }

    public void remove(E e)
    {
        for(Node<E> curr = header.getNext(); curr!=trailer; curr = curr.getNext())
        {
            if(e.equals(curr.getElement()))
            {
                removeNode(curr);
                return;
            }
        }
    }
    /** Remove node*/
    public E removeNode(Node<E> node)
    {
        Node<E> predecessor = node.getPrevious();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrevious(predecessor);
        size--;

        return node.getElement();
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

        E element = header.getElement();
        header = header.getNext();
        size--;
        if(size == 0)
        {
            trailer = null;
        }

        return element;
    }

    public E removeLast()
    {
        if(isEmpty())
        {
            return null;
        }

        E element = (E) trailer.getPrevious();
        removeNode((Node<E>) element);

        return element;
    }

    public Object copy() {
        DoubleLinkedList copy = new DoubleLinkedList();
        Node tmp = header;
        while(tmp!=null)
        {
            copy.addBetween(tmp.getElement(), tmp.getNext(), tmp.getPrevious());
            tmp = tmp.next;
        }

        return copy;
    }

    @SuppressWarnings({ "unchecked" })
    public boolean equals(Object o) {

        DoubleLinkedList other = (DoubleLinkedList) o;
        if(o==null) return false;
        if(getClass() != o.getClass()) return false;
        if(size != other.size) return false;

        Node A = header;
        Node B = other.header;

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
             curr = header.getNext();
        }

        @Override
        public boolean hasNext() {
            return curr!=trailer;
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
        Node curr = header.getNext();
        while(curr!=trailer)
        {
            result += curr.element + " ";
            curr = curr.getNext();
        }

        return "List: " + result;
    }

    public static void main(String[] args) {

    }
}