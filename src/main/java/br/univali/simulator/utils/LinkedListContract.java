package br.univali.simulator.utils;

import java.util.function.Consumer;

public interface LinkedListContract<T> {
    boolean contains(T data);

    T get(int index);

    void forEach(Consumer<T> consumer);

    boolean isEmpty();

    int size();
}
