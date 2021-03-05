package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.HiberItem;
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
        HiberItem store = new HiberItem();
        String desc = req.getParameter("desc");
        String userId = req.getParameter("user_id");
        String[] cIDs = req.getParameterValues("cIDs");
        Timestamp create = new Timestamp(System.currentTimeMillis());
        if (desc != null) {
            store.create(new Item(req.getParameter("desc"), create,
                    new User(Integer.parseInt(userId))));
        }
        List<Item> items = new ArrayList<>(store.findAll());
        if (req.getParameter("done") != null) {
            switch (req.getParameter("done")) {
                case "all" -> items = store.findAll();
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
