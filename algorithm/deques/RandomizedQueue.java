import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Random rand;
    private ArrayList<Item> elements;

    private class RandomizedQueueIterator implements Iterator<Item> {

        private RandomizedQueue<Item> rq;

        private RandomizedQueueIterator() {
            rq = new RandomizedQueue<Item>();
            for (int i = 0; i < elements.size(); i++) {
                rq.enqueue(elements.get(i));
            }
        }

        public boolean hasNext() {
            return !rq.isEmpty();
        }

        public Item next() {
            return rq.dequeue();
        }
    }


    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        rand = new Random();
        elements = new ArrayList<>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        elements.add(item);
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        size--;
        if (size == 0) {
            return elements.remove(0);
        }
        return elements.remove(rand.nextInt(size));
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return elements.get(rand.nextInt(size - 1));
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        System.out.println(rq.isEmpty());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);

        Iterator<Integer> itr = rq.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next());
        }
        System.out.println("start");

        System.out.println(rq.sample());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
    }

}
