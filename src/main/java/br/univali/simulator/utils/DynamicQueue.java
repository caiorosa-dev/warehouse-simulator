package br.univali.simulator.utils;

public class DynamicQueue {
    private Node first;
    private Node last;
    private int size;

    public DynamicQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void enqueue(Object provisions) {
        Node newNode = new Node(provisions);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        Object data = first.getData();
        first = first.getNext();
        size--;
        return data;
    }

    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return first.getData();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private class Node {
        private Node next;
        private Object provender;

        public Node(Object provisions) {
            this.provender = provisions;
            this.next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getData() {
            return provender;
        }
    }
}
