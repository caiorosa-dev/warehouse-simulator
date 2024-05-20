package br.univali.simulator.utils;

import br.univali.simulator.utils.list.LinkedListNode;

import java.util.function.Consumer;

public class DynamicStack<T> {
    LinkedListNode<T> top;

    public DynamicStack() {
        top = null;
    }

    public void push(T value) {
        LinkedListNode<T> newNode = new LinkedListNode<>(value);

        newNode.setNext(top);

        top = newNode;
    }

    public T pop() {
        if (top == null) {
            return null;
        }

        T value = top.getData();
        top.setNext(top.getNext());

        return value;
    }

    public void forEach(Consumer<T> consumer) {
        LinkedListNode<T> current = this.top;

        while (current != null) {
            consumer.accept(current.getData());
            current = current.getNext();
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        LinkedListNode<T> current = this.top;
        int size = 0;

        while (current != null) {
            size++;
            current = current.getNext();
        }

        return size;
    }
}
