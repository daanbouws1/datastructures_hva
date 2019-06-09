/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author REMCO
 */
public class LinkedList<Item> implements Iterable<Item> {

    private Node head;
    private int size;
    private final Comparator<Item> comp;

    private class Node {   // nested class to define nodes
        Item item;
        Node next;
        Node prev;
    }

    public LinkedList(Comparator<Item> comp) {
        size = 0;
        this.comp = comp;
    }

    public void add(Item item) {
        Node newNode = new Node();
        Node current = head;
        newNode.item = item;
        if (head == null) {
            head = newNode;
        }

        if(current != null) {
            while(current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
    }

    public Item get(int index) {
        if (index + 1 > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node target = head;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }

        return target.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
