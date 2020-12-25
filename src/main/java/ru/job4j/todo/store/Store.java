package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;

import java.util.List;

public interface Store {
    Item create(Item item);
    boolean update(int id, Item item);
    boolean delete(int id);
    List<Item> findAllItems();
    List<Item> findByStatus(boolean status);
    Item findById(int id);
}
