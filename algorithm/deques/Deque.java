import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class DequeIterator implements Iterator<Item> {

        public Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                return null;
            } else {
                Item next = current.item;
                current = current.next;
                return next;
            }
        }
    }

    private int size;
    private Node head;
    private Node tail;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    private void checkAdd(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkAdd(item);
        Node node = new Node();
        node.item = item;
        if (size == 0) {
            tail = node;
            head = node;
        } else {
            node.next = head;
            node.prev = null;
            head.prev = node;
            head = node;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        checkAdd(item);
        Node node = new Node();
        node.item = item;
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.next = null;
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item first = head.item;
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        return first;
    }

    // remove and return the item from the end
    public Item removeLast() {
        Item last = tail.item;
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        return last;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<>();
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addLast(7);
        deque.addLast(8);

        System.out.println(deque.size());


        Iterator<Integer> itr = deque.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next());
        }
        System.out.println("start");

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        //System.out.println(deque.removeLast());
        //System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.isEmpty());

    }
}
