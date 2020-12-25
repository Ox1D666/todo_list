package ru.job4j.todo.servlet;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.Hiber;
import ru.job4j.todo.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = new Hiber();
        try {
            if (req.getParameter("id") != null && req.getParameter("action").equals("remove")) {
                int id = Integer.parseInt(req.getParameter("id"));
                store.delete(id);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        if ((req.getParameter("id") != null)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Item item = store.findById(id);
            item.setDone(!item.isDone());
            store.update(id, item);
        }
        switch (req.getParameter("done")) {
            case "all" -> req.setAttribute("items", new ArrayList<>(store.findAllItems()));
            case "true" -> req.setAttribute("items", new ArrayList<>(store.findByStatus(true)));
            case "false" -> req.setAttribute("items", new ArrayList<>(store.findByStatus(false)));
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        Store store = new Hiber();
        store.create(new Item(req.getParameter("desc"),
                new Timestamp(new Date().getTime())));
        resp.sendRedirect(req.getContextPath() + "/index.do?done=all");
    }
}