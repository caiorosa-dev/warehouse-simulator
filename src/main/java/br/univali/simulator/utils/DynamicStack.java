package br.univali.simulator.utils;

import br.univali.simulator.utils.list.LinkedListNode;

import java.util.function.Consumer;

public class DynamicStack<T> implements LinkedListContract<T> {
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

        top = top.getNext();

        return value;
    }

    @Override
    public boolean contains(T data) {
        LinkedListNode<T> current = top;

        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    @Override
    public T get(int index) {
        LinkedListNode<T> current = top;
        int i = 0;

        while (current != null) {
            if (index == i) {
                return current.getData();
            }

            current = current.getNext();
            i++;
        }

        return null;
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
