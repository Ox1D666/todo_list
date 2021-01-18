package ru.job4j.todo.servlet;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.Hiber;
import ru.job4j.todo.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = new Hiber();
        req.setAttribute("items", new ArrayList<>(store.findAllItems()));
        List<Item> items = new ArrayList<>(store.findAllItems());
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (Item el : items) {
            writer.println(el.getDescription());
        }
        writer.flush();
    }
}
