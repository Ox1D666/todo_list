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
import java.util.ArrayList;
import java.util.List;

public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = new Hiber();
//        req.setAttribute("items", new ArrayList<>(store.findAllItems()));
        List<Item> items = new ArrayList<>(store.findAllItems());
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        Gson gson = new Gson();
//        for (Item el : items) {
//            writer.println("<tr><td>" + el.getDescription() + "</td><td>" + el.getCreate() + "</td>" +
//                    "</td><td>" + el.isDone() + "</td></tr>");
//        }
//        writer.flush();
//        resp.sendRedirect(req.getContextPath() + "/index.do?done=all");
//        req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        writer.println("<tr><td>" + "1" + "</td><td>" + "1" + "</td>" +
//                    "</td><td>" + "1" + "</td></tr>");
//        writer.print(gson.toJson(new ArrayList<>(store.findAllItems())));
//        String json = new Gson().toJson(new ArrayList<>(store.findAllItems()));
//        resp.getWriter().write(json);
//        writer.flush();
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(items));
        out.flush();
        out.close();

    }
}
