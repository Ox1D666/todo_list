package ru.job4j.todo.store;

import java.util.List;

public interface Store<T> {
    T create(T element);
    boolean update(int id, T element);
    boolean delete(int id);
    List<T> findAll();
    T findById(int id);
}
