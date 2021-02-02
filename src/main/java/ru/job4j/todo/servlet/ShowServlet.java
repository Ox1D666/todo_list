package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.Hiber;
import ru.job4j.todo.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = new Hiber();
        String desc = req.getParameter("desc");
        Timestamp create = new Timestamp(System.currentTimeMillis());
        if (desc != null) {
            store.create(new Item(req.getParameter("desc"), create));
        }
        List<Item> items = new ArrayList<>(store.findAllItems());
        if (req.getParameter("done") != null) {
            switch (req.getParameter("done")) {
                case "all" -> items = store.findAllItems();
                case "true" -> items = store.findByStatus(true);
                case "false" -> items = store.findByStatus(false);
            }
        }
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(items));
        out.flush();
        out.close();
    }
}
