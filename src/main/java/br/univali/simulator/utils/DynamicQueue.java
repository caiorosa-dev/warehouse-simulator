package br.univali.simulator.utils;

import br.univali.simulator.utils.list.LinkedListNode;

public class DynamicQueue<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    public DynamicQueue() {
        head = null;
        tail = null;
    }

    public void enqueue(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<>(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
    }

    public T dequeue() {
        if (head == null) {
            return null;
        }

        T data = head.getData();
        head = head.getNext();

        if (head != null) {
            head.setPrevious(null);
        }

        return data;
    }

    public T get(int i) {
        LinkedListNode<T> current = head;
        int index = 0;

        while (current != null) {
            if (index == i) {
                return current.getData();
            }

            current = current.getNext();
            index++;
        }

        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        LinkedListNode<T> current = head;

        while (current != null) {
            size++;
            current = current.getNext();
        }

        return size;
    }
}
