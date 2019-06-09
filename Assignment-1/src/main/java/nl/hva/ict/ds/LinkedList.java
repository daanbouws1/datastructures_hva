package nl.hva.ict.ds;

/**
 * A linked list that adds elements to the end of the list and that retrieves elements from the end of the list as fast
 * as from the head of the list.
 * For example if a list contains 1000 elements the time needed to retrieve element at index 999 should be (almost) the
 * same as is needed for retrieving element at index 0. Retrieving element at index 800 should take (almost) the same
 * time as retrieving element at index 199.
 * When deleting an element all elements with the same value are deleted. So when deleting "don't" from a list that
 * contains<br/>
 * {"I", "don't", "like", "Datastructures", "as", "much", "as", "I", "don't", "like", "Sorting", "and", "Searching"}
 * this should result in a list containing:<br/>
 * {"I", "like", "Datastructures", "as", "much", "as", "I", "like", "Sorting", "and", "Searching"}
 *
 * @param <T> defines the type (class) that is stored in this list.
 */
public class LinkedList<T> {
    private class Node {
        private T value;
        private Node prev;
        private Node next;

        private Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node first;
    private Node head;
    private int size;

    /**
     * Adds a new element to the end of this list. The performance of this method is guaranteed to be constant, in other
     * words, the number of elements already stored in the list should have no influence on the time needed to add a new
     * element.
     * @param element the element that is added to the list.
     */
    public void add(T element) {
        if (element != null) {
            if (first == null) {
                first = new Node(element, null, null);
                head = first;
            } else {
                Node newNode = new Node(element, head, first);
                head.next = newNode;
                head = newNode;
            }
            first.prev = head;
            size++;
        } else {
            throw new IllegalArgumentException("Cant create null Node");
        }
    }

    /**
     * Returns an element from the list. If the index is negative or the element does not exists
     * an IllegalArgumentException is throw containing the reason in the message.
     * @param index the index, counted from the first element, of the element that must be returned.
     */
    public T get(int index) {
        Node node = head;
        if (index == 0) {
            return node.value;
        } else if (index > 0 && index <= size()) {
            while (--index > 0) {
                node = node.next;
            }
        } else {
            throw new IllegalArgumentException("That is not a valid index in the LinkedList");
        }
        return node.value;
    }

    /**
     * Deletes the element (if it exists) from the list. In case of multiple occurrences all the occurrences are
     * deleted.
     * @param element the element to delete.
     */
    public void delete(T element) {
        if (first != null) {
            int amount = 0;
            for (Node i = first; i != head; i = i.next) {
                System.out.println(i.value);
                if (i.value == element) {
                    amount++;
                }
            }
            if (head.value == element) amount++;
            resurciveDelete(element, first, amount);
        }
    }

    private void resurciveDelete(T element, Node node, int amountOfLoops) {
        int size = size();
        if (node.value == element) {
            if (size != 1) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else {
                head = null;
                first = null;
            }
            amountOfLoops--;
            this.size--;
        }

        if (amountOfLoops != 0 && size != 1) {
            resurciveDelete(element, node.next, amountOfLoops);
        }
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in the list.
     */
    public int size() {
        return size;
    }

}
