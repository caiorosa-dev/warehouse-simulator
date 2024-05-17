package br.univali.simulator.utils;

import br.univali.simulator.utils.list.LinkedListNode;

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
}
