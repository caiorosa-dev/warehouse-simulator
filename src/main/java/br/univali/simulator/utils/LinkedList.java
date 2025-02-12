package br.univali.simulator.utils;

import br.univali.simulator.utils.list.LinkedListNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class LinkedList<T> implements LinkedListContract<T> {
    private LinkedListNode<T> head;

    public LinkedList() {
        this.head = null;
    }

    public void add(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<>(data);

        if (this.head == null) {
            this.head = newNode;
        } else {
            LinkedListNode<T> current = this.head;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(newNode);
        }
    }

    public void remove(T data) {
        if (this.head == null) {
            return;
        }

        if (this.head.getData().equals(data)) {
            this.head = this.head.getNext();
            return;
        }

        LinkedListNode<T> current = this.head;

        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                return;
            }

            current = current.getNext();
        }
    }

    public boolean contains(T data) {
        LinkedListNode<T> current = this.head;

        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public T get(int index) {
        LinkedListNode<T> current = this.head;

        for (int i = 0; i < index; i++) {
            if (current == null) {
                return null;
            }

            current = current.getNext();
        }

        return current.getData();
    }

    public int size() {
        LinkedListNode<T> current = this.head;
        int size = 0;

        while (current != null) {
            size++;
            current = current.getNext();
        }

        return size;
    }

    public void removeIf(Predicate<T> predicate) {
        LinkedListNode<T> current = this.head;
        LinkedListNode<T> previous = null;

        while (current != null) {
            if (predicate.test(current.getData())) {
                if (previous == null) {
                    this.head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
            } else {
                previous = current;
            }

            current = current.getNext();
        }
    }

    public void forEach(Consumer<T> consumer) {
        LinkedListNode<T> current = this.head;

        while (current != null) {
            consumer.accept(current.getData());
            current = current.getNext();
        }
    }

    public void clear() {
        this.head = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }
}
